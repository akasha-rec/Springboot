package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberDao memberDao;
	
	//의존성 주입(생성자)
//	public MemberService(MemberDao memberDao) {
//		this.memberDao = memberDao;
//		System.out.println("Service DI 생성");
//	}
//	
//	//의존성 주입(setter)
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}
//	
//	public MemberService() {
//		try {
//			memberDao = new MemberDao();
//			System.out.println("Service 기본 생성자");
//		} catch (Exception e) {
//			System.out.println("Dao 객체 생성 오류");
//		}
//
//	}
	
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
