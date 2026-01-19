package com.study.lastlayer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.study.lastlayer.auth.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // 메서드 단위 보안 활성화
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
				.formLogin(form -> form.disable()).httpBasic(httpBasic -> httpBasic.disable())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		// BCrypt는 솔트(Salt)를 자동으로 생성하여 암호화해주는 가장 권장되는 방식입니다.
		return new BCryptPasswordEncoder();
	}
}
