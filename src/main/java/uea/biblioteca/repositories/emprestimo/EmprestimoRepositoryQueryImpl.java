package uea.biblioteca.repositories.emprestimo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
	public List<ResumoEmprestimoDto> filtrar(EmprestimoFilter emprestimoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<ResumoEmprestimoDto> criteria = builder.createQuery(ResumoEmprestimoDto.class);
		Root<Emprestimo> root = criteria.from(Emprestimo.class);
		// USUARIO VAI SE TORNA PESSOA FUTURAMENTE
		criteria.select(builder.construct(ResumoEmprestimoDto.class, root.get("codigo"), root.get("descricao"),
				root.get("dataEmprestimo"), root.get("dataPagamento"), root.get("livro").get("titulo"),
				root.get("usuario").get("nome")));

		Predicate[] predicates = criarRestricoes(emprestimoFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}

		List<ResumoEmprestimoDto> returnList = manager.createQuery(criteria).getResultList();

		return returnList;
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