package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class QueryMethodTest {
	@Autowired private BoardRepository boardRepo;
	
	@BeforeEach //테스트 메서드 실행 이전에 수행
	public void dataPrepare() {
		for (int i = 1; i <= 200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 "+i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			boardRepo.save(board);
		}
	}
	
	@Test
	@Order(1)
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("테스트 제목 10");
		
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	@Test
	@Order(2)
	public void testByContentContaining() {
		List<Board> boardList = boardRepo.findByContentContaining("17");
		
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	@Test
	@Order(3)
	public void testByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("17", "17");
		
		System.out.println("Or 검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	@Test
	@Order(4)
	public void testByTitleContainingAndContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingAndContentContaining("17", "11");
		
		System.out.println("And 검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
}
