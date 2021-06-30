package com.kosta.KOSTA_3_final.service.subscribe;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.KOSTA_3_final.model.subscribe.Delivery;
import com.kosta.KOSTA_3_final.model.subscribe.PageVO;
import com.kosta.KOSTA_3_final.persistance.subscribe.DeliveryRepository;
import com.querydsl.core.types.Predicate;

@Service
public class DeliveryService {
	
	@Autowired
	DeliveryRepository deliRepo;
	
	public List<Delivery> selectDeliveryList(Date date) {
		System.out.println(date);
		Predicate p = deliRepo.makePredicate(date);
		return (List<Delivery>) deliRepo.findAll(p);
	}
	
	public Page<Delivery> selectDeliveryList1(PageVO pvo) {

		Predicate p = deliRepo.makePredicate(pvo.getDate());
		
		Pageable pageable = pvo.makePaging(0, "deliveryId");
		Page<Delivery> result = deliRepo.findAll(p, pageable);
		
		return result;
	}
}
