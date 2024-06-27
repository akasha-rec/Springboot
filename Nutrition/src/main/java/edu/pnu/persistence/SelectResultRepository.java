package edu.pnu.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.SelectResult;

@Repository
public interface SelectResultRepository extends JpaRepository<SelectResult, Integer> {
	List<SelectResult> findByUserId(String userId); // 작성한 아이디를 찾아 작성한 게시글 찾기
	Optional<SelectResult> findById(int rid);
}
