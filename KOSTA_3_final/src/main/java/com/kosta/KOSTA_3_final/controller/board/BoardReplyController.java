package com.kosta.KOSTA_3_final.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.KOSTA_3_final.model.board.Board;
import com.kosta.KOSTA_3_final.model.board.BoardReply;
import com.kosta.KOSTA_3_final.service.board.BoardReplyService;


@RestController
@RequestMapping("/replies/*")
public class BoardReplyController {

	
	@Autowired
	BoardReplyService service;
	
	//특정 보드 번호에 해당하는 모든 댓글 조회
	@GetMapping("/board/{bid}")
	public ResponseEntity<List<BoardReply>> selectAll(@PathVariable Long bid) {
		Board board = Board.builder().bid(bid).build();
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	//댓글상세보기
		@GetMapping("{rid}")
		public ResponseEntity<BoardReply> viewReply(@PathVariable Long rid) {
				
				return new ResponseEntity<>(service.selectById(rid), HttpStatus.OK);
			}
	
	
	
	//특정 보드에 댓글등록, 입려 후 다시 조회
	@PostMapping("/{bid}")
	public ResponseEntity<List<BoardReply>> addRep(@PathVariable Long bid, @RequestBody BoardReply reply) {
		
		Board board = Board.builder().bid(bid).build();
		reply.setBoard(board);
		service.updateOrInsert(reply);
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.CREATED);
	}
	
		
	
	
	//삭제
	@DeleteMapping("/{bid}/{rid}")
	public ResponseEntity<List<BoardReply>> deleteByboard_reply_id(@PathVariable Long rid, @PathVariable Long bid) {
		service.delete(rid);
		Board board = Board.builder().bid(bid).build();
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	//수정
	@PutMapping("/{bid}")
	public ResponseEntity<List<BoardReply>> updateRep(@PathVariable Long bid, @RequestBody BoardReply reply) {
		
		Board board = Board.builder().bid(bid).build();
		reply.setBoard(board);
		service.updateOrInsert(reply);
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	

}
