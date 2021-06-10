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

import com.kosta.KOSTA_3_final.model.user.User;

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
@Table(name = "tp_bulletin_board_reply")

//자유게시판_댓글VO
public class BoardReply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int board_reply_id;

	String board_reply; //댓글내용
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	User customer; // FK, 댓글 작성자. issue : User에서도 양방향으로 참조해야할까? <본인댓글조회 기능 시 필요> , 변수명에 대해 의논

	@CreationTimestamp
	Timestamp reg_date;

	@UpdateTimestamp
	Timestamp update_date;

	// 여러개의 댓글은 하나의 게시글을 참조한다.
	@ManyToOne
	@JoinColumn(name = "board_id")
	Board board; // FK

}
