package uea.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uea.biblioteca.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
