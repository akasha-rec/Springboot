package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class MemberTest {
	
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void successMember() {
		Member test = Member.builder()
				.userAge("30~49")
				.userCondition1("임신 3분기(19주~40주)")
				.userCondition2("15주")
				.userId("Test@naver.com")
				.password(encoder.encode("1234"))
				.phoneNumber("010-2222-3333")
				.role(Role.ROLE_MEMBER).build();
		memberRepo.save(test);
	}
}
