package edu.pnu.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	boolean existsByUserId(String userId);
	Optional<Member> findByUserId(String userId);
	Member findByPhoneNumber(String phoneNumber); // 전화번호로 ID 찾을 때 사용
	Member findByUserIdAndPhoneNumber(String userId, String phoneNumber); // ID와 전화번호로 비밀번호 변경
}
