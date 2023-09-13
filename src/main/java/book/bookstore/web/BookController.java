package book.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import book.bookstore.domain.Book;
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
	
	@RequestMapping(value = "/add")
	public String addBook(Model model){
	model.addAttribute("book", new Book());
	return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
	bookRepository.save(book);
	return "redirect:booklist";
	} 
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Long id) {
	bookRepository.deleteById(id);
	return "redirect:../booklist"; 
	}
}
