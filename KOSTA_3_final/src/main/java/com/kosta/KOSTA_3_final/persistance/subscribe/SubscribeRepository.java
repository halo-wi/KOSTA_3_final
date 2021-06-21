package com.kosta.KOSTA_3_final.persistance.subscribe;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.product_package.PackageVO;
import com.kosta.KOSTA_3_final.model.product_package.QPackageVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface SubscribeRepository extends CrudRepository<PackageVO, Integer>, QuerydslPredicateExecutor<PackageVO> {
	
	  public default Predicate makePredicate(String type, String keyword) {
	  BooleanBuilder builder = new BooleanBuilder(); 
	  QPackageVO packages =QPackageVO.packageVO;
	  builder.and(packages.packageId.gt(0));
	  
	  return builder; 
	  }
	 

}
