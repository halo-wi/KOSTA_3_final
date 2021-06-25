package com.kosta.KOSTA_3_final.persistance.product_package;


import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.product_package.PackageVO;

public interface PackageRepository extends CrudRepository<PackageVO, Long>{
	
}
