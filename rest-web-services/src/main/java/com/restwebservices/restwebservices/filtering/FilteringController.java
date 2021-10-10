package com.restwebservices.restwebservices.filtering;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

 @RestController 
public class FilteringController {
	 @GetMapping("/filtering")
	 public SomeBean retreiveSomeBean() {
		return new SomeBean("val1","val2","val3");
		 
	 }
	 
	 @GetMapping("/filtering-list-static")
	 public List<SomeBean> retreiveListSomeBean() {
		return Arrays.asList(new SomeBean("val1","val2","val3"),new SomeBean("val4","val5","val6"));
		
		 
	 }
	 @GetMapping("/filtering-dynamic")
	 public MappingJacksonValue retreiveListSomeBeanDynamic() {
		 SomeBean someBean= new SomeBean("val1","val2","val3");
		 
		    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
				 	
 
	      FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
		  MappingJacksonValue mapping=new MappingJacksonValue(someBean);
		 mapping.setFilters
		 (filters);
		 
		return  mapping;
			
		 
	 }

}
