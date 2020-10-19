package com.example.routingandfilteringgateway;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

class CASOAuth2TokenTest {

	final String token="access_token=TGT-3-LYXaMlg5f642xzlH5OsikwbEP3lYZSg2zSnzidMPffW6hxAITL-cas01.example.org&expires=7173";
	final String tokenval="TGT-3-LYXaMlg5f642xzlH5OsikwbEP3lYZSg2zSnzidMPffW6hxAITL-cas01.example.org";
	@Test
	void testExtractTokenParams() {
		
		
		CASOAuth2AccessTokenResponseHttpMessageConverter otr = new  CASOAuth2AccessTokenResponseHttpMessageConverter();
		HashMap<String, String> tokenResponseParameters=new HashMap<String, String>();
		
		otr.extractTokenParams(token, tokenResponseParameters);
		System.out.println(tokenResponseParameters);
		
		assertEquals(tokenResponseParameters.get(OAuth2ParameterNames.ACCESS_TOKEN), tokenval);
		assertEquals(tokenResponseParameters.get(OAuth2ParameterNames.EXPIRES_IN),"7173");
		assertEquals(tokenResponseParameters.get(OAuth2ParameterNames.TOKEN_TYPE),"Bearer");
		
		
	}

}
