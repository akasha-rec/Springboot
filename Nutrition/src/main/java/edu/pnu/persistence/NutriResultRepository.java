package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.NutriResult;

@Repository
public interface NutriResultRepository extends JpaRepository<NutriResult, Integer> {
	List<NutriResult> findByRid(int rid);
}
