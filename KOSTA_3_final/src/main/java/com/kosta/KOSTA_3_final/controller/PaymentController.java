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
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.kosta.KOSTA_3_final.model.product_package.PackageVO;
import com.kosta.KOSTA_3_final.model.product_package.ProductListVO;
import com.kosta.KOSTA_3_final.model.subscribe.GetTokenVO;
import com.kosta.KOSTA_3_final.persistance.product_package.PackageRepository;
import com.kosta.KOSTA_3_final.persistance.subscribe.SubscribeRepository;
import com.kosta.KOSTA_3_final.service.product_package.PackageId;
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
public class PaymentController {
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
	@Autowired
	PackageId packageId;
	@Autowired
	RequestSubPayment reqSubpay;

	@PostMapping("/payment1")
	public @ResponseBody void getImportToken(@RequestParam Map<String, Object> map)
			throws JsonMappingException, JsonProcessingException {
		int customer_uid = Integer.parseInt((String) map.get("customer_uid"));
		int price = Integer.parseInt((String) map.get("price"));
		long merchant_uid =  Long.parseLong((String) map.get("merchant_uid"));  
		reqSubpay.requestSubPay(customer_uid,merchant_uid, price);
		scheduler.startScheduler(customer_uid,price);

		}
	
	
	
	
	 @GetMapping("/payment") public void payment(Model model, Long pno) {
	  model.addAttribute("pack", subscribeService.findById(pno));
	 }
	 
	 @PostMapping("/payment") 
	 public String payment (Model model, @ModelAttribute
	 ProductListVO productList) {
	 //productList.setPackageId(Long.parseLong(packageId.getPackageId()));
	  PackageVO  newPackageVO =packService.insertPackage(productList);
	 // model.addAttribute("pack", productList);
	 return "redirect:/payment?pno="+newPackageVO.getPackageId();
	 }
		 
	@GetMapping("/shop")
	public void packlist(Model model) {
		model.addAttribute("plist", packRepo.findByPackageType(0));
	}

	@GetMapping("/index")
	public void index(Model model) {
		model.addAttribute("plist", packRepo.findByPackageType2(0));	
	}

	@GetMapping("/product_details")
	public void product_details(Model model, Long pno) {
		model.addAttribute("pack", subscribeService.findById(pno));
		model.addAttribute("plist", packRepo.findAll());
		model.addAttribute("productList", packService.findProductbyPackageNo(pno));
	}
	
	

	@Autowired
	SubscribeService subService;

	@GetMapping("/insertSubscribe")
	@ResponseBody
	public void insertSubscribe(long package_id, int customer_id) {
		subService.insertSubscribe(package_id, customer_id);

		log.info("구독정보 입력성공");
	}


}
