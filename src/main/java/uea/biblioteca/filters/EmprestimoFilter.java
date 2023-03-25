package uea.biblioteca.filters;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EmprestimoFilter {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataEmprestimo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDevolucao;

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

}
