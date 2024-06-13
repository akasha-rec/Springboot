package edu.pnu.domain;
//jwt 토큰 방식(브라우저에서는 세션의 쿠키를 이용해 로그인이 가능했지만, 네이티브 앱에서는 쿠키를 사용할 수 없으므로 '토큰'을 사용

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {
	@Id
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	private boolean enabled;
}
