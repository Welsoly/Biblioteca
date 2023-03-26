package uea.biblioteca.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.biblioteca.models.Permissao;
import uea.biblioteca.repositories.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public Permissao criar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}

	public List<Permissao> listar(){
		return permissaoRepository.findAll();
	}

	public Permissao buscarPorId(Long id) {
		Permissao permissao = permissaoRepository.findById(id).orElseThrow();
		return permissao;
	}

	public void excluir(Long id) {
		permissaoRepository.deleteById(id);
	}

	public Permissao atualizar(Long id, Permissao permissao) {
		Permissao permissaoSalva = permissaoRepository.
				findById(id).orElseThrow();
		BeanUtils.copyProperties(permissao, permissaoSalva, "id");
		return permissaoRepository.save(permissaoSalva);
	}


}