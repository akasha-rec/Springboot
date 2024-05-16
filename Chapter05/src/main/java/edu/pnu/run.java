package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class run implements ApplicationRunner { //서버 구동할 때마다 데이터 베이스 초기화 시켜줌
	
	private final BoardRepository boardRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception { //하이버네이트가 SQL 만들어서 H2로 던져
		
		Random rnd = new Random();
		for (int i = 1; i < 5; i++) {
			boardRepo.save(Board.builder()
					.title("title1" + i)
					.writer("member1")
					.content("content1" + i)
					.createDate(new Date())
					.cnt(Math.abs(rnd.nextLong()%100L))
					.build());
		}
		
		for (int i = 1; i < 5; i++) {
			boardRepo.save(Board.builder()
					.title("title2" + i)
					.writer("member2")
					.content("content2" + i)
					.createDate(new Date())
					.cnt(Math.abs(rnd.nextLong()%100L))
					.build());
		}

	}

}
