package com.example.routingandfilteringgateway;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

public class CASOAuth2UserInfoResponseHttpMessageConverter extends AbstractHttpMessageConverter<ResponseEntity<Map<String, Object>> > {

	public CASOAuth2UserInfoResponseHttpMessageConverter() {
		super();
		this.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		// TODO Auto-generated constructor stub
	}

	public CASOAuth2UserInfoResponseHttpMessageConverter(Charset defaultCharset, MediaType... supportedMediaTypes) {
		super(defaultCharset, supportedMediaTypes);
		// TODO Auto-generated constructor stub
	}

	public CASOAuth2UserInfoResponseHttpMessageConverter(MediaType... supportedMediaTypes) {
		super(supportedMediaTypes);
		// TODO Auto-generated constructor stub
	}

	public CASOAuth2UserInfoResponseHttpMessageConverter(MediaType supportedMediaType) {
		super(supportedMediaType);
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
		return null;
	}

	@Override
	protected void writeInternal(ResponseEntity<Map<String, Object>> t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}

	

}
