package uea.biblioteca.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uea.biblioteca.dto.ResumoEmprestimoDto;
import uea.biblioteca.filters.EmprestimoFilter;
import uea.biblioteca.models.Emprestimo;
import uea.biblioteca.repositories.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	public Page<ResumoEmprestimoDto> resumir(EmprestimoFilter emprestimoFilter,
			Pageable pageable){
		return emprestimoRepository.filtrar(emprestimoFilter, pageable);
	}
	public Emprestimo criar(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	public List<Emprestimo> listar(){
		return emprestimoRepository.findAll();
	}
	
	public Emprestimo buscarPorId(Long id) {
		Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow();
		return emprestimo;
	}
	
	public void excluir(Long id) {
		emprestimoRepository.deleteById(id);
	}
	
	public Emprestimo atualizar(Long id, Emprestimo emprestimo) {
		Emprestimo emprestimoSalva = emprestimoRepository.
				findById(id).orElseThrow();
		BeanUtils.copyProperties(emprestimo, emprestimoSalva, "id");
		return emprestimoRepository.save(emprestimoSalva);
	}
	

}
