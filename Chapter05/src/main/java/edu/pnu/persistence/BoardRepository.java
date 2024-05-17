package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;
//<Entity Class type, PK 타입 : @Id로 매핑한 PK>
public interface BoardRepository extends JpaRepository<Board, Long> { //body는 JPA가 만들어줌(@ 어노테이션 필요X)
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingAndContentContaining(String title, String content);
	
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	List<Board> findByTitleContaining(String searchKeyword);
	//SELECT FROM board WHERE title containing "title" and cnt > 50
	List<Board> findByTitleContainingAndCntGreaterThan(String title, Long cnt);
	//SELECT FROM board WHERE (cnt between s and e) and title containing "title" order by cnt asc
	List<Board> findByCntBetweenOrderByCntAsc(Long start, Long end);
	
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
	// 1. 위치 기반 파라미터
	//@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	//List<Board> queryAnnotationTest1(String searchKeyword); //검색 후 여러 객체를 배열로 받음
	
	//이름 기반 파라미터 > 필드 속성(테이블명)은 class명(변수명)이랑 같아야 한다.
	@Query("SELECT b FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(@Param("searchKeyword") String searchKeyword); 
	
	
	//특정 변수만 조회(특정 컬럼들만 조회해서 그 값을 들고 온다)
	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
	
	
	//네이티브 쿼리
	@Query(value="select seq, title, writer, create_date " //create_date 왜 이렇게 써야하는지 궁금
			+ "from board where title like '%'||?1||'%' "
			+ "order by seq desc", nativeQuery = true)
	List<Object[]> queryAnnotationTest3(String searchKeyowrd);
}
