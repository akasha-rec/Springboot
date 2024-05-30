package edu.pnu.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests(security->security
				.requestMatchers("/", "/system/**","/auth").permitAll()
				.requestMatchers("/board/**").authenticated()
				.requestMatchers("/admin/**").hasRole("ADMIN"));
		
		http.formLogin(form->form.loginPage("/system/login")
				.defaultSuccessUrl("/board/getBoardList", true));
		http.exceptionHandling(ex->ex.accessDeniedPage("/system/accessDenied"));
		http.logout(logout->logout
				.logoutUrl("/system/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/"));
		
		return http.build();
	}
}
