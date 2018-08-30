package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder encoder() {
		return new Pbkdf2PasswordEncoder("53cr3t");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/design", "/orders").hasRole("USER").antMatchers("/**").permitAll().antMatchers("/h2-console/**")
		.permitAll()
		.and()
		.formLogin().loginPage("/login").and().logout().logoutSuccessUrl("/design");
		
		//PER FAR FUNZIONARE h2-console -- NON USARE IN PRODUZIONE
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}

////CONFIGURAZIONE IN-MEMORY
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.inMemoryAuthentication().withUser("buzz").password("{noop}infinity").authorities("ROLE_USER").and()
//			.withUser("woody").password("{noop}bullseye").authorities("ROLE_USER");
//}

//// CONFIGURAZIONE CON STORAGE SU JDBC
//@Autowired
//DataSource dataSource;
//
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.jdbcAuthentication().dataSource(dataSource)
//			.usersByUsernameQuery("select username, password, enabled from Users " + "where username=?")
//			.authoritiesByUsernameQuery("select username, authority from UserAuthorities " + "where username=?")
//			.passwordEncoder(new Pbkdf2PasswordEncoder("53cr3t"));
//}


/*
 * By default, Spring Security listens for login requests
at "/login" and expects that the username and password fields be named "username"
and "password". This is configurable, however. For example, the following
configuration customizes the path and field names:
.and()
.formLogin()
.loginPage("/login")
.loginProcessingUrl("/authenticate")
.usernameParameter("user")
.passwordParameter("pwd")

Here, we’re specifying that Spring Security should listen for requests to "/authenticate"
to handle login submissions. Also the username and password fields should now be
named "user" and "pwd".

By default, a successful login will take the user directly to the page that they were
navigating to when Spring Security determined that they needed to login. If the user
were to directly navigate to the login page, then a successful login will take them to the
root path (e.g., the home page). But we can change that default by specifying a default
success page:
.and()
.formLogin()
.loginPage("/login")
.defaultSuccessUrl("/design")

As configured here, if the user were to successfully login after directly going to the
login page, then they will be directed to the design page. Optionally, we can force the
user to the design page always after login, even if they were navigating elsewhere prior
to login, by passing true as a second parameter to defaultSuccessUrl :
.and()
.formLogin()
.loginPage("/login")
.defaultSuccessUrl("/design", true)
 */



