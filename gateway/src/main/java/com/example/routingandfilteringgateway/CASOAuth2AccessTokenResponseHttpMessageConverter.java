package com.example.routingandfilteringgateway;



import java.io.BufferedInputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.util.StreamUtils;

public class CASOAuth2AccessTokenResponseHttpMessageConverter extends OAuth2AccessTokenResponseHttpMessageConverter {

	public CASOAuth2AccessTokenResponseHttpMessageConverter() {
		super();
		// TODO Auto-generated constructor stub
	    //this.getSupportedMediaTypes().clear();
	    this.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
	}

	@Override
	protected OAuth2AccessTokenResponse readInternal(Class<? extends OAuth2AccessTokenResponse> clazz,
			HttpInputMessage inputMessage) throws HttpMessageNotReadableException {
		//return super.readInternal(clazz, inputMessage);
		try {
			@SuppressWarnings("unchecked")
			HashMap<String, String> tokenResponseParameters = new HashMap<String, String>();			
		
			
			String tokenResponse = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.ISO_8859_1);
			

			extractTokenParams(tokenResponse, tokenResponseParameters);
			
			return this.tokenResponseConverter.convert(tokenResponseParameters);
		} catch (Exception ex) {
			throw new HttpMessageNotReadableException("An error occurred reading the OAuth 2.0 Access Token Response: " +
					ex.getMessage(), ex, inputMessage);
		}
	}

	/**
	 * 
	 * @param response
	 * @param tokenResponseParameters
	 */
	protected void extractTokenParams(String response, HashMap<String, String> tokenResponseParameters) {
		System.out.println("Response Text: "+ response);
		final String tokenKey="access_token=";
		final String expiresKey="&expires=";
		int ntoken = response.indexOf(tokenKey);
		
		int nstate = response.indexOf(expiresKey);
		if(ntoken >=0 && nstate >=0) {
			String token = response.substring(ntoken+tokenKey.length(), nstate);
			String expire = response.substring(nstate+expiresKey.length());
			tokenResponseParameters.put(OAuth2ParameterNames.ACCESS_TOKEN, token);
			tokenResponseParameters.put(OAuth2ParameterNames.EXPIRES_IN, expire);
			tokenResponseParameters.put(OAuth2ParameterNames.TOKEN_TYPE, "Bearer");
									
		}
	}
	
	

	
}
