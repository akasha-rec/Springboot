package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.IdSet;

@Repository
public interface IdSetRepository extends JpaRepository<IdSet, Integer> {

}
