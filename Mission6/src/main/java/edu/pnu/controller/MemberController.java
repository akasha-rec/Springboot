package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/members")
	public List<Member> getAllMembers() {
		return memberService.getAllMembers();
	}
	
	@GetMapping("/member")
	public Member getMember(String username) {
		return memberService.getMember(username);
	}
	
	@PostMapping("/member")
	public Member addMember(Member m) {
		return memberService.addMember(m);
	}
	
	@PutMapping("/member")
	public Member updateMember(Member m) {
		return memberService.updateMember(m);
	}
	
	@DeleteMapping("/member")
	public Member deleteMember(String username) {
		return memberService.deleteMember(username);
	}
}
