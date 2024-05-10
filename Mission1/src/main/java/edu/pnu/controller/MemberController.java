package edu.pnu.controller; //controller가 service까지 다 해버리는 경우인데 없다

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberController {
	private List<MemberVO> memberList; //클래스의 속성으로 List<MemberVO> 타입의 참조변수를 선언
	
	public MemberController() {
		memberList = new ArrayList<MemberVO>(); //생성자에서 메모리를 할당
		for (int i = 1; i <= 10; i++) {
//			MemberVO member = new MemberVO();
//			member.setId(i);
//			member.setPass("pass" + i);
//			member.setName("이름" + i);
//			member.setRegidate(new Date());
//			memberList.add(member);
			
			memberList.add(MemberVO.builder()
					.id(i)
					.pass("pass" + i)
					.name("name" + i)
					.regidate(new Date()).build());
		}
	}
	
	//검색 (Read - select)
//	@GetMapping("/members")
//	public List<MemberVO> getMembers() {
//		return memberList;
//	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getMembers() {
		// return ResponseEntity.ok(memberList);
		return ResponseEntity.badRequest().body(memberList);
	}
	
	//검색 (Read - select)
	@GetMapping("/member")
	public MemberVO getMember(Integer id) {
		for (MemberVO m : memberList) {
			if (m.getId() == id) {				
				return m;
			}
		}
		return null;
	}
	
	//입력 (Create == insert - Post)
	@PostMapping("/member") // form의 action
	//dispatcherservlet이 param을 받아 @postmapping을 찾아서 변수에 값 세팅해서 memberVO 객체를 만들어
	public MemberVO addMember(MemberVO memberVO) {
		if (getMember(memberVO.getId()) != null)
			return null;
		
		memberVO.setRegidate(new Date());
		memberList.add(memberVO);
		return memberVO;
	}
	
	//입력 (Create == insert - Post)
		@PostMapping("/memberJSON")
		public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
			if (getMember(memberVO.getId()) != null) {
				return null;
			}
			
			memberVO.setRegidate(new Date());
			memberList.add(memberVO);
			return memberVO;
	}
		
	// 수정(Update - put)
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = getMember(memberVO.getId());
		if (m == null)
			return 0;
			
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	
	// 삭제(Delete - Delete)
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		try {
			memberList.remove(getMember(id));
		} catch(Exception e) {
			return 0;
		}
		return 1;
	}
	
}



