package com.kosta.KOSTA_3_final.persistance.board;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.board.Board;
import com.kosta.KOSTA_3_final.model.board.BoardReply;


public interface BoardReplyPersistance extends CrudRepository<BoardReply, Long>{
/*	@Query("select r.rid, b.bid, b.bcontent, b.bregdate, b.btitle, b.bupdatedate, b.customer "
			+ "from Board b left outer join BoardReply r "
			+ "on b.bid = r.bid order by r.rid (desc)")*/
	public List<BoardReply> findByBoard(Board board);
}
