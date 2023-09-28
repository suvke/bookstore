package book.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import book.bookstore.domain.AppUser;
import book.bookstore.domain.AppUserRepository;
import book.bookstore.domain.Book;
import book.bookstore.domain.BookRepository;
import book.bookstore.domain.Category;
import book.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository, AppUserRepository urepository) {
	return (args) -> {
		
		crepository.save(new Category("Tietokirjallisuus"));
		crepository.save(new Category("Kaunokirjallisuus"));
		
		brepository.save(new Book("Ohjelmointia", "Olli Ohjelmoija", 2020, "1234-A4B6", 25, crepository.findByName("Tietokirjallisuus").get(0)));
		brepository.save(new Book("Java-ohjelmointi", "Olga Ohjelmoija", 2019, "9876-ABCD", 15, crepository.findByName("Tietokirjallisuus").get(0)));
		
		AppUser user1 = new AppUser("user", "$2a$10$JKJTrFnQj0rt6q.iES.BDu/55YZ1mem9oSEkd/a6S4C2u4j1ybJN6", "user.user@email.com", "USER");
		AppUser user2 = new AppUser("admin", "$2a$10$UhIUkEuExmCh/onM725jOuk/Dy2sip43kFmlBsZ315BB4wLJVcf1u", "admin.admin@email.com", "ADMIN");
		
		urepository.save(user1);
		urepository.save(user2);
		
		for (Book book : brepository.findAll()) {
			log.info(book.toString());
		}
	};
	}

}
