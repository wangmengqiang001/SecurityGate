package com.example.routingandfilteringgateway;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CASOAuth2UserInfoResponseHttpMessageConverter extends AbstractGenericHttpMessageConverter<ResponseEntity<Map<String, Object>>> {

	private static final int String = 0;

	public CASOAuth2UserInfoResponseHttpMessageConverter() {
		//super(MediaType.APPLICATION_OCTET_STREAM);
		this.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		//this.CASOAuth2UserInfoResponseHttpMessageConverter(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		
		
	}



	public CASOAuth2UserInfoResponseHttpMessageConverter(MediaType... supportedMediaTypes) {
		this.setSupportedMediaTypes(Arrays.asList(supportedMediaTypes));
		// TODO Auto-generated constructor stub
	}

	

	@Override
	protected boolean supports(Class<?> clazz) {
		
		//return ResponseEntity.class == clazz;
		return clazz != null;
	}

	@Override
	protected ResponseEntity<Map<String, Object>> readInternal(
			Class<? extends ResponseEntity<Map<String, Object>>> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		String userInfo = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
		ResponseEntity<Map<String, Object>>  entity=extractEntity(userInfo);
		
		return entity;
	}

	protected ResponseEntity<Map<String, Object>> extractEntity(String userInfo) {
		// TODO Auto-generated method stub
		System.out.println("userInfo: "+ userInfo);
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, Object> mapEntity=null; // = new HashMap<String, Object>();
		try {
			mapEntity  = objectMapper.readValue(userInfo, Map.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<Map<String, Object>> entity = new ResponseEntity<Map<String, Object>>(mapEntity,HttpStatus.OK);
		//mapEntity.put("id", "admin");
		return entity;
	}

	@Override
	protected void writeInternal(ResponseEntity<Map<String, Object>> t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResponseEntity<Map<java.lang.String, Object>> read(Type type, Class<?> contextClass,
			HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	protected void writeInternal(ResponseEntity<Map<java.lang.String, Object>> t, Type type,
			HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}

	

}
