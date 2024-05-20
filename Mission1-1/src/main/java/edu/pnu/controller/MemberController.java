package edu.pnu.controller; //HashMap 모르겠어...

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.pnu.domain.MemberVO;

public class MemberController {
	private HashMap<Integer, MemberVO> map;
	
	public MemberController() {
		map = new HashMap<Integer, MemberVO>();
		for (int i = 1; i <= 10; i++) {
			map.put(i, MemberVO.builder()
					.id(i)
					.pass("pass" + i)
					.name("name" + i)
					.regidate(new Date()).build());
		}
	}
	
	@GetMapping("/members")
	public Collection<MemberVO> getMembers() {
		return map.values();
	}
	
//	@PostMapping("/member")
//	public MemberVO getMember(Integer id) { //키로 검색...
//		for ()
//	}
}
