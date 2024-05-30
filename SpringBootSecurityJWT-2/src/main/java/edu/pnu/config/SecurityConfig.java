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

import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.filter.JWTAuthenticationFilter;
import edu.pnu.persistence.MembersRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired MembersRepository memberRepository;
	@Autowired AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable());
		
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/public/**").permitAll()
				.requestMatchers("/intra/marketing/**").hasAnyRole("MARKETING", "ADMIN")
				.requestMatchers("/intra/develop/**").hasAnyRole("DEVELOP", "ADMIN")
				.requestMatchers("/intra/finance/**").hasAnyRole("FINANCE", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN"));
		
		http.formLogin(frmLogin->frmLogin.disable());
		http.httpBasic(basic->basic.disable());
		
		http.sessionManagement(ssmn->ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		// AuthorizationFilter(SpringSecurity) 앞에 JWTAuthorizationFilter를 위치시킨다.
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		return http.build();
	}
}
