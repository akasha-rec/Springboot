package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
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
