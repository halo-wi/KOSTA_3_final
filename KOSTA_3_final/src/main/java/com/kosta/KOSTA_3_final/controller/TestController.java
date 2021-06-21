package com.kosta.KOSTA_3_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.KOSTA_3_final.persistance.subscribe.SubscribeRepository;
import com.kosta.KOSTA_3_final.service.subscribe.SubscribeService;
import com.kosta.KOSTA_3_final.service.user.UserService;



@Controller

public class TestController {
	
	@Autowired
	SubscribeRepository repo;
	@Autowired
	SubscribeService subscribeService;
	@Autowired
	UserService userService;
	

	@CrossOrigin(origins="http://localhost:8888")
	@GetMapping("/payments/subPayment")
	public void subPayment(Model model, int pno) {
		//정기결제
		model.addAttribute("pack", subscribeService.findById(pno));
	}
	


	
	@GetMapping("/payments/packList")
	public String list(Model model) {
		model.addAttribute("plist", repo.findAll());
		
		
		return "/payments/list";
	}
	
	@GetMapping("/shop")
	public String packlist(Model model) {
		model.addAttribute("plist", repo.findAll());
		
		
		return "/shop";
	}
	
	@GetMapping("/payments/packDetail")
	public void selectpackage(Model model, int pno) {
		model.addAttribute("pack", subscribeService.findById(pno));
		model.addAttribute("plist", repo.findAll());
	}
	
	@GetMapping("/payments/userName")
	public void userName() {
		
	}
	@GetMapping("/index")
	public void index() {
		
	}
	
	@GetMapping("/product_details")
	public void product_details() {
		
	}
	
}
