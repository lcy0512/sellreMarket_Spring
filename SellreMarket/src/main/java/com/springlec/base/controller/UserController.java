package com.springlec.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springlec.base.model.Inquiry;
import com.springlec.base.model.UserInfo;
import com.springlec.base.service.InquiryService;
import com.springlec.base.service.NoticeService;
import com.springlec.base.service.UserInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	InquiryService inquiryS;
	
	@Autowired
	NoticeService noticeS;
	
	@Autowired
	UserInfoService userS;
	
	// 로그인 Page
	@GetMapping("/login")
	public String loginPage(HttpServletRequest request) {
		return "Login";
	}
	
	// 로그인 시 ID,PW 확인 AJAX
	@PostMapping({"/loginCheck","mypage"})
	public ResponseEntity<String> checkId(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		String userid = request.getParameter("id");
		String password = request.getParameter("password");
		String code = request.getParameter("code");

		String name = userS.checkID(userid, password);
		
		if(code == "mypage") {
			// 내 정보 수정을 통해 들어왔을때,
			// ID,PW를 확인 후 값이 있으면 true, 없으면 false return
			return ResponseEntity.ok(name != "false" ? "true" : "false");
		}
		
		if (name != "false") {
			// 로그인 하였을 때 id와 name의 session 생성
			session.setAttribute("userName", name);
			session.setAttribute("id", userid);
		}
		
		return ResponseEntity.ok(name);
	}
	
	// 회원가입 Page
	@GetMapping("/signup")
	public String signupPage() {
		return "CustomerSignup";
	}
	
	// 문의사항 목록 Page
	@GetMapping("/inquiry")
	public String inquiryPage(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		
		String userid = (String)session.getAttribute("id");
		List<Inquiry> inquiryList = inquiryS.inquiryList(userid);
		
		model.addAttribute("InquiryList", inquiryList);
		
		return "individualInquiry";
	}
	
	// 1:1 문의 Page
	@PostMapping("/inquirywrite")
	public String inquiryWrite(HttpServletRequest request) {
		return "inquirywrite";
	}

	
	// 문의사항 Insert Action
	@PostMapping("/inquiryInsert")
	public String inquiryInsertAction(HttpServletRequest request, @RequestParam (name = "image", required = false) MultipartFile file) throws Exception {
		HttpSession session = request.getSession(); 
		String inimage = null;
		
		if (file != null && !file.isEmpty()) inimage = inquiryS.uploadFile(file);
		
		String intitle = request.getParameter("intitle");
		String incontent = request.getParameter("incontent");
		String userid = (String)session.getAttribute("id");
		String questid = request.getParameter("questid");
		
		inquiryS.inquiryInsert(intitle, incontent, inimage, questid, userid);
		
		return "redirect:/inquiry";
	}
	
	// 문의사항 상세내역 확인 Page
	@PostMapping("/inquirydetail")
	public String inquiryDetailPage(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		
		String inquiryid = request.getParameter("inquiryid");
		String userid = (String)session.getAttribute("id");
		
		model.addAttribute("detailInquiry", inquiryS.inquiryDetail(userid, inquiryid));
		
		return "InquiryDetail";
	}
	
	// 내 정보 수정 재확인 Page
	@GetMapping("/mypageinfo")
	public String mypageinfoPage(HttpServletRequest request) {
		return "mypageinfo";
	}
	
	// 공지사항 Page : /notice를 통해 공지사항 Page 연결 후 AJAX를 통해 /noticelist 실행
	@GetMapping("/notice")
	public String noticePage() {
		return "notice";
	}
	
	// 공지사항 목록 AJAX
	@PostMapping("/noticelist")
	public ResponseEntity<Map<String, Object>> noticelistPage(HttpServletRequest request) throws Exception {
		String keyword = request.getParameter("keyword");
		String curPage = request.getParameter("curPage");
		
		Map<String, Object> responseData = noticeS.noticeList(curPage, keyword);
		
		return ResponseEntity.ok().body(responseData);
	}
	
	// 공지사항 상세내역 Page
	@GetMapping("/noticedetail")
	public String noticeDetailPage(HttpServletRequest request, Model model) throws Exception {
		String eventid = request.getParameter("eventid");
		
		model.addAttribute("detailDto",noticeS.noticeDetail(eventid));
		
		return "noticedetail";
	}
	
	// 이용안내 Page
	@GetMapping("/user_guide")
	public String user_guidePage() throws Exception {
		return "user_guide";
	}
	
	// 아이디 중복체크 AJAX 및 email 중복체크 후 인증번호 발송 
	@PostMapping("/duplicatedCheck")
	public ResponseEntity<Map<String,Object>> duplicatedCheckAction(HttpServletRequest request) throws Exception {
		return ResponseEntity.ok(request.getParameter("userid") != null ? 
				userS.checkDuplicatedId(request.getParameter("userid")) : 
					userS.checkDuplicatedEmail(request.getParameter("email"), request));
	}
	
	// 회원가입 및 개인정보 수정
	@PostMapping("/userInfo")
	public String userInfoAction(HttpServletRequest request) throws Exception {
		String userid = request.getParameter("memberId");
		String nowpassword = request.getParameter("nowpassword");
		String password = request.getParameter("password");
		String tel = request.getParameter("mobileNumber");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		
		// 개인정보 수정 시 password는 nowpassword란 key값으로 전달된다. 
		password = (password == null ? password = nowpassword : password);
		
		String gender = request.getParameter("gender");
		
		gender = (gender.equals("NONE") ? 
				Integer.toString(2) : 
					gender.equals("FEMALE") ? Integer.toString(1) : Integer.toString(0));
		
		String birthYear = request.getParameter("birthYear");
		String birthMonth = request.getParameter("birthMonth");
		String birthDay = request.getParameter("birthDay");
		
		String birthdate = birthYear + "-" + birthMonth + "-" + birthDay;
		
		// 회원가입
		if(userS.checkDuplicatedId(userid).get("result").equals("true")) {
			userS.customerSignUp(userid, password, tel, name, email, address, detailAddress, gender, birthdate);
		}
		// 정보수정
		else {
			userS.updateUserInfo(userid, password, tel, name, email, address, detailAddress, gender, birthdate);
		}
		
		return "redirect:/notice";
	}
	
	// Category Menubar MouseOver 시 보여주는 AJAX
	@GetMapping("/Category")
	public String categoryMenubar() {
		return "Category";
	}
	
	@PostMapping("/mypagedetail")
	public String myPageDetailPage(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		
		String userid = (String)session.getAttribute("id");
		UserInfo user = userS.userDetail(userid);
		model.addAttribute("UserDetail",user);
		
		return "mypagedetail";
	}
	
	@GetMapping("/deleteuser")
	public String deleteUserAction(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("id");
		// status : 0으로 변경
		userS.userDelete(userid);
		// 메인화면으로 수정해야함!
		return "redirect:/notice";
	}
	
	@GetMapping("/logout")
	public String logoutAction(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		// 메인화면으로 수정해야함!
		return "redirect:/notice";
	}

	
}
