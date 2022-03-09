package com.te.Car.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.te.Car.filter.JwtRequestFilter;
import com.te.Car.util.JwtUtil;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtRequestFilter filter;
	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().cors().disable().authorizeHttpRequests().antMatchers("/addCar").hasRole("ADMIN")
//		.antMatchers("/getCars").hasAnyRole("ADMIN","SUPER").antMatchers("/deleteCar").hasAnyRole("ADMIN","SUPER")
//		.antMatchers("/updateCar").hasAnyRole("ADMIN","SUPER").and().formLogin();
//		
//		http.csrf().disable().cors().disable().authorizeHttpRequests().antMatchers("/addCar").permitAll();
//	}

		http.csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeHttpRequests().antMatchers("/addCar").permitAll();
		http.authorizeHttpRequests().antMatchers("/authenticate").permitAll();
		http.authorizeHttpRequests().antMatchers("/signUp").permitAll();

		http.authorizeHttpRequests().antMatchers("/getAllCars").permitAll();

		http.authorizeHttpRequests().antMatchers("/deleteCar/**").permitAll();
		http.authorizeHttpRequests().antMatchers("/updateCar/**").permitAll();
		http.authorizeHttpRequests().antMatchers("/super/getall").hasRole("SUPERADMIN");

		
		http.authorizeHttpRequests().anyRequest().authenticated();
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/getAllCars");
//	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	 

}
