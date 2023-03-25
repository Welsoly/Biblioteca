package uea.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uea.biblioteca.models.Emprestimo;
import uea.biblioteca.repositories.emprestimo.EmprestimoRepositoryQuery;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>, EmprestimoRepositoryQuery{

}
