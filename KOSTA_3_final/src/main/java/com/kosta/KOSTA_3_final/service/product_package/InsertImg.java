package com.kosta.KOSTA_3_final.service.product_package;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Component;

import com.kosta.KOSTA_3_final.model.product_package.ProductListVO;

@Component
public class InsertImg {
	public void insertImg(ProductListVO productList) {
		if(productList.getProductName() == null) {
			String[] length = {"0"};
			productList.setProductName(length);
		}
		System.out.println("여긴오남??");
		IntStream.range(0, productList.getProductName().length).forEach(idx->{
			String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
		    String basePath = rootPath;
		    String filePath = basePath + "/git/final_project_subscribe/final_project_subscribe/src/main/resources/static/images/product_images/" + productList.getFiles()[idx].getOriginalFilename();
		    
		    File dest = new File(filePath);
		    try {
		    	
				productList.getFiles()[idx].transferTo(dest);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
}
