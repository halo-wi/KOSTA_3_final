package com.kosta.KOSTA_3_final;

import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import com.kosta.KOSTA_3_final.model.board.Board;
import com.kosta.KOSTA_3_final.model.board.BoardReply;
import com.kosta.KOSTA_3_final.model.board.PageVO;

import com.kosta.KOSTA_3_final.persistance.board.BoardPersistance;

import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;
@Log
@Commit
@SpringBootTest
class Kosta3FinalApplicationTests {
	
	@Autowired
	BoardPersistance repo;
	

	//@Test
	void contextLoads() {
	}
	
	
	//@Test
		public void conditionRetrieve() {
			Predicate p = repo.makePredicate(null, null);
			//Pageable pageable = PageRequest.of(0, 3);
			PageVO pvo = new PageVO();
			Pageable pageable = pvo.makePaging(0, "bid");
			Page<Board> result = repo.findAll(p, pageable);
			List<Board> boardlist = result.getContent();
			System.out.println("여긴오");
			boardlist.forEach(b->{
				System.out.println(b);
			});
			System.out.println("한페이지의 사이즈"+result.getSize());
			System.out.println("전체페이지"+result.getTotalPages());
		}
	
	
	
	@Transactional
	//@Test
	public void boardReplyCount() {
		repo.findById(690L).ifPresent(b->{
			System.out.println(b.getBreplies().size());
		});
	}
	
	@Transactional
	//@Test
	public void insertReply() {
		repo.findById(690L).ifPresent(b->{
			List<BoardReply> replies = b.getBreplies();
			b.setBtitle("title 수정합니다1.");
			IntStream.range(1, 4).forEach(i->{
				BoardReply wreply = BoardReply.builder()
						.reply("댓글..."+i)
						.board(b)
						.build();
				replies.add(wreply);
			});
			repo.save(b);
		});
	}
	
	
	

}
