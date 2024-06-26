package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
	
	// POST/login 요청이 왔을 때 인증을 시도하는 메소드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
		// request에서 json 타입의 [id, password]를 읽어서 Member 객체 생성
		ObjectMapper mapper = new ObjectMapper();
		Member member = null;
		
		try {
			member = mapper.readValue(request.getInputStream(), Member.class);
			System.out.println(member);
			
			// Secutiry에게 자격 증명 요청에 필요한 객체 생성
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUserId(), member.getPassword());
			
			// 인증 진행 > UserDetailsService의 loadUserByUsername에서 DB로부터 사용자 정보 읽어온 뒤
			// 사용자 입력 정보와 비교한 뒤 자격 증명에 성공하면 Authentication 객체를 만들어서 return
			Authentication auth = authenticationManager.authenticate(authToken);
			System.out.println("auth" + auth);
			return auth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		return null;
	}
	
	// 인증이 성공했을 때 실행되는 후처리 메소드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		// 자격 증명이 성공하면 loadUserByUsername에서 만든 객체가 authResult에 담겨져
		User user = (User)authResult.getPrincipal();
		
		String token = JWT.create()
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*30))
				.withClaim("username", user.getUsername())
				.sign(Algorithm.HMAC256("edu.pnu.jwt"));
		
		// 응답 헤더에 추가
		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		response.setStatus(HttpStatus.OK.value());
	}
}
