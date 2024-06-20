package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<String> findId(@RequestBody Member member) { // ? > Object > String으로 결정
    	String result = memberService.findId(member);
    	
    	if (result.equals("존재하지 않는 회원입니다.")) {
    		return ResponseEntity.badRequest().body(result);
    	}
    	else {
    		return ResponseEntity.ok(result);
    	}
    }
    
    @PostMapping("/user/find/password")
    public ResponseEntity<String> findPassword(@RequestBody Member member) {
    	String result = memberService.findPassword(member);
    	
    	if (result.equals("존재하지 않는 회원입니다.")) {
    		return ResponseEntity.badRequest().body(result);
    	}
    	else {
    		return ResponseEntity.ok(result);
    	}
    }
    
//    @PostMapping("/user/change/password")
//    public ResponseEntity<?> changePassword(@RequestBody Member member) { // 비밀번호 변경
//    	
//    }
}
