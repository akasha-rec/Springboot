package edu.pnu.controller; // POST/login 요청이 들어오면 실행

import java.util.Collection;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j // 로깅을 위한 코드 생성 > 로깅용 Logger 객체 생성
public class JWTController {
	
	private final AuthenticationConfiguration authConfig;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member) {
		
		try {
			// Security에게 로그인 요청에 필요한 객체 생성
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			// 인증 진행 > UserDetailsService를 상속받은 클래스의 loadUserByUsername 호출
			Authentication auth = authConfig.getAuthenticationManager().authenticate(authToken);
		
			// 인증 결과 생성된 Authentication 객체에서 필요한 정보를 읽어서 토큰을 만들어서 헤더에 추가
			User user = (User)auth.getPrincipal();
			Collection<GrantedAuthority> cols = user.getAuthorities();
			StringBuffer role = new StringBuffer();
			for (GrantedAuthority ga : cols) {
				role.append(ga.getAuthority());
			}
			
			String token = JWT.create()
					.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*120)) // token의 만료 시간 설정
					.withClaim("username", user.getUsername()) // jwt의 payload(실질적인 데이터) 부분에서 private 설정. username으로 식별하겠다.
					.sign(Algorithm.HMAC256("edu.pnu.jwt"));
			
			log.info(user.toString());
			log.debug(token);
			
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
}