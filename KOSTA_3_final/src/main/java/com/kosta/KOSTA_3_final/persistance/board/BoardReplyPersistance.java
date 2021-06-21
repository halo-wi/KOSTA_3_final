package com.kosta.KOSTA_3_final.persistance.board;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.board.Board;
import com.kosta.KOSTA_3_final.model.board.BoardReply;


public interface BoardReplyPersistance extends CrudRepository<BoardReply, Long>{
	public List<BoardReply> findByBoard(Board board);
}
