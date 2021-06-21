package com.kosta.KOSTA_3_final;


import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.KOSTA_3_final.persistance.product_package.CategoryRepository;
import com.kosta.KOSTA_3_final.persistance.product_package.ProductRepository;

@SpringBootTest
public class PackageTest {
	
	@Autowired
	ProductRepository productRepo;

	@Autowired
	CategoryRepository categoryRepo;
	
	//@Test
	@Transactional
	public void test1() {
		productRepo.findAll().forEach(i -> {
			System.out.println(i);
		});
	}
	
	//@Test
	@Transactional
	public void test2() {
		categoryRepo.findAll().forEach(i -> {
			System.out.println(i);
		});
	}
}
