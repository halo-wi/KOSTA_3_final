package com.kosta.KOSTA_3_final.service.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.KOSTA_3_final.model.product_package.PackageVO;
import com.kosta.KOSTA_3_final.persistance.subscribe.SubscribeRepository;

@Service
public class SubscribeService {
	@Autowired
	SubscribeRepository subRepo;
	
	public PackageVO findById(int packageId) {
		return subRepo.findById(packageId).get();
	}
}
