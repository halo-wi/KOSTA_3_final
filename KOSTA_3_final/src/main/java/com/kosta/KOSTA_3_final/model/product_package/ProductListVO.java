package com.kosta.KOSTA_3_final.model.product_package;

import java.util.Arrays;

public class ProductListVO {
	private int packageId;
	private int packagePrice;
	private String packageName;
	private int[] productId;
	private int[] productQty;
	
	
	@Override
	public String toString() {
		return "ProductListVO [package_id=" + packageId + ", package_price=" + packagePrice + ", package_name=" + packageName
				+ ", product_id=" + Arrays.toString(productId) + ", product_qty=" + Arrays.toString(productQty) + "]";
	}
	
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int package_id) {
		this.packageId = package_id;
	}
	public int getPackagePrice() {
		return packagePrice;
	}
	public void setPackagePrice(int package_price) {
		this.packagePrice = package_price;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String package_name) {
		this.packageName = package_name;
	}
	public int[] getProductId() {
		return productId;
	}
	public void setProductId(int[] product_id) {
		this.productId = product_id;
	}
	public int[] getProductQty() {
		return productQty;
	}
	public void setProductQty(int[] product_qty) {
		this.productQty = product_qty;
	}
	
	
}