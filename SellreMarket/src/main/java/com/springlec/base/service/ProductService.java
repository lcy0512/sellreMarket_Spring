package com.springlec.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springlec.base.model.Product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ProductService {
	// new Product
	public List<Product> productView(HttpServletRequest request, String id, int curPag, String headerCategory, String alignCategory) throws Exception;
//	public List<Product> newProductView(int limitFrom, int countPerPage, String id, int curPage) throws Exception;
//	// new Product info align asc
//	public List<Product> newProductAlignAscView(int limitFrom, int countPerPage, String id, int curPage) throws Exception;
//	// new Product info align desc
//	public List<Product> newProductAlignDescView(int limitFrom, int countPerPage, String id, int curPage) throws Exception;
	public String newAdImg() throws Exception;
	public List<Product> bestAdImgs() throws Exception;
	
	// main Best Product info 
//	public List<Product> bestProductView(HttpServletRequest request, int limitFrom, int countPerPage, String id, int curPage, String alignCategory) throws Exception;
	// Best Product info align asc
//	public List<Product> bestProductAlignAscView(int limitFrom, int countPerPage, String id, int curPage) throws Exception;
//	// Best Product info align desc
//	public List<Product> bestProductAlignDescView(int limitFrom, int countPerPage, String id, int curPage) throws Exception;
	
	// Recipe Product
	// main Recipe Product info 
//	public List<Product> recipeProductView(HttpServletRequest request, int limitFrom, int countPerPage, String id, int curPage, String alignCategory) throws Exception;
	// Recipe Product info align asc
//	public List<Product> recipeProductAlignAscView(int limitFrom, int countPerPage, String id, int curPage) throws Exception;
//	// Recipe Product info align desc
//	public List<Product> recipeProductAlignDescView(int limitFrom, int countPerPage, String id, int curPage) throws Exception;
	
	
	// product page count 
//	public void productPageCount(HttpServletRequest request, HttpServletResponse response, String headerCategory, String alignCategory, String id, int curPage) throws Exception;
	public Map<String, Object> productPageCount(HttpServletRequest request, HttpServletResponse response, String headerCategory, String alignCategory, String id, int curPage) throws Exception;
//	// best product page count 
//	public int bestProductPageCount() throws Exception;
//	// recipe product page count 
//	public int recipeProductPageCount() throws Exception;
	
	public int cartCount(String id) throws Exception;
	
}