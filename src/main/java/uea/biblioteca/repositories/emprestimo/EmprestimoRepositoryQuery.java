package uea.biblioteca.repositories.emprestimo;

import java.util.List;

import uea.biblioteca.dto.ResumoEmprestimoDto;
import uea.biblioteca.filters.EmprestimoFilter;

public interface EmprestimoRepositoryQuery {
	
	public List<ResumoEmprestimoDto> filtrar(EmprestimoFilter emprestimoFilter);

}
