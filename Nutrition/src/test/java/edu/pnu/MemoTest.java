package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.domain.MyMemo;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.persistence.MyMemoRepository;

@SpringBootTest
public class MemoTest {

	@Autowired
	private MyMemoRepository memoRepo;

	@Autowired
	private MemberRepository memRepo;
	
	@Test
	public void insertMemo() {
		Member mem = Member.builder()
				.userId("44@naver.com")
				.password("1111")
				.userAge("30~49")
				.userCondition1("임신 3분기(19주~40주)")
				.userCondition2("15주")
				.build();
		
		memRepo.save(mem);
		
		MyMemo test = MyMemo.builder()
				 .title("test")
				 .member(mem)
				 .content("테스트").build();
	
		memoRepo.save(test);
	}
}
