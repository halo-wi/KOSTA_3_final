package com.kosta.KOSTA_3_final.model.board;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.kosta.KOSTA_3_final.model.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tp_question_board_reply")
//문의게시판_댓글VO
public class QnAReply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int question_reply_id;
	
	String question_reply;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	QnA question_id;
	
	
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	Member admin;
	
	
	@CreationTimestamp
	Timestamp question_reply_reg_date;

	@UpdateTimestamp
	Timestamp question_reply_update_date;
}
