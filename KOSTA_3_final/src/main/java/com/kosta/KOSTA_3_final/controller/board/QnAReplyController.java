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

import com.kosta.KOSTA_3_final.model.board.QnA;
import com.kosta.KOSTA_3_final.model.board.QnAReply;
import com.kosta.KOSTA_3_final.service.board.QnAReplyService;

@RestController
@RequestMapping("/replies/*")
public class QnAReplyController {
	
	@Autowired
	QnAReplyService service;
	
	@GetMapping("/QnA/{qid}")
	public ResponseEntity<List<QnAReply>> selectAll(@PathVariable Long qid) {
		QnA qnA = QnA.builder().qid(qid).build();
		return new ResponseEntity<>(service.selectAll(qid), HttpStatus.OK);
	}
	
	//댓글상세보기
		@GetMapping("{qid}")
		public ResponseEntity<QnAReply> viewReply(@PathVariable Long qid) {
				
				return new ResponseEntity<>(service.selectById(qid), HttpStatus.OK);
			}
	
	
	
	//특정 보드에 댓글등록, 입려 후 다시 조회
	@PostMapping("/{qid}")
	public ResponseEntity<List<QnAReply>> addRep(@PathVariable Long qid, @RequestBody QnAReply reply) {
		
		QnA qnA = QnA.builder().qid(qid).build();
		reply.setQid(qnA);
		service.updateOrInsert(reply);
		return new ResponseEntity<>(service.selectAll(qid), HttpStatus.CREATED);
	}
	
		
	
	
	//삭제
	@DeleteMapping("/{qid}/{qrid}")
	public ResponseEntity<List<QnAReply>> deleteByqrid(@PathVariable Long qrid, @PathVariable Long qid) {
		service.delete(qrid);
		QnA qnA = QnA.builder().qid(qid).build();
		
		return new ResponseEntity<>(service.selectAll(qid), HttpStatus.OK);
	}
	
	//수정
	@PutMapping("/{qid}")
	public ResponseEntity<List<QnAReply>> updateRep(@PathVariable Long qid, @RequestBody QnAReply reply) {
		
		QnA qnA = QnA.builder().qid(qid).build();
		reply.setQid(qnA);
		service.updateOrInsert(reply);
		
		return new ResponseEntity<>(service.selectAll(qid), HttpStatus.OK);
	}
	
}
