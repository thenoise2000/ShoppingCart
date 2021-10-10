package com.major.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.major.services.CustomUserDetailService;

@EnableWebSecurity
@Configuration
@Order(1)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable();
		
		httpSecurity.antMatcher("/admin/**")
					.authorizeRequests()
					.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")					
					.and()
					.formLogin()
					.loginPage("/login")
					//.loginProcessingUrl("/admin/process-login")
					.defaultSuccessUrl("/")
					.failureUrl("/login?error= true")
					.usernameParameter("username").passwordParameter("password")
					.and()
					.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
					.deleteCookies("JSESSIONID")
					.and()
					.exceptionHandling();
	}
	
	

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/images/**", "/productimages/**", "/css/**", "/js/**");
	}
}
