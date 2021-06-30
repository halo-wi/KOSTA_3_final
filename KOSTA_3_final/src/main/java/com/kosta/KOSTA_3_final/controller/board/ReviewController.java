package com.kosta.KOSTA_3_final.controller.board;

import java.security.Principal;
import java.util.List;

import org.hibernate.internal.CriteriaImpl.Subcriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.KOSTA_3_final.model.board.PageMake;
import com.kosta.KOSTA_3_final.model.board.PageVO;
import com.kosta.KOSTA_3_final.model.board.Review;
import com.kosta.KOSTA_3_final.model.product_package.PackageVO;
import com.kosta.KOSTA_3_final.model.subscribe.Subscribe;
import com.kosta.KOSTA_3_final.model.user.Member;
import com.kosta.KOSTA_3_final.service.board.ReviewService;
import com.kosta.KOSTA_3_final.service.product_package.ProductPackageService;
import com.kosta.KOSTA_3_final.service.user.UserService;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService service;
	@Autowired
	UserService uservice;
	@Autowired
	ProductPackageService pservice;
	
	@GetMapping("/board/reviewlist")
	public void selectAll(Model model, PageVO pagevo) {
		if(pagevo==null) pagevo = PageVO.builder().page(1).size(10).keyword("").type("").build();

		Page<Review> result = service.selectAll(pagevo);
		
		
	 
		model.addAttribute("reviewResult", result);
		model.addAttribute("pagevo", pagevo);
		model.addAttribute("result", new PageMake<>(result));
	}
	
	
	@GetMapping("/board/reviewregister")
	public void boardRegister(Model model, Principal principal) {
		String email = principal.getName();
		List<Subscribe> subscribes = uservice.getMemberInfo(email).getSubscribes();
		model.addAttribute("subscribes", subscribes);
	}

	
	
	
	@PostMapping("/board/reviewregister")
	public String reviewRegisterPost( Review review, RedirectAttributes rttr, Principal principal) {
		Member member = uservice.getMemberInfo(principal.getName());
		review.setCustomer(member);
		Review ins_re = service.insertReview(review);
		//주소창에 보이지 않고 전달된다.
		rttr.addFlashAttribute("resultMessage", ins_re==null?"입력실패":"입력성공");
		return "redirect:/board/reviewlist";
	}

	  @GetMapping("/board/reviewdelete")
	  public String reviewDelete(Long reviewId) {
		  return "redirect:/board/reviewlist";
				 
	  }

}
