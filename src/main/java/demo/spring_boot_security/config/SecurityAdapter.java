package demo.spring_boot_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configuere....");
		http.authorizeRequests()
			.antMatchers("/regist/**", "/assets/**").permitAll()
			.antMatchers("/users/**").hasAuthority("admin")
			.anyRequest().fullyAuthenticated()
			.and()
			.formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").defaultSuccessUrl("/").permitAll()
			.and()
			.logout().logoutUrl("/logout").deleteCookies("remember-me").logoutSuccessUrl("/login").permitAll()
			.and()
			.rememberMe();
	}
	
//	private SecurityFilter securityFilter() {
//		SecurityFilter securityFilter = new SecurityFilter();
////		securityFilter.setAccessDecisionManager(accessDecisionManger());
////		securityFilter.setAuthenticationManager(authenticationManger());
//		return securityFilter;
//	}
//	
//	private AccessDecisionManagerImpl accessDecisionManger() {
//		AccessDecisionManagerImpl accessDecisionManger = new AccessDecisionManagerImpl();
//		return accessDecisionManger;
//	}
//	
//	private AuthenticationManager authenticationManger() {
//		AuthenticationProvider authenticationProvider = new AuthProvider();
//		
//		AuthenticationManager authenticationManger = null;
//		try {
//			authenticationManger = new AuthenticationManagerBuilder(null).authenticationProvider(authenticationProvider).build();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return authenticationManger;
//	}
}
