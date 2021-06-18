package com.kosta.KOSTA_3_final.model.product_package;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kosta.KOSTA_3_final.model.board.Review;
import com.kosta.KOSTA_3_final.model.subscribe.Subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//패키지
@Getter
@Setter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tp_package")
public class PackageVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "package_id")
	int packageid;
	@Column(name = "package_name")
	String packagename;
	int price;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pack", cascade = CascadeType.ALL) //, fetch = FetchType.EAGER
	List<Review> reviews;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pack", cascade = CascadeType.ALL) //, fetch = FetchType.EAGER
	List<Subscribe> subscribes;
	
	
	@OneToMany(mappedBy = "pack")
	List<Product_Package> ppList;
}
