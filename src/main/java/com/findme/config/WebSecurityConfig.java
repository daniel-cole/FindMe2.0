package com.findme.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.findme.security.CustomAuthenticationManager;
import com.findme.security.JWTAuthenticationFilter;
import com.findme.security.JWTLoginFilter;
import com.findme.security.SecurityHelper;
import com.findme.security.TokenAuthenticationService;
import com.findme.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Autowired
	SecurityHelper securityHelper;

	@Autowired
	DataSource dataSource;

	@Autowired
	TokenAuthenticationService tokenAuthenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.csrf().disable() //disable csrf until there's an actual website
			.authorizeRequests()
			.antMatchers("/*").permitAll() 
			.antMatchers("/api/login","/api/register")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.logout()
				.permitAll()
				.and()
			.addFilterBefore(new JWTLoginFilter("/api/login", authenticationManager(), tokenAuthenticationService),
					UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTAuthenticationFilter(tokenAuthenticationService), 
					UsernamePasswordAuthenticationFilter.class);
		// @formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return new CustomAuthenticationManager(userService, securityHelper);
	}

}