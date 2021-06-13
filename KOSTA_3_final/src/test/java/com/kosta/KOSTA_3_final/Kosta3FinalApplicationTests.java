package com.kosta.KOSTA_3_final;

import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.kosta.KOSTA_3_final.model.board.BoardReply;
import com.kosta.KOSTA_3_final.persistance.board.BoardPersistance;

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
	
	
	@Transactional
	@Test
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
