package uea.biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uea.biblioteca.models.Emprestimo;
import uea.biblioteca.models.Livro;
import uea.biblioteca.models.Usuario;
import uea.biblioteca.repositories.EmprestimoRepository;
import uea.biblioteca.repositories.LivroRepository;
import uea.biblioteca.repositories.UsuarioRepository;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		CadastrarUsuario();
		CadastrarEmprestimo();
		CadastrarLivro();
		}

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private LivroRepository livroRepository;
	
	DateTimeFormatter fdt2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	DateTimeFormatter fdt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	private void CadastrarUsuario() {
		Usuario at1 = new Usuario(null,"Welliton","welliton@gmai.com","321452", null);
		usuarioRepository.save(at1);	
	}
	private void CadastrarLivro() {
		Livro lr1 = new Livro(null,"nav","na",LocalDate.parse("10-08-2010", fdt), null);
		livroRepository.save(lr1);
	}
	private void CadastrarEmprestimo() {
		Emprestimo em1 = new Emprestimo(null,LocalDate.parse("10-08-2010", fdt),LocalDate.parse("10-08-2010", fdt),null,null);
		emprestimoRepository.save(em1);
		
	}
	}
