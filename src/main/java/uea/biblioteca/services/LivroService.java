package uea.biblioteca.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.biblioteca.models.Livro;
import uea.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public Livro criar(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public List<Livro> listar(){
		return livroRepository.findAll();
	}
	
	public Livro buscarPorId(Long id) {
		Livro livro = livroRepository.findById(id).orElseThrow();
		return livro;
	}
	
	public void excluir(Long id) {
		livroRepository.deleteById(id);
	}
	
	public Livro atualizar(Long id, Livro livro) {
		Livro livroSalva = livroRepository.
				findById(id).orElseThrow();
		BeanUtils.copyProperties(livro, livroSalva, "id");
		return livroRepository.save(livroSalva);
	}
	

}
