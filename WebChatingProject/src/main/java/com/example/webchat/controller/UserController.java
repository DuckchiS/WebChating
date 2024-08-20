package com.example.webchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webchat.dto.UserDTO;
import com.example.webchat.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/loginProc")
	public String loginProcess(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
	    if (username.equals("사용자 입력 아이디") && password.equals("사용자 입력 비밀번호")) {
	        // 로그인 성공
	        session.setAttribute("username", username);
	        return "redirect:/"; 
	    } else {
	        // 로그인 실패
	        model.addAttribute("error", "아이디나 비밀번호가 잘못되었습니다.");
	        return "user/login"; 
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
