package com.kosta.KOSTA_3_final.persistance.product_package;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kosta.KOSTA_3_final.model.product_package.Product;
import com.kosta.KOSTA_3_final.model.product_package.Product_Package;

public interface ProductPackageRepository extends CrudRepository<Product_Package, Integer>{
	List<Product_Package> findByProduct(Product product);

	@Query(value ="select b.price from tp_productpack a left outer join tp_product b on(b.product_id = a.product_id) where package_id =:pid", nativeQuery = true)
	public List<Integer> findbyPackageId(@Param("pid") Long long1);


}
