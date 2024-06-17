package edu.pnu.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepo;
	PasswordEncoder encoder = new BCryptPasswordEncoder();

	public boolean successMember(Member member) { // 회원가입
		if (memberRepo.existsByUserId(member.getUserId())) {
			return false;
		}
		else {
			member.setAge(member.getAge());
			member.setGubun(member.getGubun());
			member.setWeekNum(member.getWeekNum());
			member.setUserId(member.getUserId());
			member.setPassword(encoder.encode(member.getPassword()));
			memberRepo.save(member);
			return true;
		}
	}
	
	public Optional<Member> findMemberByUserId(String userId) {
        return memberRepo.findByUserId(userId);
    }
}
