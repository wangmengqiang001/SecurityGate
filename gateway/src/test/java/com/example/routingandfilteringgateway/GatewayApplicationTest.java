package com.example.routingandfilteringgateway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.zuul.context.RequestContext;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {RoutingAndFilteringGatewayApplication.class
		})
//,OAuth2ClientSecurityConfigLocal.class
public class GatewayApplicationTest {

    @Autowired
    private TestRestTemplate rest;
    
   

    static ConfigurableApplicationContext bookService;

    @BeforeAll
    public static void startBookService() {
        bookService = SpringApplication.run(BookService.class,
                "--server.port=8071");
        
        
    }

    @AfterAll
    public static void closeBookService() {
        bookService.close();
    }

    @BeforeEach
    public void setup() {
        RequestContext.testSetCurrentContext(new RequestContext());
    }

    @Test
    public void test() {
        String resp = rest.getForObject("/books/available", String.class);
       // rest.g
        assertThat(resp).isEqualTo("books");
    }
    @Test
    public void testX() {
        String resp = rest.getForObject("/books/xvailable", String.class);
       // rest.g
       // assertThat(resp).isEqualTo("books");
        assertThat(resp).containsIgnoringCase("Access Denied");
    }
    
    @Test
    public void testXAuth() {
        String resp = rest.getForObject("/books/authavailable", String.class);
       // rest.g
        assertThat(resp).isEqualTo("auth_books");
        //assertThat(resp).containsIgnoringCase("Access Denied");
    }

    @Configuration
    @EnableAutoConfiguration  
    @RestController
     static class BookService {
        @RequestMapping("/available")
        public String getAvailable() {
            return "books";
        }
        @RequestMapping("/xvailable")
        public String getXAvailable() {
            return "xbooks";
        }
        @RequestMapping("/authavailable")
        public String getAuthAvailable() {
            return "auth_books";
        }
        @Bean 
        WebSecurityConfigurerAdapter configSecurity() {
        	return new OAuth2ClientSecurityConfigLocal();
        }
        
        
    }
    @EnableWebSecurity
    @Order(value=90)
    static class  OAuth2ClientSecurityConfigLocal extends WebSecurityConfigurerAdapter {

    	@Override
    	protected void configure(HttpSecurity http) throws Exception {
    		super.configure(http);
    		//if super is called, exception that antMatchers cannot follow anyRequest  occurs
      	
      		
      		http
      		.authorizeRequests()
      		
      		//.antMatchers("/available").anonymous()
      		//.antMatchers("/authavailable").anonymous()
      		.antMatchers("/*available").anonymous()
      		//The order is fatal, it is the priority of permission
      		.antMatchers("/**").denyAll();
      		
      		
    	
    	}
    	
    	
    }
   
    
    
   
}
