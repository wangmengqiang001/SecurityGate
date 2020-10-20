package com.example.routingandfilteringgateway;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class CASOAuth2UserInfoTest {

	final String responseBody="{\"id\":\"admin\",\"attributes\":[{\"uid\":\"uid\"},{\"eduPersonAffiliation\":\"eduPersonAffiliation\"},{\"groupMembership\":\"groupMembership\"}]}";
	
	@Test
	void testExtractEntity() {
		CASOAuth2UserInfoResponseHttpMessageConverter cvt= new CASOAuth2UserInfoResponseHttpMessageConverter();
		ResponseEntity<Map<String, Object>> usrInfo = cvt.extractEntity(responseBody);
		
		
		assertNotNull(usrInfo);
		assertNotNull(usrInfo.getBody());
		assertNotNull(usrInfo.getBody().get("id"));
		assertEquals("admin",usrInfo.getBody().get("id"));
		
		System.out.println(usrInfo.toString());
	}

}
