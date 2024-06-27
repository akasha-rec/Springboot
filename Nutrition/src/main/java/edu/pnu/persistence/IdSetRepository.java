package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.IdSet;

@Repository
public interface IdSetRepository extends JpaRepository<IdSet, Integer> {
	// 이런 식의 쿼리를 사용할 때는 Object[]로 해야
	// 위치 기반 파라미터 사용 > 쿼리 실행 시 사용되는 파라미터 값으로 매핑
	@Query("SELECT n.foodname FROM Nutrition n, IdSet i WHERE n.id = i.nid and i.rid = ?1")
	List<Object[]> getFoodname(int rid);
}
