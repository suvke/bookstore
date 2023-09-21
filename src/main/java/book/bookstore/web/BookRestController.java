package book.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import book.bookstore.domain.Book;
import book.bookstore.domain.BookRepository;
import book.bookstore.domain.CategoryRepository;

@RestController
public class BookRestController {
	
	@Autowired
	private BookRepository bRepository;
	
	@Autowired
	private CategoryRepository cRepository;
	
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<Book> bookListRest() {
		return (List<Book>) bRepository.findAll(); 
	}
	
	@RequestMapping(value = "/book/{id}", method= RequestMethod.GET)
	public Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return bRepository.findById(id); 
	}

} 
