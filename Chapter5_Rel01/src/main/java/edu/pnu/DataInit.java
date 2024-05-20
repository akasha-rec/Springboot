package edu.pnu;

import java.util.Date;

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
public class DataInit implements ApplicationRunner { //서버 구동할 때마다 데이터 베이스 초기화 시켜줌
	
	private final BoardRepository boardRepo;
	private final MemberRepository memberRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception { //하이버네이트가 SQL 만들어서 H2로 던져
		
		Member member1 = Member.builder()
				.id("member1").password("password1").name("홍길동")
				.role("USER").build();
		memberRepo.save(member1);
		
		Member member2 = Member.builder()
				.id("member2").password("password2").name("홍이동")
				.role("ADMIN").build();
		memberRepo.save(member2);
		
		for (int i = 1; i <= 5; i++) {
			boardRepo.save(Board.builder()
					.title("title1" + i)
					.content("content1" + i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.member(member1)
					.build());
		}
		
		for (int i = 1; i <= 5; i++) {
			boardRepo.save(Board.builder()
					.title("title2" + i)
					.content("content2" + i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.member(member2)
					.build());
		}

	}

}
