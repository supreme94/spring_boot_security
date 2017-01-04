package demo.spring_boot_security.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import demo.spring_boot_security.filter.PreAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityAdapter extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(preAuthenticationProvider());//.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder())
	}
	
	private AuthenticationProvider preAuthenticationProvider() {
	    PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
	    provider.setPreAuthenticatedUserDetailsService(new SSOAuthenticationUserDetailsService());
	    return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configuere....");
	    http.
        csrf().disable().
        sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
        and().
        authorizeRequests().
        antMatchers("/","/index","/assets/**").permitAll().
        anyRequest().
        authenticated().
        and().
        exceptionHandling().
        authenticationEntryPoint(new RestAuthenticationEntryPoint());
	    
	    http.addFilter(headerAuthenticationFilter());
//		http
//			.csrf().disable()
//			.rememberMe().disable()
//			.authorizeRequests()
//			.antMatchers("/regist/**", "/assets/**").permitAll()
//			.antMatchers("/users/**").hasAuthority("admin")
//			.anyRequest().fullyAuthenticated()
//			.and()
//			.formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/").permitAll()
//			.and()
//			.logout().logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/login").permitAll();
	}
	
	@Bean
	public PreAuthenticationFilter headerAuthenticationFilter() throws Exception {
	    return new PreAuthenticationFilter(authenticationManager());
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
