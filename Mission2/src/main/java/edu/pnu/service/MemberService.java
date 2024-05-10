package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.MemberVO;

public class MemberService {
	private List<MemberVO> list;
	
	public MemberService() {
		list = new ArrayList<MemberVO>();
		for (int i = 1; i <= 10; i++) {
			list.add(MemberVO.builder()
					.id(i)
					.pass("pass" + i)
					.name("name" + i)
					.regidate(new Date()).build());
		}
	}
	
	public List<MemberVO> getAllMembers() {
		return list;
	}

	public MemberVO getMemberId(Integer id) {
		for (MemberVO mem : list) {
			if (mem.getId() == id) {
				return mem;
			}
		}
		return null;
	}
	
	public MemberVO addMember(@RequestBody MemberVO memberVO) {
		if (getMemberId(memberVO.getId()) != null) {
			return null;
		}
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}
	
	public int updateMember(@RequestBody MemberVO memberVO) {
		MemberVO m = getMemberId(memberVO.getId());
		if(m == null)
			return 0;
		
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	
	public int removeMember(@RequestBody MemberVO memberVO) {
		try {
			list.remove(getMemberId(memberVO.getId()));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
