package edu.pnu.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
//    @PostMapping("/Login")
//    public ResponseEntity<?> loginMember
    
	@GetMapping("/member")
	 public ResponseEntity<?> getMember(String userId) {
        // MemberService를 통해 userId로 멤버 조회
        Optional<Member> optionalMember = memberService.findMemberByUserId(userId);

        if (optionalMember.isPresent()) {
            return ResponseEntity.ok(optionalMember.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
