package com.codewithMantu.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.codewithMantu.blog.security.CustomUserDetailsService;
import com.codewithMantu.blog.security.JwtAuthenticationEntryPoint;
import com.codewithMantu.blog.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig{
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFilter  authenticationFilter ;

//	@Bean
//	public  UserDetailsService   userDetailsService() {
//		
//		UserDetails normal=User.builder()
//				
//				
//				.username("Mantu")
//				.password(passwordEncoder().encode("mantu"))
//				.roles("NORMAl")
//				.build();
//		
//		
//		UserDetails admin=User.builder()
//				.username("Pandit")
//				.password(passwordEncoder().encode("pandit"))
//				.roles("ADMIN")
//				.build();
//				
//		//user create
//		//  is implementation class of USerDetailsService
//		return new InMemoryUserDetailsManager(normal,admin);
//		
//	}
	@Bean
	public  SecurityFilterChain  securityFilterChain(HttpSecurity http)throws Exception{
		
//		http.autharizaRequsts()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.formLogin().LoginPage("Longin.html")
//		.loginProccesssingUrl("/proccess-url")
//		.defaultSuccessUrl("/dashboard")
//		.failureUrl("/error")
//		.and()
//		.logout()
//		.logoutUrl("/do-logout");
//		
		
		http.csrf() //CsrfConfigurer<HttpSecurity>
		.disable() //HttpSecurity
		.cors()//CorsConfigurer<HttpSecurity>
		.disable()//HttpSecurity
		.authorizeRequests()//ExpressionUrlAuthorizationConfigurer<...>AuthorizedUrlRegistry
		.antMatchers("/auth/login")
		.permitAll()
		.anyRequest()//ExpressionUrlAuthorizationConfigurer<...>AuthorizedUrl
		.authenticated()//ExpressionUrlAuthorizationConfigurer<...>AuthorizedUrlRegistry
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//		.httpBasic();
		
		return http.build();
		
	}
	@Bean
	public  DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return  daoAuthenticationProvider;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	public AuthenticationManager authnticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
	
	}
