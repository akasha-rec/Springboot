package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepo;

	public List<Member> getAllMembers() {
		return memberRepo.findAll(); 
	}
	
	public Member getMember(String username) {
		return memberRepo.findById(username).get();
	}
	
	public Member addMember(Member mem) {
		return memberRepo.save(mem);
	}
	
	public Member updateMember(Member mem) {
		Member m = memberRepo.findById(mem.getUsername()).get();
		m.update(mem);
		return memberRepo.save(m);
	}
	
	public Member deleteMember(String username) {
		Member m = memberRepo.findById(username).get();
		memberRepo.deleteById(username);
		return m;
	}
}
