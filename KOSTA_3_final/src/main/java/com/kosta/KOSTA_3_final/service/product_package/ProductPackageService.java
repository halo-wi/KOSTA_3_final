package com.kosta.KOSTA_3_final.service.product_package;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;


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
	
	@Autowired
	InsertImg insertImg;
	
	int price_result = 0;
	
	public List<Product> selectProductAll() {
		return (List<Product>) productRepo.findAll();
	}
	
	public List<Company> selectCompanyAll() {
		return (List<Company>) companyRepo.findAll();
	}
	
	public List<Product> selectProductAll(String type, String keyword) {
		System.out.println(type);
		System.out.println(keyword);
		Predicate p = productRepo.makePredicate(type, keyword);
		return (List<Product>) productRepo.findAll(p);
	}
	
	public List<Category> selectCategoryAll() {
		return (List<Category>) categoryRepo.findAll();
	}
	
	public PackageVO insertPackage(ProductListVO productList) {
		
		String pack_img = "";
		
		if(productList.getPackageType() == 0) {
			// ???????????? ?????????
			insertImg.insertImg(productList);	
			pack_img = productList.getFiles()[0].getOriginalFilename();
		}
		
		// ?????? ???????????? ?????????
		PackageVO packageVO = PackageVO.builder().
				packageName(productList.getPackageName()). // ????????? login??? ????????? ??????+"package" ??? ??????
				price(productList.getPackagePrice()).
				packageType(productList.getPackageType()).
				img(pack_img).
				build();

		PackageVO newPackage = packageRepo.save(packageVO);
		System.out.println(newPackage+"newPackage!!!!!!!!!!!!!!!!!");
		// ?????? ???????????? ?????????
		
		 
		IntStream.range(0, productList.getProductId().length).forEach(idx->{
			
			Product p = productRepo.findById(productList.getProductId()[idx]).get();
			PackageVO packageVO1 = packageRepo.findById(packageVO.getPackageId()).get();
			
			Product_Package productPackage = Product_Package.builder().
					product_qty(productList.getProductQty()[idx]).
					product(p).
					pack(packageVO1).
					build();
			
			productPackageRepo.save(productPackage);
			System.out.println("insert ??????" + idx);
		});
		return newPackage;
	}
	
	public void adminInsertProduct(ProductListVO productList) {
		// ???????????? ?????????
		insertImg.insertImg(productList);
	    
		IntStream.range(0, productList.getProductName().length).forEach(idx->{
						
			Product product = Product.builder().
					productName(productList.getProductName()[idx]).
					price(productList.getProductPrice()[idx]).
					categoryId(productList.getCategoryId()[idx]).
					companyId(productList.getCompanyId()[idx]).
					build();
			
			productRepo.save(product);
			System.out.println("insert ??????" + idx);
		});	
	}
	
	public void adminDeleteProduct(ProductListVO productList) {
		System.out.println("ddd");  
		System.out.println(productList.getProductId()[0]);  
		// ????????? ????????? product_id
		Product product = productRepo.findById(productList.getProductId()[0]).get();
		System.out.println("product : "+product);

		// ????????????_????????? ??????????????? package_id??? ????????? row??? ????????? ??? ??????
		List<Product_Package> product_pack_list = productPackageRepo.findByProduct(product);
		System.out.println("product_pack_list : "+product_pack_list);
		// ????????????_????????? ??????????????? package_id??? ????????? ??? ??????. ???????????? set?????? ??????
		HashSet<Long> package_id_list = new HashSet<>();
		
		for(Product_Package product_pack:product_pack_list) {
			// set??? package_id??????
			package_id_list.add(product_pack.getPack().getPackageId());
			
			// ????????????_????????? ??????????????? ?????? ????????? ?????? ?????? ??????
			productPackageRepo.deleteById(product_pack.getPpId());
		}
		System.out.println("package_id_list : "+package_id_list);
		// ???????????? ??????????????? ????????? ??????????????? ??????
		productRepo.delete(product);

		// ????????????_????????? ??????????????? ????????? package_id??? ????????? ??? ????????? ????????? price??? ??????
		Iterator<Long> it = package_id_list.iterator();
		
		while(it.hasNext()){
			
			// package_id??? ??? ?????? ??????
			long package_id = it.next();
			
			for(Integer product_price:productPackageRepo.findbyPackageId(package_id)) {
				System.out.println(product_price);
				price_result += product_price;
				
			}
			packageRepo.findById(package_id).ifPresent(b->{
				// ????????? ???????????? ?????? ????????? ???????????? ????????????.
				
				// ????????? ????????? ???????????? ??????
				b.setPrice(price_result);
				
				// ????????? ????????? ????????? ????????? ???????????? ???????????? ??????????????????.
				packageRepo.save(b);
			});
			
			// ????????? ????????? ????????? ???????????? ????????? ?????? update??? ?????? reset?????????.
			price_result = 0;
		}
	}
	
	public PackageVO selectById(Long packageId) {
		return packageRepo.findById(packageId).get();
	}
	   
	   
	public List<String> findProductbyPackageNo(Long pno){
		return productPackageRepo.findProductbyPackageNo(pno);
	}
}