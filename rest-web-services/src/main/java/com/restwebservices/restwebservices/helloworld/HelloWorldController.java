package com.restwebservices.restwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	 @Autowired
	private MessageSource messageSource;
	
	@GetMapping("/hello-world")
	public String helloWorld() {	
		return "Hello World";
		 
	}
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {	
		return new HelloWorldBean("Hello World Bean"); 
	}
	
	@GetMapping(path="/hello-world/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {	
		return new HelloWorldBean("hello world "+ name); 
	}
	
	@GetMapping("/hello-world-int")
	public String helloWorldInternalized(
			@RequestHeader(name="Accept-Language",required=false) 
			Locale locale
			)
			 {	
		return  messageSource.
				getMessage("good.morning.message",null,"default",
						//locale
						LocaleContextHolder.getLocale()
						);
		 
	}
}
