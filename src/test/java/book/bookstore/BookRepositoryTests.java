package book.bookstore;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import book.bookstore.domain.Book;
import book.bookstore.domain.BookRepository;
import book.bookstore.domain.CategoryRepository;

@DataJpaTest
public class BookRepositoryTests {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test
	void findAllBooks() {
		Iterable<Book> books = bookRepository.findAll();
		assertThat(books).hasSize(2);
	}

	@Test
	public void saveBook() {
		Book book = new Book("Test", "Test", 2000, "123456", 10, categoryRepository.findByName("Kaunokirjallisuus").get(0));
		bookRepository.save(book);
		assertNotEquals(book.getId(), null);
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = bookRepository.findByTitle("Ohjelmointia");
		Book book = books.get(0);
		bookRepository.delete(book);
		List<Book> newBooks = bookRepository.findByTitle("Ohjelmointia");
		assertThat(newBooks).hasSize(0);
	}
	
	@Test
	public void getNonExistingBook() {
		Optional<Book> book = bookRepository.findById((long) 10);
		assertThat(book).isEmpty();
	}
	
	// tämän testin tulisi feilata, koska tyhjää kirjaa ei pitäisi pystyä tallentamaan
	// tällä hetkellä testi kuitenkin menee läpi, koska tyhjän kirjan tallentamista ei ole rajoitettu
	@Test
	public void tryToSaveEmptyBook() {
		Book book = new Book();
		bookRepository.save(book);
		assertNotEquals(book.getId(), null);
	}
}