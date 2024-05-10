package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		try {
			memberDao = new MemberDao();
		} catch (Exception e) {
			System.out.println("Dao 객체 생성 오류");
		}

	}
	
	public List<MemberVO> getAllMembers() {
		return memberDao.getAllMembers();
	}
	
	public MemberVO getMemberId(Integer id) {
		return memberDao.getMemberId(id);
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		return memberDao.addMember(memberVO);
	}
	
	public int removeMember(Integer id) {
		return memberDao.removeMember(id);
	}
	
	public MemberVO updateMember(MemberVO memberVO) {
		return memberDao.updateMember(memberVO);
	}
}
