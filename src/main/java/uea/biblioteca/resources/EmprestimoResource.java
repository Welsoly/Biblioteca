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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import uea.biblioteca.dto.ResumoEmprestimoDto;
import uea.biblioteca.filters.EmprestimoFilter;
import uea.biblioteca.models.Emprestimo;
import uea.biblioteca.services.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoResource {
	
	@Autowired
	private EmprestimoService emprestimoService;
	
	@PostMapping
	public ResponseEntity<Emprestimo> criar(@Valid @RequestBody Emprestimo emprestimo) {
		Emprestimo emprestimoSalva = emprestimoService.criar(emprestimo);
		
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(emprestimoSalva.getId()).toUri();
		
		return ResponseEntity.created(uri).body(emprestimoSalva);
	}
	
	@GetMapping
	public ResponseEntity<List<ResumoEmprestimoDto>> resumir(EmprestimoFilter emprestimoFilter) {
		List<ResumoEmprestimoDto> resumos = emprestimoService.resumir(emprestimoFilter);
		return ResponseEntity.ok().body(resumos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Emprestimo> buscarPorId(@PathVariable 
			Long id){
		Emprestimo emprestimo = emprestimoService.buscarPorId(id);
		return ResponseEntity.ok().body(emprestimo);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		emprestimoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Emprestimo> atualizar(@PathVariable Long id,
			@Valid @RequestBody Emprestimo emprestimo){
		Emprestimo emprestimoSalva = emprestimoService.atualizar(id,
				emprestimo);
		return ResponseEntity.ok().body(emprestimoSalva);
		
	}
}
