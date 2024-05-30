package edu.pnu.board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
	
	@GetMapping("/auth")
	public @ResponseBody User auth(@AuthenticationPrincipal User user) {
		return user;
	}
	
	@GetMapping("/system/login")
	public void login() {
		
	}
	
	@GetMapping("/system/accessDenied")
	public void accessDenied() {
		
	}
	
	@GetMapping("/system/logout")
	public void logout() {
		
	}
	
	@GetMapping("/admin/adminPage")
	public void admin() {
		
	}
}
