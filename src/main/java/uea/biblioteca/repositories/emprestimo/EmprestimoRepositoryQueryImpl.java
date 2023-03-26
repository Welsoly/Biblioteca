package uea.biblioteca.repositories.emprestimo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import uea.biblioteca.dto.ResumoEmprestimoDto;
import uea.biblioteca.filters.EmprestimoFilter;
import uea.biblioteca.models.Emprestimo;

public class EmprestimoRepositoryQueryImpl implements EmprestimoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ResumoEmprestimoDto> filtrar(EmprestimoFilter emprestimoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<ResumoEmprestimoDto> criteria = builder.createQuery(ResumoEmprestimoDto.class);
		Root<Emprestimo> root = criteria.from(Emprestimo.class);
		// USUARIO VAI SE TORNA PESSOA FUTURAMENTE
		criteria.select(builder.construct(ResumoEmprestimoDto.class, root.get("codigo"), root.get("descricao"),
				root.get("dataEmprestimo"), root.get("dataPagamento"), root.get("livro").get("titulo"),
				root.get("pessoa").get("nome")));

		Predicate[] predicates = criarRestricoes(emprestimoFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}

		TypedQuery<ResumoEmprestimoDto> query = manager.createQuery(criteria);

		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(emprestimoFilter));
	}

	private Long total(EmprestimoFilter emprestimoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Emprestimo> root = criteria.from(Emprestimo.class);

		Predicate[] predicates = criarRestricoes(emprestimoFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<ResumoEmprestimoDto> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDeRegistroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRegistroPorPagina);
	}

	private Predicate[] criarRestricoes(EmprestimoFilter emprestimoFilter, CriteriaBuilder builder,
			Root<Emprestimo> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (emprestimoFilter.getDataEmprestimoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataEmprestimo"), emprestimoFilter.getDataEmprestimoDe()));
		}

		if (emprestimoFilter.getDataEmprestimoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataEmprestimo"), emprestimoFilter.getDataEmprestimoAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}