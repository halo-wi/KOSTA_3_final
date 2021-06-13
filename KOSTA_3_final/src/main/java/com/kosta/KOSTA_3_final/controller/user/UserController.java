package com.kosta.KOSTA_3_final.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.KOSTA_3_final.model.user.User;
import com.kosta.KOSTA_3_final.service.user.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	JavaMailSender sender;
	

	
	@GetMapping("/auth/login")
	public void login(){
		
	}
	
	@GetMapping("/accessFail")
	public void deniedMethod(){
		
	}
	@GetMapping("/Home")
	public void loginMethod(){
		
	}
	@GetMapping("/logout")
	public void logout(){
		
		
	}
	@GetMapping("/auth/joinForm")
	public void joinUser(){
		
	}
	
	@PostMapping("/auth/joinForm")
	public String joinUser2(@ModelAttribute User user) {
		service.joinUser(user);
		
		return "redirect:/auth/login";
		
	}
	
@GetMapping("/auth/passwordFind")
public void pwdfind() {
	
}


}



