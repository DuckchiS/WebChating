package com.example.webchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webchat.dto.UserDTO;
import com.example.webchat.entity.UserEntity;
import com.example.webchat.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
    @Autowired 
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/loginProc")
	public String loginProcess(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
	    UserEntity user = userService.findByUsername(username);  // UserEntity 사용

	    // 비밀번호 매칭 여부 확인
	    if (user != null && bCryptPasswordEncoder.matches(password, user.getUserPassword())) {
	        // 로그인 성공 시 사용자 정보를 세션에 저장
	        session.setAttribute("loggedInUser", user);  // 세션에 UserEntity 객체 저장
	        return "redirect:/";  // 홈으로 리다이렉트
	    } else {
	        // 로그인 실패 시 에러 메시지 전달
	        model.addAttribute("error", "아이디나 비밀번호가 잘못되었습니다.");
	        return "user/login";  // 로그인 페이지로 다시 이동
	    }
	}
	
	@GetMapping("/signup")
	public String signUp() {
		return "user/signup";
	}
	
	@PostMapping("/signupProc")
	public String signupProc(UserDTO user) {
		userService.signUpProcess(user);
		return "redirect:/user/login";
	}
}
