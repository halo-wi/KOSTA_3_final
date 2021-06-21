package com.kosta.KOSTA_3_final.model.product_package;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//상품_패키지 연결VO
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tp_productpack")
public class Product_Package {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int ppId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id")
	PackageVO pack;
	
	
	int product_qty;
}
