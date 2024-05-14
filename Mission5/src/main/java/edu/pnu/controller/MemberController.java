package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members")
	public List<MemberVO> getAllMembers() {
		return memberService.getAllMembers();
	}
	
	@GetMapping("/member")
	public MemberVO getMemberId(Integer id) {
		return memberService.getMemberId(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		return memberService.addMember(memberVO);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO memberVO) {
		return memberService.updateMember(memberVO);
	}
	
	@DeleteMapping("/member")
	public int deleteMember(Integer id) {
		return memberService.deleteMember(id);
	}
}
