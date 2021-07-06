package com.kosta.KOSTA_3_final.service.product_package;

import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.KOSTA_3_final.model.product_package.Category;
import com.kosta.KOSTA_3_final.model.product_package.Company;
import com.kosta.KOSTA_3_final.model.product_package.PackageVO;
import com.kosta.KOSTA_3_final.model.product_package.Product;
import com.kosta.KOSTA_3_final.model.product_package.ProductListVO;
import com.kosta.KOSTA_3_final.model.product_package.Product_Package;
import com.kosta.KOSTA_3_final.persistance.product_package.CategoryRepository;
import com.kosta.KOSTA_3_final.persistance.product_package.CompanyRepository;
import com.kosta.KOSTA_3_final.persistance.product_package.PackageRepository;
import com.kosta.KOSTA_3_final.persistance.product_package.ProductPackageRepository;
import com.kosta.KOSTA_3_final.persistance.product_package.ProductRepository;
import com.querydsl.core.types.Predicate;



@Service
public class ProductPackageService {
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	PackageRepository packageRepo;
	
	@Autowired
	ProductPackageRepository productPackageRepo;

	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	CompanyRepository companyRepo;
	
	public PackageVO selectById(Long packageId) {
		return packageRepo.findById(packageId).get();
	}
	
	
	public List<Product> selectProductAll() {
		return (List<Product>) productRepo.findAll();
	}
	
	public List<Company> selectCompanyAll() {
		return (List<Company>) companyRepo.findAll();
	}
	
	public List<Product> selectProductAll(String type, String keyword) {
		Predicate p = productRepo.makePredicate(type, keyword);
		return (List<Product>) productRepo.findAll(p);
	}
	
	public List<Category> selectCategoryAll() {
		return (List<Category>) categoryRepo.findAll();
	}
	
	public List<String> findProductbyPackageNo(Long pno){
		return productPackageRepo.findProductbyPackageNo(pno);
	}
	

	public PackageVO insertPackage(ProductListVO productList) {
		// 따로 클래스로 만들자
		PackageVO packageVO = PackageVO.builder().
				packageName("남후승package"). // 이름도 login한 사람의 이름+"package" 로 사용
				price(productList.getPackagePrice()).
				packageType(1).
				build();

		PackageVO newPackage = packageRepo.save(packageVO);
		IntStream.range(0, productList.getProductId().length).forEach(idx->{
			Product p = productRepo.findById(productList.getProductId()[idx]).get();
			PackageVO packageVO1 = packageRepo.findById(packageVO.getPackageId()).get();
			Product_Package productPackage = Product_Package.builder().
					product_qty(productList.getProductQty()[idx]).
					product(p).
					pack(packageVO1).
					build();
			productPackageRepo.save(productPackage);
		});
		return newPackage;
	}
	
	public void adminInsertProduct(ProductListVO productList) {
		IntStream.range(0, productList.getProductName().length).forEach(idx->{
			Product product = Product.builder().
					productName(productList.getProductName()[idx]).
					price(productList.getProductPrice()[idx]).
					categoryId(productList.getCategoryId()[idx]).
					companyId(productList.getCompanyId()[idx]).
					build();
			
			productRepo.save(product);

		});	
	}
	
}