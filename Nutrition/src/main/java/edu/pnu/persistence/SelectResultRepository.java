package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.SelectResult;

@Repository
public interface SelectResultRepository extends JpaRepository<SelectResult, Integer> {

}
