package com.example.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/list")
	public String list() {
		return "board/list";
	}
	
	@GetMapping("/read")
	public String read() {
		return "";
	}
	
	@GetMapping("/write")
	public String write() {
		return "";
	}
	
	@PostMapping("/writeProc")
	public String writeProc() {
		return "";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "";
	}
}
