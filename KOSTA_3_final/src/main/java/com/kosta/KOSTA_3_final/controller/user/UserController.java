package com.kosta.KOSTA_3_final.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.KOSTA_3_final.model.user.EmailDTO;
import com.kosta.KOSTA_3_final.model.user.User;
import com.kosta.KOSTA_3_final.service.user.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	JavaMailSender sender;
	
	@PostMapping("/email")
	public Map<String, Object> mail(String mail, HttpSession session) {
		//이메일 인증
		Map<String, Object> map = new HashMap<>();
		Random random = new Random();
		String key = "";

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail); // 스크립트에서 보낸 메일을 받을 사용자 이메일 주소
		// 입력 키를 위한 코드
		for (int i = 0; i < 3; i++) {
			int index = random.nextInt(25) + 65; // A~Z까지 랜덤 알파벳 생성
			key += (char) index;
		}
		int numIndex = random.nextInt(8999) + 1000; // 4자리 정수를 생성
		key += numIndex;
		message.setSubject("인증번호 입력을 위한 메일 전송");
		message.setText("인증 번호 : " + key);
		sender.send(message);
		map.put("key", key);
		return map;
		
	}
	
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
	public String joinUser2(User user) {
		service.joinUser(user);
		
		return "redirect:/auth/login";
		
	}
	
@GetMapping("/auth/passwordFind")
public void pwdfind() {
	
}

@PostMapping("/auth/passwordFind")
public @ResponseBody Map<String, Boolean> pw_find(String email, String customer_name){
    Map<String,Boolean> json = new HashMap<>();
    boolean pwFindCheck = service.userEmailCheck(email, customer_name);

    System.out.println(pwFindCheck);
    json.put("check", pwFindCheck);
    return json;
}

@PostMapping("/auth/passwordFind/sendmail")
	public @ResponseBody void sendEmail(String userEmail, String userName){
	 EmailDTO dto = service.createMailAndChangePassword(userEmail, userName);
     
     SimpleMailMessage msg = new SimpleMailMessage();
     msg.setSubject(dto.getTitle());
		msg.setText(dto.getMessage());
		msg.setTo(userEmail);
	 service.sendEmail(msg);
       
}
}



