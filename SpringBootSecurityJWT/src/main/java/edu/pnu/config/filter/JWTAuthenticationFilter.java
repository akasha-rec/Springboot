package edu.pnu.config.filter; //로그인 정보 가로채는 필터

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	// 인증 객체
	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
		// request에서 json 타입의 [username/password]를 읽어서 Member 객체 생성
		ObjectMapper mapper = new ObjectMapper(); //자바 객체 > JSON 객체로(직렬화) 혹은 JSON 객체 > 자바 클래스로(역직렬화)
		Member member = null;
		try {
			member = mapper.readValue(request.getInputStream(), Member.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Security에게 로그인 요청에 필요한 객체 생성
		Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
		
		// 인증 진행 > UserDetailsService를 상속받은 클래스의 loadUserByUsername 호출
		Authentication auth = authenticationManager.authenticate(authToken);
		System.out.println("auth:" + auth);
		
		return auth;
	}
	
	// 인증이 성공했을 때 실행되는 후처리 메소드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		// 인증 결과 생성된 Authentication 객체에서 필요한 정보를 읽어서 토큰을 만들어서 헤더에 추가
		User user = (User)authResult.getPrincipal(); //UserDetails를 구현한 객체가 가지고 있는 정보들을 가져옴
		
		String token = JWT.create()
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*120)) // token의 만료 시간 설정
				.withClaim("username", user.getUsername()) // jwt의 payload(실질적인 데이터) 부분에서 private 설정. username으로 식별하겠다.
				.sign(Algorithm.HMAC256("edu.pnu.jwt"));
		response.addHeader("Authorization", "Bearer " + token); //응답하는 헤더에 추가, "Bearer " = 키워드
	}
}
