package edu.pnu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;
    
    @PostMapping("/BeMember")
    public ResponseEntity<?> BeMember(@RequestBody Member member) { // 회원가입
    	// System.out.println("멤버" + member);
        boolean isSuccess = memberService.successMember(member);
        
        if (!isSuccess) {
            return ResponseEntity.badRequest().body("중복된 아이디입니다.");
        } else {
            return ResponseEntity.ok("가입 완료되었습니다.");
        }
    }
    
    @PostMapping("/user/find/id") // ID 찾기
    public ResponseEntity<Map<String, String>> findId(@RequestBody Member member) { // ? > Object > String으로 결정
    	String result = memberService.findId(member);
    	
    	Map<String, String> response = new HashMap<>();
    	
    	if (result.equals("존재하지 않는 회원입니다.")) {
    		response.put("message", result);
    		return ResponseEntity.badRequest().body(response);
    	}
    	else {
    		response.put("userId", result);
    		return ResponseEntity.ok(response);
    	}
    }
    
    @PutMapping("/user/find/password")
    public ResponseEntity<Map<String, String>> findPassword(@RequestBody Member member) {
    	String result = memberService.findPassword(member);
    	
    	Map<String, String> response = new HashMap<>();
    	
    	if (result.equals("존재하지 않는 회원입니다.")) {
    		response.put("message", result);
    		return ResponseEntity.badRequest().body(response);
    	}
    	else {
    		response.put("newPwd", result);
    		return ResponseEntity.ok(response);
    	}
    }
    
	
    // 로그인 세션 정보확인용 URL // POST + localhost:8080/login > Header에서 Bearer 복사 > localhost:8080/auth > Header : Authorization으로 확인
	@GetMapping("/auth") 
	public @ResponseBody ResponseEntity<?> auth(@AuthenticationPrincipal User user){
		if(user == null) { 
			return ResponseEntity.ok("로그인 상태가 아닙니다."); 
		}
		else {
			return ResponseEntity.ok(user); 
		} 
	}
	
	@GetMapping("/auth1") 
	public void auth1(){ // jwt 토큰에서 로그인한 사용자 정보 읽어오는 코드
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		System.out.println(user.getUsername());
		return; 
	}
    
}
