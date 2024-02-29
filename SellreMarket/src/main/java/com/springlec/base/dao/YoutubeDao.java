package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.AdminProductDto;
import com.springlec.base.model.Category;

public interface YoutubeDao {
	
	//카테고리 가져오기
	public List<Category> categoryList() throws Exception;
	
	//중분류 가져오기
	public List<Category> subCategoryList(String type) throws Exception;
	
	//초기 subtype 가져오기
	public List<Category> selectSubCategory() throws Exception;
	 
	//카테고리에 해당하는 제품 가져오기
	public List<AdminProductDto> getProductlist(String type, String subtype) throws Exception;
	
	//youtube insert
	public void insertYoutubeInfo(String yname, String image, String ytitle) throws Exception;
	
	//유튜브 id 가져오
	public int selectYoutubeId(String yname, String image) throws Exception;
	
	//레시피 등록
	public void insertRecipeInfo(int youtubeid, String rcontent) throws Exception;
	
	//레시피아이디 가져오기
	public int selectRecipeId(int youtubeid, String rcontent) throws Exception;
	
	//제품과 레시피 연결
	public void insertProductToRecipe(int recipeid, String productid) throws Exception;
}
