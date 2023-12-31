package book.bookstore.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import book.bookstore.domain.Book;
import book.bookstore.domain.BookRepository;
import book.bookstore.domain.CategoryRepository;
import jakarta.validation.Valid;

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
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
	
		if (bindingResult.hasErrors()) {
			model.addAttribute("book", book);
			model.addAttribute("categories", crepository.findAll());
			System.out.print("there was an error");
			return "addbook";
		}
		
		bookRepository.save(book);
		return "redirect:booklist";
	} 
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
		return "redirect:../booklist"; 
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("book", bookRepository.findById(id));
    	model.addAttribute("categories", crepository.findAll());
    	return "editbook";
    }
}
