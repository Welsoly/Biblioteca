package uea.biblioteca.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.biblioteca.models.Pessoa;
import uea.biblioteca.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa criar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public List<Pessoa> listar(){
		return pessoaRepository.findAll();
	}
	
	public Pessoa buscarPorId(Long id) {
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow();
		return pessoa;
	}
	
	public void excluir(Long id) {
		pessoaRepository.deleteById(id);
	}
	public Pessoa atualizarPropriedadeAtivo(Long codigo,
			Boolean ativo) {
		Pessoa pessoaSalva = pessoaRepository.findById(codigo).
				orElseThrow();
		pessoaSalva.setAtivo(ativo);
		return pessoaRepository.save(pessoaSalva);
	}
	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepository.
				findById(id).orElseThrow();
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		return pessoaRepository.save(pessoaSalva);
	}
	

}
