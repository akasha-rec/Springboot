package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Members;
import edu.pnu.persistence.MembersRepository;

@SpringBootTest
public class Repotest {
	
	@Autowired MembersRepository memRepo;
	
	@Test
	public void doWork() {
		Members member = memRepo.findById("admin").get();
		System.out.println(member);
	}
}
