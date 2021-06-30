package com.kosta.KOSTA_3_final.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.KOSTA_3_final.logic.subscribe.GetDate;
import com.kosta.KOSTA_3_final.model.board.PageMake;
import com.kosta.KOSTA_3_final.model.subscribe.Delivery;
import com.kosta.KOSTA_3_final.model.subscribe.PageVO;
import com.kosta.KOSTA_3_final.service.subscribe.DeliveryService;

@Controller
public class DeliveryController {
	
	@Autowired
	DeliveryService deliService;
	
	@Autowired
	GetDate date;
	
//	@GetMapping("/delivery/deliveryList")
//	public String selectDeliveryList(Model model) {
//		
//		model.addAttribute("deliverylist", deliService.selectDeliveryList(date.getDate()));
//		return "/delivery/deliveryList";
//	}
//	
//	@GetMapping("/delivery/deliveryListSearch")
//	public String selectDeliveryList1(Model model, Date date) {
//		model.addAttribute("deliverylist", deliService.selectDeliveryList(date));
//		
//		return "/delivery/deliveryListSearch";
//	}
	
	@GetMapping("/delivery/deliveryCalender")
	public String getCalender() {
				
		return "/delivery/deliveryCalender";
	}
	
	@GetMapping("/delivery/deliveryList")
	public void selectAll(Model model, PageVO pagevo) {
	
		pagevo = PageVO.builder().page(1).size(5).date(date.getDate()).build();
		System.out.println("controller...pagevo:" + pagevo);
		System.out.println("controller...getDate:" + pagevo.getDate());
		Page<Delivery> result = deliService.selectDeliveryList1(pagevo);
		
		
		model.addAttribute("deliverylist", result);
		model.addAttribute("pagevo", pagevo);
		model.addAttribute("result", new PageMake<>(result));
		
	}
	
	@GetMapping("/delivery/deliveryListSearch")
	public String selectAll1(Model model, PageVO pagevo) {
		
		pagevo = PageVO.builder().page(pagevo.getPage()).size(5).date(pagevo.getDate()).build();
		System.out.println("controller...pagevo:" + pagevo.getPage());
		System.out.println("controller...getDate:" + pagevo.getDate());
		Page<Delivery> result = deliService.selectDeliveryList1(pagevo);
		
		model.addAttribute("deliverylist", result);
		model.addAttribute("pagevo", pagevo);
		model.addAttribute("result", new PageMake<>(result));
		
		return "/delivery/deliveryListSearch";
	}
}
