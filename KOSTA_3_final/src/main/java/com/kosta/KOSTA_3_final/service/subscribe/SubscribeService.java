package com.kosta.KOSTA_3_final.service.subscribe;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.KOSTA_3_final.model.product_package.PackageVO;
import com.kosta.KOSTA_3_final.model.subscribe.Subscribe;
import com.kosta.KOSTA_3_final.model.user.Member;
import com.kosta.KOSTA_3_final.persistance.product_package.PackageRepository;
import com.kosta.KOSTA_3_final.persistance.subscribe.SubscribeRepository;
import com.kosta.KOSTA_3_final.persistance.user.MemberRepository;

@Service
public class SubscribeService {

	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	PackageRepository packRepo;
	
	@Autowired
	SubscribeRepository subRepo;
	
	
	
	public PackageVO findById(Long packageId) {
		return packRepo.findById(packageId).get();
	}
	
	public void insertSubscribe(long package_id, long customer_id ){
		PackageVO pack=   packRepo.findById(package_id).get();
		System.out.println(pack);
		Member member = memberRepo.findById(customer_id).get();
		Subscribe sub = Subscribe.builder()
				.subscribeId(new Date().getTime())
				.pack(pack)
				.customer(member)
				.build();
		
		System.out.println(sub+"insert하는 정보");
		subRepo.save(sub);
		
	}

}
