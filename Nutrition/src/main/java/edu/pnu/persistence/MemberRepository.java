package edu.pnu.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	boolean existsByUserId(String userId);
	Optional<Member> findByUserId(String userId);
}
