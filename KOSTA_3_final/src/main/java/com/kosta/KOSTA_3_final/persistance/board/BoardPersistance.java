package com.kosta.KOSTA_3_final.persistance.board;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.board.Board;
import com.kosta.KOSTA_3_final.model.board.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;


public interface BoardPersistance extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board>{

 
	
//JPQL문법
	/*@Query("select b.btitle, count(r) "
			+ "from Board b left outer join b.breplies r "
			+ "group by b.btitle order by b.btitle")
	public List<Object[]> getBoardReplyCount();                 
	
	public default Predicate makePredicate1() {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard board = QBoard.board;
		builder.and(board.bid.gt(0)); // and board_id>0
		
		return builder;
	}
	*/public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard board = QBoard.board;
		 
		 
		if(type==null) return builder;
		builder.and(board.bid.gt(0L));// and board_id>0
		String whereStr = "%"+keyword+"%";
		switch(type) {
		case "btitle":
			builder.and(board.btitle.like(whereStr));
			break;
		case "bcontent":
			builder.and(board.bcontent.like(whereStr));
			break;
		case "email":
			builder.and(board.customer.email.like(whereStr));
			break;
		default:
			break;
		}
		 
		return builder;
	}

}
