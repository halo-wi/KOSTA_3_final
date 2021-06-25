package com.kosta.KOSTA_3_final;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.KOSTA_3_final.model.product_package.PackageVO;
import com.kosta.KOSTA_3_final.model.subscribe.Delivery;
import com.kosta.KOSTA_3_final.model.subscribe.Subscribe;
import com.kosta.KOSTA_3_final.model.user.Member;
import com.kosta.KOSTA_3_final.persistance.product_package.PackageRepository;
import com.kosta.KOSTA_3_final.persistance.subscribe.DeliveryRepository;
import com.kosta.KOSTA_3_final.persistance.subscribe.SubscribeRepository;
import com.kosta.KOSTA_3_final.persistance.user.MemberRepository;

@SpringBootTest
public class DeliveryTest {

	@Autowired
	DeliveryRepository deliRepo;
	
	@Autowired
	PackageRepository packRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	SubscribeRepository subRepo;
	
	@Test
	public void test1() {
		
		System.out.println("1");
		PackageVO pack = packRepo.findById(24L).get();
		Member member = memberRepo.findById(42L).get();
		System.out.println("2");
		Delivery delivery = Delivery.builder().
		customer(member).
		pack(pack).
		deliveryDate(getDate()).
		build();
	
		
		deliRepo.save(delivery);
	}
	
	public Date getDate() {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		String format_time1 = format1.format(time.getTime());

		Date date = Date.valueOf(format_time1);
		return date;
	}
		
}

