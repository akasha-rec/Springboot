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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired private AuthenticationConfiguration authenticationConfiguration;
	@Autowired private MemberRepository memberRepository;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable());
		
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/MyPage").authenticated()
				.requestMatchers("/NutriCal").hasAnyRole("MEMBER", "ADMIN")
				.anyRequest().permitAll());
		
		http.formLogin(frmLogin->frmLogin.disable()); // 스프링부트 시큐리티 제공 폼 로그인 X
		http.httpBasic(basic->basic.disable()); //Http Basic 인증 방식을 사용X
		
		http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		// AuthorizationFilter(SpringSecurity) 앞에 JWTAuthorizationFilter를 위치시킨다.
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		
		http.cors(cors->cors.configurationSource(corsSource()));		
		return http.build();
	}
	
	private CorsConfigurationSource corsSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOriginPattern(CorsConfiguration.ALL); // 요청을 허용할 서버
		config.addAllowedMethod(CorsConfiguration.ALL); // 요청을 허용할 Method
		config.addAllowedHeader(CorsConfiguration.ALL); // 요청을 허용할 Header
		config.setAllowCredentials(true); // 요청/응답에 자격증명정보 포함을 허용
		// true인 경우 addAllowedOrigin("*")는 사용 불가 > Pattern으로 변경
		
		config.addExposedHeader(CorsConfiguration.ALL);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config); // 위 설정을 적용할 Rest 서버의 URL 패턴 설정
		return source;
	}
}
