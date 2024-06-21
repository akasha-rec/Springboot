package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.NutriResult;

@Repository
public interface NutriResultRepository extends JpaRepository<NutriResult, Integer> {

}
