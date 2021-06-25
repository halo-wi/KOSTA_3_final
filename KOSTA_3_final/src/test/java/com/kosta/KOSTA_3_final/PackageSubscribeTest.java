package com.kosta.KOSTA_3_final;



import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;

import com.kosta.KOSTA_3_final.model.product_package.PackageVO;
import com.kosta.KOSTA_3_final.persistance.subscribe.SubscribeRepository;

import lombok.extern.java.Log;

@SpringBootTest
@Log
@Commit
public class PackageSubscribeTest {
	@Autowired
	SubscribeRepository repo;
	
	@Test
	@Transactional
	public void testList1() {
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC,"packageId");
		Page<PackageVO> result = repo.findAll(repo.makePredicate(null, null),pageable);
		
		log.info("page: "+result.getPageable());
		log.info("----------------------------------");
		result.getContent().forEach(packages->log.info(""+packages));
		
				
	}
	


}
