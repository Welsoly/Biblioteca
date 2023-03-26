package uea.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uea.biblioteca.models.Permissao;

@Repository
public interface PermissaoRepository  extends JpaRepository<Permissao, Long> {

}
