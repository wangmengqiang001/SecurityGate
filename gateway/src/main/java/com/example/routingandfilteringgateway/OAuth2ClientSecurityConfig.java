package com.example.routingandfilteringgateway;

import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.client.RestTemplate;

@EnableWebSecurity
public class OAuth2ClientSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
	
		
  		http
		  .authorizeRequests()
                  .mvcMatchers("/login").permitAll() // here
                  //.anyRequest().authenticated()
                  .and()
              	    	.oauth2Login()
                    	.loginPage("/login") // and here
                  .and()
		  .headers().frameOptions().disable()
		  .and()
  			.authorizeRequests()
  			.antMatchers("/newbook/**","/user","/login")
  			//.anonymous()
  			.permitAll()
  			.and()  		
			.authorizeRequests(req -> req  				
					
					.anyRequest().authenticated() 

        )
        .oauth2Login(res -> res
        		.tokenEndpoint(tokenEndpoint ->
                tokenEndpoint
                    .accessTokenResponseClient(this.accessTokenResponseClient())                    
            )
        		//.userInfoEndpoint().userService(userService())
        		).oauth2Login(res -> res
        				.userInfoEndpoint(uc -> uc.userService(userService())));
       
  		
	
	}

	private OAuth2UserService<OAuth2UserRequest, OAuth2User> userService() {
		// TODO Auto-generated method stub
		//CASOAuth2UserServices userService = new CASOAuth2UserServices();
		DefaultOAuth2UserService userService= new DefaultOAuth2UserService();
		MappingJackson2HttpMessageConverter convert = new MappingJackson2HttpMessageConverter();
		convert.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM,MediaType.APPLICATION_JSON));
		RestTemplate restTemplate = new RestTemplate(Arrays.asList(
				new FormHttpMessageConverter(),
				convert));

		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
		userService.setRestOperations(restTemplate);
		
		return userService;
	}

	private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
		// TODO Auto-generated method stub
		DefaultAuthorizationCodeTokenResponseClient client = new DefaultAuthorizationCodeTokenResponseClient();
		RestTemplate restTemplate = new RestTemplate(Arrays.asList(
				new FormHttpMessageConverter(),
				new CASOAuth2AccessTokenResponseHttpMessageConverter(),
				new OAuth2AccessTokenResponseHttpMessageConverter()));

		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
	
		client.setRestOperations(restTemplate);
		return client;
	}

}
