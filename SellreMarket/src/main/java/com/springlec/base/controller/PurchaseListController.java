package com.springlec.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springlec.base.model.PurchaseDto;
import com.springlec.base.service.PurchaseService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PurchaseListController {
	
	@Autowired
	PurchaseService service;
	
	//구매목록 페이지
	@GetMapping("/purchaseListPage")
	public String purchaseListPage(HttpServletRequest request, Model model) throws Exception {	
		
		//	Dao에서 구매내역 불러오기
		List<PurchaseDto> listDao = service.purchaseDao();
		model.addAttribute("purchase", listDao);
		
		
		return "purchase/purchaseListPage";
	}
	
	//구매목록 페이지
	@GetMapping("/purchaseListDetailPage")
	public String purchaseListDetailPage(HttpServletRequest request, Model model) throws Exception {
		
		// 검색어 변수 저장
		String purchaseId = request.getParameter("purchaseId");	
		System.out.println("레알 성공임? : " + purchaseId);
		
		// 검색어를 Dao로 보내서 DB에서 처리 된 값을 List에 저장
		List<PurchaseDto> detailDao = service.detailDao(purchaseId);
		
		System.out.println(detailDao.get(0).getPurchaseDate());
		
		// List를 "purchase" 이름으로 저장
		model.addAttribute("purchase", detailDao);
		
		System.out.println("집 보내줘~ : " + detailDao);
		
		return "purchase/purchaseListDetailPage";
	}
			
	
	// 검색했을 때
	@GetMapping("/purchaseSearch")
	public String purchaseSearch(HttpServletRequest request, Model model) throws Exception {
		
		// 검색어 변수 저장
		String searchText = request.getParameter("searchText");
		
		// 검색어를 Dao로 보내서 DB에서 처리 된 값을 List에 저장
		List<PurchaseDto> searchDao = service.searchInsideDao(searchText);
		List<PurchaseDto> searchOutsideDao = service.searchOutsideDao(searchText);
		
		// List를 "purchase" 이름으로 저장
		model.addAttribute("purchase", searchDao);
		model.addAttribute("outsidePurchase", searchOutsideDao);
		
		return "purchase/purchaseListPage";
	}
}
