package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired private MemberService memberService;
	
	@GetMapping("/login") //login.html 호출
	public void login() { //void > [url명.html] 호출 
		System.out.println("login 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied");
	}
	
	@GetMapping("/auth")
	public @ResponseBody ResponseEntity<?> auth(@AuthenticationPrincipal User user) {
		
		if(user == null) {
			return ResponseEntity.ok("로그인 상태가 아닙니다.");
		}
		//바꾸고 나서 로그인하고 다시 /auth 호출하니까 해당 정보를 띄워줌
		return ResponseEntity.ok(user); //user.toString() < 로그인하지 않아서 null인 상태인데 toString해서 에러
	}
	
	@GetMapping("/join")
	public void join() {}
	
	@PostMapping("/join")
	public String joinProc(Member member) { //String > [return명.html] 호출
		if (member.getRole() == null) {
			member.setRole(member.getRole());
		}
		
		member.setRole(Role.ROLE_MEMBER);
		member.setPassword(member.getPassword());
		member.setEnabled(true);
		memberService.save(member);
		return "welcome"; //welcome.html 호출
	}
}
