package com.shoestore.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.shoestore.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private DataSource dataSource;
	
	
	
	

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider =  new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
		
		
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	
	private static final String [] PUBLIC_MATCHERS= {
			"/css/**",
			"/js/**",
			"/image/**",
			"/",
			"/login",
			"/register",
			"/about",
			"/contact",
			"/newReleases",
			"/basketBallShoes",
			"/process_register",
			"/productPage",
			"/runningShoes",
			"/skateShoes",
			
	};
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests().
			/*antMatchers("/**").*/
			antMatchers(PUBLIC_MATCHERS).
			permitAll().anyRequest().authenticated();
		
		
			http
				.csrf().disable().cors().disable()
				.formLogin()
				.usernameParameter("username")
				.failureUrl("/login?error")
				.defaultSuccessUrl("/profile")
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").deleteCookies("remember-me").permitAll()
				.and()
				.rememberMe();
				
		
	}
	
	
	
	
}