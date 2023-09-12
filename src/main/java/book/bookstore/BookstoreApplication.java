package book.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import book.bookstore.domain.Book;
import book.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
	return (args) -> {
		repository.save(new Book("Ohjelmointia", "Olli Ohjelmoija", 2020, "1234-A4B6", 25));
		repository.save(new Book("Java-ohjelmointi", "Olga Ohjelmoija", 2019, "9876-ABCD", 15));
	};
	}

}
