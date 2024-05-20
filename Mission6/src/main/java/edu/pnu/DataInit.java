package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 1; i <= 5; i++) {
			boardRepo.save(Board.builder()
					.title("제목1" + i)
					.content("내용1" + i).build());
		}
		
		for (int i = 1; i <= 5; i++) {
			boardRepo.save(Board.builder()
					.title("제목2" + i)
					.content("내용2" + i).build());
		}
			memberRepo.save(Member.builder()
					.username("member1")
					.password("password1")
					.role("USER").build());
			
			memberRepo.save(Member.builder()
					.username("member2")
					.password("password2")
					.role("ADMIN").build());
		}
		
	}
