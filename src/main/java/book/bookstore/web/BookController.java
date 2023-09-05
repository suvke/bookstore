package book.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
	
	@RequestMapping("index")
	@ResponseBody
	public String showMainPage() {
		return "This is the book store main page";
	}

}
