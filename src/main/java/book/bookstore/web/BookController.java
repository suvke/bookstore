package book.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import book.bookstore.domain.Book;
import book.bookstore.domain.BookRepository;
import book.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
@Autowired
private BookRepository bookRepository;

@Autowired
private CategoryRepository crepository;
	
	@GetMapping(value= {"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	/* RESTful service to get all books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepository.findAll(); 
	}
	*/
	
	/*RESTful service to get one book by id
	@RequestMapping(value = "/book/{id}", method= RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return bookRepository.findById(id); 
	}
	*/
	
	@RequestMapping(value = "/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
	
		bookRepository.save(book);
		return "redirect:booklist";
	} 
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
		return "redirect:../booklist"; 
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("book", bookRepository.findById(id));
    	model.addAttribute("categories", crepository.findAll());
    	return "editbook";
    }
}
