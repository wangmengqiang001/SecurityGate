package com.example.routingandfilteringgateway;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.routingandfilteringgateway.filters.pre.SimpleFilter;

@EnableZuulProxy
@SpringBootApplication
@RestController
public class RoutingAndFilteringGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(RoutingAndFilteringGatewayApplication.class, args);
  }

  @Bean
  public SimpleFilter simpleFilter() {
    return new SimpleFilter();
  }
  
  @GetMapping("/user")
  public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
      return Collections.singletonMap("name", principal.getAttribute("id"));
  }
  
  
  

  
  

}
