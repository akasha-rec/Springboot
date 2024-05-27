package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	@Autowired
	private MemberRepository memberRepository;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable()); //CSRF 보호 비활성화
		
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		///login > 404 에러 >> 프론트엔드에서 로그인 정보를 보내달라고 or postman으로 로그인 해주는 방식으로 테스트
		http.formLogin(fmLogin->fmLogin.disable()); // Form을 이용한 로그인을 사용하지 않겠다는 설정
		http.httpBasic(basic->basic.disable()); // Http Basic 인증 방식을 사용하지 않겠다
		
		// 세션을 유지하지 않겠다고 설정 > url 호출 뒤 응답할 때까지는 유지되지만, 응답 후 삭제된다
		// 프론트에서 로그인 요청했을 때 토큰을 보냈다가 다시 로그인을 요청하면 기존의 토큰을 헤더에 담아서 request 요청이 오면 분석해서 User 객체를 만들어서 세션에 올렸다가 다시 보낼 때는 삭제 ↓ 있으면 토큰 방식이구나 알면 됨
		http.sessionManagement(ssmn->ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 설정X > 로그인 성공 후에도 세션에 살아있지만, 토큰 방식의 이점X
		
		// 스프링 시큐리티가 등록한 필터체인의 뒤에 작성한 필터를 추가 + 스프링 시큐리티가 만든 필터를 상속받은 경우에는 그저 addFilter
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		// 스프링 시큐리티가 등록한 필터들 중에서 AuthorizationFilter 앞에 앞에서 작성한 필터 삽입
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		return http.build();
	}
}
