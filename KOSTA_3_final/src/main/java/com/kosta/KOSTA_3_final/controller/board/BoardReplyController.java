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
	@GetMapping("/board/{board_id}")
	public ResponseEntity<List<BoardReply>> selectAll(@PathVariable Long board_id) {
		Board board = Board.builder().board_id(board_id).build();
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	//댓글상세보기
		@GetMapping("{board_reply_id}")
		public ResponseEntity<BoardReply> viewReply(@PathVariable Long board_reply_id) {
				
				return new ResponseEntity<>(service.selectById(board_reply_id), HttpStatus.OK);
			}
	
	
	
	//특정 보드에 댓글등록, 입려 후 다시 조회
	@PostMapping("/{board_id}")
	public ResponseEntity<List<BoardReply>> addRep(@PathVariable Long board_id, @RequestBody BoardReply reply) {
		
		Board board = Board.builder().board_id(board_id).build();
		reply.setBoard(board);
		service.updateOrInsert(reply);
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.CREATED);
	}
	
		
	
	
	//삭제
	@DeleteMapping("/{board_id}/{board_reply_id}")
	public ResponseEntity<List<BoardReply>> deleteByboard_reply_id(@PathVariable Long board_reply_id, @PathVariable Long board_id) {
		service.delete(board_reply_id);
		Board board = Board.builder().board_id(board_id).build();
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	//수정
	@PutMapping("/{board_id}")
	public ResponseEntity<List<BoardReply>> updateRep(@PathVariable Long board_id, @RequestBody BoardReply reply) {
		
		Board board = Board.builder().board_id(board_id).build();
		reply.setBoard(board);
		service.updateOrInsert(reply);
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	

}
