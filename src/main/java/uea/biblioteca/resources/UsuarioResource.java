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
import uea.biblioteca.models.Usuario;
import uea.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalva = usuarioService.criar(usuario);
		
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(usuarioSalva.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuarioSalva);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<List<Usuario>> listar() {
		List<Usuario> usuarios = usuarioService.listar();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Usuario> buscarPorId(@PathVariable 
			Long id){
		Usuario usuario = usuarioService.buscarPorId(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@DeleteMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		usuarioService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_USUARIO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id,
			@Valid @RequestBody Usuario usuario){
		Usuario usuarioSalva = usuarioService.atualizar(id,
				usuario);
		return ResponseEntity.ok().body(usuarioSalva);
		
	}
}

