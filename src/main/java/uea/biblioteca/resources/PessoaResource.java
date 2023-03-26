package uea.biblioteca.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import uea.biblioteca.models.Pessoa;
import uea.biblioteca.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.criar(pessoa);
		
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(pessoaSalva.getId()).toUri();
		
		return ResponseEntity.created(uri).body(pessoaSalva);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoas = pessoaService.listar();
		return ResponseEntity.ok().body(pessoas);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable 
			Long id){
		Pessoa pessoa = pessoaService.buscarPorId(id);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@DeleteMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		pessoaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping(value="/{id}/ativo")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PESSOA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Pessoa> atualizarPropriedadeAtivo(
			@PathVariable Long id, @RequestBody Boolean ativo){
		Pessoa pessoaSalva = pessoaService.
				atualizarPropriedadeAtivo(id, ativo);
		return ResponseEntity.ok(pessoaSalva);
	}
	@PutMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PESSOA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id,
			@Valid @RequestBody Pessoa pessoa){
		Pessoa pessoaSalva = pessoaService.atualizar(id,
				pessoa);
		return ResponseEntity.ok().body(pessoaSalva);
		
	}
}
