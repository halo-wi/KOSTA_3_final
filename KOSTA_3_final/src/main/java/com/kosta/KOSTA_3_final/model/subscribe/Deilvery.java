package com.kosta.KOSTA_3_final.model.subscribe;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="tp_delivery")



//배송
public class Deilvery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int delivery_id;
	
	@ManyToOne
	@JoinColumn(name="subscribe_id")
	Subscribe subscribe;
	
	
	Date delivery_date;
	
}
