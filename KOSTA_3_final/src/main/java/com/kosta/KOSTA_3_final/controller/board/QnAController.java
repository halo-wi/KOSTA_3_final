package com.kosta.KOSTA_3_final.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.KOSTA_3_final.model.board.PageMake;
import com.kosta.KOSTA_3_final.model.board.PageVO;
import com.kosta.KOSTA_3_final.model.board.QnA;
import com.kosta.KOSTA_3_final.service.board.QnAService;

@Controller
public class QnAController {
	@Autowired
	QnAService service;
	
	@GetMapping("/board/qnalist")
	public void selectAll(Model model, PageVO pagevo) {
		Page<QnA> result = service.selectAll(pagevo);
		
		List<QnA> boardlist = result.getContent();
		System.out.println(1);
		boardlist.forEach(b->{
			System.out.println(2);
			System.out.println("asdfsadfsdafajsdflk"+b);
		});
		System.out.println("한페이지의 사이즈"+result.getSize());
		System.out.println("전체페이지"+result.getTotalPages());
		
		model.addAttribute("boardResult", result);
		model.addAttribute("pagevo", pagevo);
		model.addAttribute("result", new PageMake<>(result));
		
	}

}
