package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration // 스프링 시큐리티 설정 클래스임을 알리는 어노테이션
@EnableWebSecurity // 웹 보안 지원 활성화
public class SecurityConfig {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Bean // 비밀번호 암호화되는 데 사용 > BCryptPasswordEncoder 객체가 스프링 컨테이너에 의해 관리되는 빈이 됨
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean // 시큐리티 필터 체인 구성 > HttpSecurity 객체를 통해 다양한 시큐리티 설정을 적용하고 빈으로 등록하여 스프링 시큐리티가 해당 설정을 사용
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable()); // CSRF 보호 비활성화
		
		//접근 권한 설정하는 메소드
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.formLogin(frmLogin->frmLogin.disable()); // form을 이용한 로그인 사용X > 여기서는 jwt 토큰 방식
		http.httpBasic(basic->basic.disable()); // http Basic 인증 방식 사용X
		
		// 세션 유지X > url 호출 뒤 응답할 때까지는 유지되지만, 응답 후 삭제된다
		http.sessionManagement(ssmn->ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepo), AuthorizationFilter.class);
		return http.build();
	}
}
