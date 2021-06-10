package com.kosta.KOSTA_3_final.model.board;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kosta.KOSTA_3_final.model.subscribe.Subscribe;
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
@Table(name = "tp_bulletin_board")
public class Board {
	@Id // 필수PK
	@GeneratedValue(strategy = GenerationType.AUTO)
	int board_id;
	String board_title;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	User customer; //댓글작성자
	
	
	String board_content;
	@CreationTimestamp
	Timestamp board_regdate;
	@UpdateTimestamp
	Timestamp board_updatedate;

	@JsonIgnore // tostring과 유사, JSON만들때 무한loop 방지
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<BoardReply> board_replies;

}
