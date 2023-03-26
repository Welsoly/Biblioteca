package uea.biblioteca.dto;

import java.time.LocalDate;

import uea.biblioteca.models.Livro;
import uea.biblioteca.models.Pessoa;

public class ResumoEmprestimoDto {
	private Long id;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private Livro livro;
	private Pessoa pessoa;

	public ResumoEmprestimoDto() {
		super();
	}

	public ResumoEmprestimoDto(Long id, LocalDate dataEmprestimo, LocalDate dataDevolucao, Livro livro, Pessoa pessoa) {
		super();
		this.id = id;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.livro = livro;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
