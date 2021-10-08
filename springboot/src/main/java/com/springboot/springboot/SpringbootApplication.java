package com.springboot.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication 
//autoconfigration and component scanning
public class SpringbootApplication {

	public static void main(String[] args) {
	ApplicationContext applicationContext=	SpringApplication.run(SpringbootApplication.class, args);
	
	for(String str:applicationContext.getBeanDefinitionNames()) {
 	}
	
	
	
	}

}
