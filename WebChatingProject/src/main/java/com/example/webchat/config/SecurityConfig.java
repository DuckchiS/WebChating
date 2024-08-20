package com.example.webchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/user/signup", "/user/signupProc","/board/list","/board/read").permitAll()
				.requestMatchers("/board/write", "/board/del").hasRole("Admin") 
				.requestMatchers("/chating").hasAnyRole("User","Admin")
				.requestMatchers("/board/reply").authenticated() 
		        .anyRequest().permitAll()
				);
		
        http.formLogin(auth -> auth
                .loginPage("/user/login").permitAll()
                .loginProcessingUrl("/user/loginProc")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/user/login?error=true")
                .permitAll()
        );

        http.logout(auth -> auth
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .permitAll()
        );

        http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
}
