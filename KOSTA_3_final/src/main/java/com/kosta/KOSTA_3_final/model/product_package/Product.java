package com.kosta.KOSTA_3_final.model.product_package;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//상품
@Getter
@Setter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tp_product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int product_id;
	String product_name;
	int price;
	int category_id;
	int company_id;
	
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	Category category; 

	@ManyToOne
	@JoinColumn(name = "company_id", insertable = false, updatable = false)
	Company company; 
	
	@OneToMany(mappedBy = "product")
	List<Product_Package> ppList;
	
	
}
