package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //설정 클래스라고 정의(IoC 컨테이너에 로드)
@EnableWebSecurity //스프링 시큐리티 적용에 필요한 필터 객체들 자동 생성
public class SecurityConfig {
	
	@Bean //붙어있는 메서드가 리턴하는 객체를 IoC 컨테이너에 등록하라는 지시(httpSecurity 객체로 등록)
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(security->security
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.csrf(cf->cf.disable()); //CSRF 보호 비활성화(사이트간 요청 위조)
//		http.formLogin(form->{}); //스프링부트가 제공해주는 로그인을 사용하겠다는 설정
		http.formLogin(form->
				form.loginPage("/login") //↓ 보통은 false로 설정 : 로그인 이전 접근하려고 했던 페이지로 이동
					.defaultSuccessUrl("/loginSuccess", true)); //lambda식이라서 {} 해줘야 하는데 .~~로 체인식 코딩을 하면 필요X
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		
		http.logout(logout->logout
				.invalidateHttpSession(true) // 현재 브라우저와 연결된 세션 강제 종료
				.deleteCookies("JSESSIONID") // 세션 아이디가 저장된 쿠키 삭제
				.logoutSuccessUrl("/login")); // 로그아웃 후 이동할 URL 지정
		
		http.headers(hr->hr.frameOptions(fo->fo.disable())); //url 호출했을 때 프레임만 떠서 추가한 코드 > 정상적으로 화면 뜸
		 
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() { //패스워드를 쉽게 암호화할 수 있도록 PasswordEncoder 인터페이스를 구현한 클래스 제공
		return new BCryptPasswordEncoder();
	}
	
//		JPA 연동 시큐리티 할 때는 없어야 충돌 X	
//		@Autowired //임시 테스트용 사용자 계정을 메모리에 등록해서 권한 관계가 제대로 작동하는지 확인 > 코드대로 로그인
//		public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//			auth.inMemoryAuthentication()
//				.withUser("manager")
//				.password("{noop}abcd") //No Operation > 비밀번호 암호화되어있지 않다
//				.roles("MANAGER");
//			auth.inMemoryAuthentication()
//				.withUser("admin")
//				.password("{noop}abcd")
//				.roles("ADMIN");
//		}
	}
