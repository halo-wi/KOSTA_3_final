package com.kosta.KOSTA_3_final.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kosta.KOSTA_3_final.model.subscribe.GetTokenVO;
import com.kosta.KOSTA_3_final.persistance.product_package.PackageRepository;
import com.kosta.KOSTA_3_final.persistance.subscribe.SubscribeRepository;
import com.kosta.KOSTA_3_final.service.product_package.ProductPackageService;
import com.kosta.KOSTA_3_final.service.subscribe.GetPayementStatus;
import com.kosta.KOSTA_3_final.service.subscribe.ImportPay;
import com.kosta.KOSTA_3_final.service.subscribe.ReqPaymentScheduler;
import com.kosta.KOSTA_3_final.service.subscribe.RequestSubPayment;
import com.kosta.KOSTA_3_final.service.subscribe.SchedulePayment;
import com.kosta.KOSTA_3_final.service.subscribe.SubscribeService;
import com.kosta.KOSTA_3_final.service.user.UserService;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class TestController {

	@Setter(onMethod_ = @Autowired)
	private ImportPay pay;

	@Autowired
	RequestSubPayment reqPay;

	@Autowired
	ProductPackageService packService;
	@Autowired
	PackageRepository packRepo;

	@Autowired
	SubscribeRepository repo;
	@Autowired
	SubscribeService subscribeService;
	@Autowired
	UserService userService;
	@Autowired
	SchedulePayment setSchedulePay;
	@Autowired
	GetPayementStatus getPayementStatus;
	@Autowired
	ReqPaymentScheduler scheduler;
	
	/*
	 * @GetMapping("/payments/subPayment") public void subPayment(Model model, Long
	 * pno) { // 정기결제 model.addAttribute("pack", subscribeService.findById(pno)); }
	 */

	// 재결제 요청
	@PostMapping("/payment")
	public @ResponseBody void getImportToken(@RequestParam Map<String, Object> map)
			throws JsonMappingException, JsonProcessingException {
		System.out.println("결제요청+스케줄");

		int customer_uid = Integer.parseInt((String) map.get("customer_uid"));
		int price = Integer.parseInt((String) map.get("price"));
		long merchant_uid =  Long.parseLong((String) map.get("merchant_uid"));     //parseLong 배움
		log.info("requestPayment post-----------------");
		log.info(getPayementStatus.paymentStatus(merchant_uid));
		if(getPayementStatus.paymentStatus(merchant_uid).equals("paid")) {
			scheduler.startScheduler(customer_uid,price);
		
		}
		
		
		log.info(setSchedulePay.schedulePay(customer_uid, price));
		
		System.out.println(merchant_uid + " : merchant_uid");

	}

	@GetMapping("/payment")
	public void payment(Model model, Long pno) {
		model.addAttribute("pack", subscribeService.findById(pno));
	}

	@GetMapping("/shop")
	public void packlist(Model model) {
		model.addAttribute("plist", packRepo.findAll());

	}

	@GetMapping("/index")
	public void index() {

	}

	@GetMapping("/product_details")
	public void product_details(Model model, Long pno) {
		model.addAttribute("pack", subscribeService.findById(pno));
		model.addAttribute("plist", packRepo.findAll());
	}

	@Autowired
	SubscribeService subService;

	@GetMapping("/insertSubscribe")
	@ResponseBody
	public String insertSubscribe(long package_id, int customer_id) {

		System.out.println(package_id);
		System.out.println(customer_id);
		System.out.println("입력시도");
		subService.insertSubscribe(package_id, customer_id);
		System.out.println("입력성공");
		return "입력성공";
	}

	/*
	 * @GetMapping("/getStatus")
	 * 
	 * @ResponseBody public void getStatus() { try {
	 * log.info(getPayementStatus.paymentStatus()); } catch (JsonMappingException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

}
