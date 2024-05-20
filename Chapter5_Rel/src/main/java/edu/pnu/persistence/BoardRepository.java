package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;
//<Entity Class type, PK 타입 : @Id로 매핑한 PK>
public interface BoardRepository extends JpaRepository<Board, Long> { //body는 JPA가 만들어줌(@ 어노테이션 필요X)

}
