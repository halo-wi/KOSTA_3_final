package com.kosta.KOSTA_3_final.persistance.board;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;



import com.kosta.KOSTA_3_final.model.board.QQnA;
import com.kosta.KOSTA_3_final.model.board.QnA;
import com.kosta.KOSTA_3_final.model.user.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface QnARepository extends CrudRepository<QnA, Long>, QuerydslPredicateExecutor<QnA>{

	
		public default Predicate makePredicate(String type, String keyword) {
			BooleanBuilder builder = new BooleanBuilder();
			QQnA QnA = QQnA.qnA;
			QMember user = QMember.member;
			
			
			if(type==null) return builder;
			builder.and(QnA.qid.gt(0)); 
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
