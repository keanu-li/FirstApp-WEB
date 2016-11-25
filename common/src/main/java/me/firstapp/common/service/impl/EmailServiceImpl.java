package me.firstapp.common.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import me.firstapp.common.service.EmailService;

@Component
public class EmailServiceImpl implements EmailService {

	@CachePut(value = "emailCode", key = "#email")
	public String sendMailCode(String email) {
		String code = "1234";
		return code;
	}

	@Cacheable(value = "emailCode", key = "#email")
	public String getMAilCode(String email) {
		return null;
	}

}
