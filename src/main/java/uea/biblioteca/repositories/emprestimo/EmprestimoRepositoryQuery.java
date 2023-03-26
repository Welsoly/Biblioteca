package uea.biblioteca.repositories.emprestimo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uea.biblioteca.dto.ResumoEmprestimoDto;
import uea.biblioteca.filters.EmprestimoFilter;

public interface EmprestimoRepositoryQuery {
	
	public Page<ResumoEmprestimoDto> filtrar(EmprestimoFilter emprestimoFilter, Pageable pageable);

}
