package uea.biblioteca.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import uea.biblioteca.models.Livro;
import uea.biblioteca.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroResource {
	
	@Autowired
	private LivroService livroService;
	
	@PostMapping
	public ResponseEntity<Livro> criar(@Valid @RequestBody Livro livro) {
		Livro livroSalva = livroService.criar(livro);
		
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(livroSalva.getId()).toUri();
		
		return ResponseEntity.created(uri).body(livroSalva);
	}
	
	@GetMapping
	public ResponseEntity<List<Livro>> listar() {
		List<Livro> livros = livroService.listar();
		return ResponseEntity.ok().body(livros);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> buscarPorId(@PathVariable 
			Long id){
		Livro livro = livroService.buscarPorId(id);
		return ResponseEntity.ok().body(livro);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		livroService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id,
			@Valid @RequestBody Livro livro){
		Livro livroSalva = livroService.atualizar(id,
				livro);
		return ResponseEntity.ok().body(livroSalva);
		
	}
	
	
	
}