package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {
	
	//@Autowired //DI > 자동으로 연결 1. / 4. final 필드변수 + @RequiredArgsConstructor
	private final MemberService memberService;
	
	//@Autowired 2. 생성자에 파라미터로
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//		System.out.println("Controller 생성자에 Autowired");
//	}
	
	//3번째 setter
//	public setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
//	public MemberController() {
//		memberService = new MemberService(new MemberDao()); //생성자 - 의존성 주입
//		System.out.println("Controller 객체 생성");
//		memberService = new MemberService(); //같이
//		memberService.setMemberDao(new MemberDao()); //setter - 의존성 주입
//	}
	
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
