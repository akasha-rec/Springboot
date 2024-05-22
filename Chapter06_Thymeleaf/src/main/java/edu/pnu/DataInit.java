package edu.pnu;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner { //기본 데이터 세팅
	
	@Autowired private BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 1; i <= 5; i++) {
			boardRepo.save(Board.builder()
					.title("제목" + i)
					.writer("member1")
					.content("내용" + i)
					.createDate(new Date()).build());
		}

	}

}
