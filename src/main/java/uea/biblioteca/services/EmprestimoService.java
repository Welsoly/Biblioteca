package uea.biblioteca.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.biblioteca.models.Emprestimo;
import uea.biblioteca.repositories.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
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
