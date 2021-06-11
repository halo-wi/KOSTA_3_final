package com.kosta.KOSTA_3_final.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.KOSTA_3_final.model.board.Board;
import com.kosta.KOSTA_3_final.model.board.PageMake;
import com.kosta.KOSTA_3_final.model.board.PageVO;
import com.kosta.KOSTA_3_final.service.board.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	@GetMapping("/board/boardlist")
	public void selectAll(Model model, PageVO pagevo) {
		Page<Board> result = service.selectAll(pagevo);
		
		List<Board> boardlist = result.getContent();
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
	
	@GetMapping("/board/register")
	public void boardRegister() {
		
	}
	
	@PostMapping("/board/register")
	public String boardRegisterPost(Board board, RedirectAttributes rttr) {
		System.out.println(board);
		
		Board ins_board = service.insertBoard(board);
		//주소창에 보이지 않고 전달된다.
		rttr.addFlashAttribute("resultMessage", ins_board==null?"입력실패":"입력성공");
		return "redirect:/board/boardlist";
	}

	  @GetMapping("/board/boarddetail") 
	  public void selectAll(Model model, Long board_id, PageVO pagevo) { 
		  model.addAttribute("board",service.selectById(board_id)); 
		  model.addAttribute("pagevo", pagevo);
		  }

	  @GetMapping("/board/delete")
	  public String boardDelete(Long board_id) {
		  int ret = service.deleteBoard(board_id);
		  System.out.println("삭제 : "+ret);
		  return "redirect:/board/boardlist";
				 
	  }
	
	  @PostMapping("/board/update")
	  public String boardUpdate(Board board, RedirectAttributes rttr, Integer page, Integer size,
			  String type, String keyword) {
		  Board update_board= service.updateBoard(board);
		  System.out.println("수정사항 : "+update_board);
		  
		  rttr.addFlashAttribute("resultMessage", update_board==null?"수정실패":"수정성공");
		  
		  
		  String param = "page=" + page + "&size=" + size + "&type="+type+"&keyword="+keyword;
		  return "redirect:/board/boardlist?" + param;
				 
	  }

}
