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
@Table(name = "tp_question_board")
//문의게시판VO
public class QnA {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int question_id;
	
	String question_title;
	String question_content;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	Member customer; // 문의글 작성자
	
	@CreationTimestamp
	Timestamp question_reg_date;

	@UpdateTimestamp
	Timestamp question_update_date;

}
