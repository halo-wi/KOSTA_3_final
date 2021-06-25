package com.kosta.KOSTA_3_final.model.product_package;

import java.util.Arrays;

public class ProductListVO {
	private int package_id;
	private int package_price;
	private String package_name;
	private int[] productId;
	private int[] product_qty;
	
	
	@Override
	public String toString() {
		return "ProductListVO [package_id=" + package_id + ", package_price=" + package_price + ", package_name=" + package_name
				+ ", product_id=" + Arrays.toString(productId) + ", product_qty=" + Arrays.toString(product_qty) + "]";
	}
	
	public int getPackage_id() {
		return package_id;
	}
	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}
	public int getPackage_price() {
		return package_price;
	}
	public void setPackage_price(int package_price) {
		this.package_price = package_price;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public int[] getProduct_id() {
		return productId;
	}
	public void setProduct_id(int[] product_id) {
		this.productId = product_id;
	}
	public int[] getProduct_qty() {
		return product_qty;
	}
	public void setProduct_qty(int[] product_qty) {
		this.product_qty = product_qty;
	}
	
	
}
