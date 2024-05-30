package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Members;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MembersRepository;

@SpringBootTest
public class MemberInitialize {
	
	@Autowired MembersRepository memRepo;
	
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void doWork() {
		
		memRepo.save(Members.builder()
				.username("marketing")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_MARKETING)
				.enabled(true).build());
		
		memRepo.save(Members.builder()
				.username("develop")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_DEVELOP)
				.enabled(true).build());
		
		memRepo.save(Members.builder()
				.username("finance")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_FINANCE)
				.enabled(true).build());
		
		memRepo.save(Members.builder()
				.username("admin")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_ADMIN)
				.enabled(true).build());
	}
}
