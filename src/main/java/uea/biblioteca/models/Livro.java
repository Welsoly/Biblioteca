package uea.biblioteca.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private String editora;
	private LocalDate anoPublicacao;
	
	

	public Livro() {
		super();
	}

	public Livro(Long id, String titulo, String editora, LocalDate anoPublicacao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.editora = editora;
		this.anoPublicacao = anoPublicacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public LocalDate getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(LocalDate anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}

}
