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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import uea.biblioteca.models.Permissao;
import uea.biblioteca.services.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoResource {

	@Autowired
	private PermissaoService permissaoService;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PERMISSAO') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Permissao> criar(@Valid @RequestBody Permissao permissao) {
		Permissao permissaoSalva = permissaoService.criar(permissao);

		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(permissaoSalva.getId()).toUri();

		return ResponseEntity.created(uri).body(permissaoSalva);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERMISSAO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<List<Permissao>> listar() {
		List<Permissao> permissaos = permissaoService.listar();
		return ResponseEntity.ok().body(permissaos);
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERMISSAO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Permissao> buscarPorId(@PathVariable 
			Long id){
		Permissao permissao = permissaoService.buscarPorId(id);
		return ResponseEntity.ok().body(permissao);
	}

	@DeleteMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PERMISSAO') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		permissaoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_PERMISSAO') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Permissao> atualizar(@PathVariable Long id,
			@Valid @RequestBody Permissao permissao){
		Permissao permissaoSalva = permissaoService.atualizar(id,
				permissao);
		return ResponseEntity.ok().body(permissaoSalva);

	}

}