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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
		
		System.out.println("[FindByTitle] 검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	@Test
	@Order(2)
	public void testByContentContaining() {
		List<Board> boardList = boardRepo.findByContentContaining("17");
		
		System.out.println("[FindByContentContaining] 검색 결과");
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
	
	@Test
	@Order(5)
	public void testfindByTitleContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDesc("17");
		
		System.out.println("[findByTitleContainingOrderBySeqDesc] 검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	@Test //페이징
	@Order(6)
	public void testFindByTitleContaing() {
//		Pageable paging = PageRequest.of(0, 5); // 5개만 출력됨
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq"); //페이징 & 정렬
		
		Page<Board> pageInfo = boardRepo.findByTitleContaining("제목", paging);
		
		System.out.println("PAGE SIZE: " + pageInfo.getSize());
		System.out.println("TOTAL PAGES: " + pageInfo.getTotalPages());
		System.out.println("TOTAL COUNT: " + pageInfo.getTotalElements());
		System.out.println("NEXT: " + pageInfo.getPageable());
		
		List<Board> boardList = pageInfo.getContent();
		
		System.out.println("[FindByTitleContaing] 페이징 검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
}
