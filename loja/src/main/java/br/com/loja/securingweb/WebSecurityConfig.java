package br.com.loja.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.loja.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	    private UsuarioService usuarioService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.and()
			.csrf().disable()
			.authorizeRequests()
				  .antMatchers("/finalizar").hasRole("USER")
				  .antMatchers("/pagamento").hasRole("USER")
				  .antMatchers("/cadastro").hasRole("ADMIN")
				  .antMatchers("/template").hasRole("USER")
				  .antMatchers("/carrinho/**").permitAll()
				  .antMatchers("/**").permitAll()
			.anyRequest().authenticated()
				.and()
			.formLogin()
				.failureUrl("/login-error")
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.permitAll()
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/resources/**", "/static/**","/webjars/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(usuarioService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
