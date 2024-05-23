package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping({"/", "/index"})
	public String index() { //[return명.html] 호출
		System.out.println("index 요청");
		return "index"; //index.html 호출
	}
	
	@GetMapping("/member") //member.html 호출
	public void member() { //[url명.html] 호출 
		System.out.println("Member 요청");
	}
	
	@GetMapping("/manager")
	public void manager() { 
		System.out.println("Manager 요청");
	}
	
	@GetMapping("/admin")
	public void admin() { 
		System.out.println("Admin 요청");
	}
}
