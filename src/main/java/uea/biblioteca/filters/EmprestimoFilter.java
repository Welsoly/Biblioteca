package uea.biblioteca.filters;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EmprestimoFilter {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataEmprestimoDe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataEmprestimoAte;
	
	
	public LocalDate getDataEmprestimoDe() {
		return dataEmprestimoDe;
	}
	public void setDataEmprestimoDe(LocalDate dataEmprestimoDe) {
		this.dataEmprestimoDe = dataEmprestimoDe;
	}
	public LocalDate getDataEmprestimoAte() {
		return dataEmprestimoAte;
	}
	public void setDataEmprestimoAte(LocalDate dataEmprestimoAte) {
		this.dataEmprestimoAte = dataEmprestimoAte;
	}

	
}
