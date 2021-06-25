package com.kosta.KOSTA_3_final.controller.product_package;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kosta.KOSTA_3_final.model.product_package.ProductListVO;
import com.kosta.KOSTA_3_final.service.product_package.ProductPackageService;


@Controller
public class ProductPackageController {

	@Autowired
	ProductPackageService productService;
	
	@GetMapping("/product/customPackaging2")
	public String selectProduct(Model model) {
		model.addAttribute("categorylist", productService.selectCategoryAll());
		model.addAttribute("productlist", productService.selectProductAll());
		
		return "/product/customPackaging2";
	}
	
	@GetMapping("/product/productList")
	public String selectProduct(Model model, String type, String keyword) {
		model.addAttribute("productlist", productService.selectProductAll(type, keyword));
		
		return "/product/productList";
	}
	
	@GetMapping("/product/customInsert")
	public String insertPackage(Model model, @ModelAttribute ProductListVO productList) {
		productService.insertPackage(productList);
		
		return "redirect:/product/customPackaging2";
	}

	

}
