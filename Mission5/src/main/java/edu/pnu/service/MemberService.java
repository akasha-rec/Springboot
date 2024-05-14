package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public List<MemberVO> getAllMembers() {
		return memberDao.getAllMembers();
	}
	
	public MemberVO getMemberId(Integer id) {
		return memberDao.getMemberID(id);
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		return memberDao.addMember(memberVO);
	}
	
	public MemberVO updateMember(MemberVO memberVO) {
		return memberDao.updateMember(memberVO);
	}
	
	public int deleteMember(Integer id) {
		return memberDao.deleteMember(id);
	}
}
