package com.kosta.KOSTA_3_final.model.product_package;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@ToString(exclude = {"category","company","ppList"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tp_product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	int productId;
	@Column(name = "product_name")
	String productName;
	int price;
	@Column(name = "category_id")
	int categoryId;
	@Column(name = "company_id")
	int companyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", insertable = false, updatable = false)
	Company company; 
	
	@JsonIgnore
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	List<Product_Package> ppList;
	
	
}
