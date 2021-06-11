package com.kosta.KOSTA_3_final.persistance.board;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.board.Board;
import com.kosta.KOSTA_3_final.model.board.QBoard;
import com.kosta.KOSTA_3_final.model.user.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;


public interface BoardPersistance extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board>{

//	//JPQL문법
	@Query("select b.board_title, count(r) "
			+ "from Board b left outer join b.board_replies r "
			+ "group by b.board_title order by b.board_title")
	public List<Object[]> getBoardReplyCount();                 
	
	public default Predicate makePredicate1() {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard board = QBoard.board;
		builder.and(board.board_id.gt(0)); // and board_id>0
		
		return builder;
	}
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard board = QBoard.board;
		QUser user = QUser.user;
		builder.and(board.board_id.gt(0)); // and board_id>0
		
		if(type==null) return builder;
		switch(type) {
		case "board_title":
			builder.and(board.board_title.like("%"+keyword+"%"));
			break;
		case "board_content":
			builder.and(board.board_content.like("%"+keyword+"%"));
			break;
		case "email":
			builder.and(user.email.like("%"+keyword+"%"));
			break;
		default:
			break;
		}
		return builder;
	}

}
