package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@GetMapping("/public")
	public String noAuth() {
		return "public";
	}
	
	@GetMapping("/intra/marketing")
	public String marketing() {
		return "marketing";
	}
	
	@GetMapping("/intra/devleop")
	public String develop() {
		return "develop";
	}
	
	@GetMapping("/intra/finance")
	public String finance() {
		return "finance";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}
