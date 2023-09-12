package book.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import book.bookstore.domain.BookRepository;

@Controller
public class BookController {
@Autowired
private BookRepository bookRepository;
	
	@GetMapping(value= {"/", "/booklist"})
	public String bookList(Model model) {
	model.addAttribute("books", bookRepository.findAll());
	return "booklist";
	}
}
