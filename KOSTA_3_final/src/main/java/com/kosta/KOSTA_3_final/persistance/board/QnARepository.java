package com.kosta.KOSTA_3_final.persistance.board;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;



import com.kosta.KOSTA_3_final.model.board.QQnA;
import com.kosta.KOSTA_3_final.model.board.QnA;
import com.kosta.KOSTA_3_final.model.user.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface QnARepository extends CrudRepository<QnA, Long>, QuerydslPredicateExecutor<QnA>{

	//JPQL문법
	/*
	 * @Query("select b.qtitle, count(r) " +
	 * "from QnA b left outer join b.qreplies r " +
	 * "group by b.qtitle order by b.qtitle") public List<Object[]>
	 * getBoardReplyCount();
	 */              
		
		public default Predicate makePredicate1() {
			BooleanBuilder builder = new BooleanBuilder();
			QQnA QnA = QQnA.qnA;
			builder.and(QnA.qid.gt(0)); 
			
			return builder;
		}
		public default Predicate makePredicate(String type, String keyword) {
			BooleanBuilder builder = new BooleanBuilder();
			QQnA QnA = QQnA.qnA;
			QUser user = QUser.user;
			builder.and(QnA.qid.gt(0)); 
			
			if(type==null) return builder;
			switch(type) {
			case "qtitle":
				builder.and(QnA.qtitle.like("%"+keyword+"%"));
				break;
			case "qcontent":
				builder.and(QnA.qcontent.like("%"+keyword+"%"));
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
