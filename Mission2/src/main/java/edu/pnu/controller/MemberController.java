package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public MemberVO getMemberId(Integer id) {
		return memberService.getMemberId(id);
	}
	
	@PostMapping("/memberJSON")
	public MemberVO addMember(@RequestBody MemberVO memberVO) {
		return memberService.addMember(memberVO);
	}
	
	@PutMapping("/memberJSON")
	public int updateMember(@RequestBody MemberVO memberVO) {
		return memberService.updateMember(memberVO);
	}
	
	@DeleteMapping("/memberJSON")
	public int removeMember(@RequestBody MemberVO memberVO) {
		return memberService.removeMember(memberVO);
	}
}
