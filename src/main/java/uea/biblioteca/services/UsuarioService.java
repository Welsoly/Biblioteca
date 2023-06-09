package uea.biblioteca.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import uea.biblioteca.models.Usuario;
import uea.biblioteca.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public Usuario criar(Usuario usuario) {
		usuario.setSenha("{bcrypt}"+encoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow();
		return usuario;
	}
	
	public void excluir(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalva = usuarioRepository.
				findById(id).orElseThrow();
		if(usuario.getSenha()!=null) {
			usuario.setSenha("{bcrypt}"+encoder.encode(usuario.getSenha()));
			BeanUtils.copyProperties(usuario, usuarioSalva, "id");
			return usuarioRepository.save(usuarioSalva);
		}
		BeanUtils.copyProperties(usuario, usuarioSalva, new String[] 
				{"senha", "id"});
		return usuarioRepository.save(usuarioSalva);
	}
	

}
