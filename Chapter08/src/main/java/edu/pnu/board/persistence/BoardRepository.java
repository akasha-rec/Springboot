package edu.pnu.board.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.pnu.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	@Query("SELECT b FROM Board b")
	Page<Board> getBoardList(Pageable pageable); // 페이징 처리와 정렬을 위해서 Pageable 타입의 객체를 매개변수로 받도록 + 검색 결과와 관련된 다양한 정보를 웹 페이지에서 사용하기 위해서 Page를 리턴 타입으로
}
