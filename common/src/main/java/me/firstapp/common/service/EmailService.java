package me.firstapp.common.service;

public interface EmailService {

	String sendMailCode(String email);

	String getMAilCode(String email);
}
