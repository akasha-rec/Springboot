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
				.userAge("19~29")
				.userCondition1("임신 2분기(13주~18주)")
				.userCondition2("15주")
				.userId("44@naver.com")
				.password(encoder.encode("1234"))
				.role(Role.ROLE_MEMBER).build();
		memberRepo.save(test);
	}
}