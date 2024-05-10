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
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public List<MemberVO> getAllMembers() {
		return memberService.getAllMembers();
	}
	
	@GetMapping("/member")
	public MemberVO getMember(Integer id) {
		return memberService.getMemberId(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		return memberService.addMember(memberVO);
	}
	
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		return memberService.removeMember(id);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO memberVO) {
		return memberService.updateMember(memberVO);
	}
	
}
