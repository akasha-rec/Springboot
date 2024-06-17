package edu.pnu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	@Id
	private String userId;
	private String password;
	
	private String age;
	private String gubun;
	private String weekNum;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role = Role.ROLE_MEMBER;
}
