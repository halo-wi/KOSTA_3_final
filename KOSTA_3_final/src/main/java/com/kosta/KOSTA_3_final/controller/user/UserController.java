package com.kosta.KOSTA_3_final.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.KOSTA_3_final.model.user.Member;
import com.kosta.KOSTA_3_final.security.UserSecurity;
import com.kosta.KOSTA_3_final.service.user.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	JavaMailSender sender;
	
	
	@GetMapping("/login")
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
	public String joinUser2(@ModelAttribute Member user) {
		service.joinUser(user);
		
		return "redirect:/login";
		
	}
	
@GetMapping("/auth/passwordFind")
public void pwdfind() {
	
}

@GetMapping("/mypage/profile")
public String profile(Model model) {
	Object princifal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	UserSecurity ss=(UserSecurity)princifal;
	Member mem=service.getMemberInfo(ss.getUsername());
	model.addAttribute("memberinfo", mem);
	
	return "mypage/profile";
}
@GetMapping("/mypage/edit")
public String profileEdit(Model model) {
	Object princifal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	UserSecurity ss=(UserSecurity)princifal;
	Member mem=service.getMemberInfo(ss.getUsername());
	model.addAttribute("memberinfo", mem);
	
	return "mypage/edit";
}
@PostMapping("/mypage/edit")
public String profileAfterEdit(Model model,Member member) {
	Member mem=service.getMemberInfo(member.getEmail());
	mem.setAddress(member.getAddress());
	mem.setAddressdetail(member.getAddressdetail());
	mem.setCustomerName(member.getCustomerName());
	mem.setPassword(member.getPassword());
	mem.setPhone_number(member.getPhone_number());
	mem.setPostnumber(member.getPostnumber());
	service.joinUser(mem);
	
	Object princifal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	UserSecurity ss=(UserSecurity)princifal;
	Member mem2=service.getMemberInfo(ss.getUsername());
	model.addAttribute("memberinfo", mem2);
	
	return "mypage/profile";
}



}




