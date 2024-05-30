package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Members;

public interface MembersRepository extends JpaRepository<Members, String> {

}
