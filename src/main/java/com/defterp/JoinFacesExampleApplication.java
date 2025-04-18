package com.defterp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;

/**
 * JoinFaces Example Configuration class.
 * @author Marcelo Fernandes
 */
@SpringBootApplication
public class JoinFacesExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(JoinFacesExampleApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean<>(servlet, "*.jsf");
	}
}
