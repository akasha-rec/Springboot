package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;
//<Entity Class type, PK 타입 : @Id로 매핑한 PK>
public interface BoardRepository extends JpaRepository<Board, Long> { //body는 JPA가 만들어줌
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingAndContentContaining(String title, String content);
}
