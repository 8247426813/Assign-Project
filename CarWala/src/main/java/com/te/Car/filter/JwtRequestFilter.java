package com.te.Car.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.te.Car.util.JwtUtil;
@Component
public class JwtRequestFilter  extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = null;
		String adminUsername = null;
		
		String autherirationHeader = request.getHeader("Authorization");
		
		if (autherirationHeader != null && autherirationHeader.startsWith("Bearer ")) {
			jwt=autherirationHeader.substring(7);
            adminUsername=jwtUtil.extractUsername(jwt);
		}
		if (adminUsername != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails admin = userDetailsService.loadUserByUsername(adminUsername);
			
			if (jwtUtil.validateToken(jwt, admin)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						admin.getUsername(), admin.getPassword(), admin.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}

		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "4200");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Auth-Token, Content-Type, Accept, X-Requested-With, remember-me");
		filterChain.doFilter(request, response);
	}

//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		
//		return new AntPathMatcher().match("/", ALREADY_FILTERED_SUFFIX)
//	}
		
	

}
