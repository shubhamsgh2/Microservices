package com.springboot.springboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
	
	@GetMapping("/books")      //hardcoded books returned
	public List<Book> getAllBooks(){
		return Arrays.asList(
				new Book(1L," test","abc")
				);
		
		
	}

}
