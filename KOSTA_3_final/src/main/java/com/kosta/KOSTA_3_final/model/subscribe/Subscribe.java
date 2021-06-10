package com.kosta.KOSTA_3_final.model.subscribe;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kosta.KOSTA_3_final.model.product_package.PackageVO;
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
@Table(name = "tp_subscribe" )


//구독
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int subscribe_id;
	int package_id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	User customer;
	
	@ManyToOne
	@JoinColumn(name = "package_id", insertable = false, updatable = false)
	PackageVO pack;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "subscribe", cascade = CascadeType.ALL)
	List<Deilvery> delivery;
	
	
	Date payment_date;
	int sub_check;//0 or 1
	
}
