package edu.shop.config;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.shop.entity.Account;
import edu.shop.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	//Cung cấp dữ liệu đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Lấy username, password, roles -> userDetail
		auth.userDetailsService(username ->{
			try {
				//tìm kiếm user
				 Account user = accountService.getById(username);
				 
				 //Lấy password và mã hõa
				 String password = bCryptPasswordEncoder.encode(user.getPassword());
				 
				 //Lấy vai trò người dùng đưa vào mảng
				 String [] roles = user.getAuthorities().stream()
						 .map(er -> er.getRole().getId())
						 .collect(Collectors.toList()).toArray(new String[0]);
				 return User.withUsername(username).password(password).roles(roles).build();
			} catch ( NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found");
			}
		});
	}
	
	//Phân quyền sử dụng
	protected void configure(HttpSecurity http)throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests()
		
		//địa chỉ bắt đầu bằng order -> bắt buộc phải đăng nhập
		.antMatchers("/order/**").authenticated()
		.antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
		.antMatchers("/rest/authorities").hasRole("DIRE")
		.anyRequest().permitAll();
		
		http.formLogin()
		//địa chỉ dẫn đến trang login
		.loginPage("/security/login/form")
		
		//địa chỉ form login submit tới
		.loginProcessingUrl("/security/login")
		
		//
		.defaultSuccessUrl("/security/login/success", false)
		.failureUrl("/security/login/error");
		
		
		http.rememberMe()
		.tokenValiditySeconds(86400);
		
		//đăng nhập r nhưng chưa được cấp quyền
		// -> unauthoried
		http.exceptionHandling()
		.accessDeniedPage("/security/unauthoried");
		
		//đăng xuất
		http.logout()
		.logoutUrl("/security/logoff")
		.logoutSuccessUrl("/security/logoff/success");
	}
	
	
	//Mã hóa password
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//Cho phép truy xuất RESP API từ bên ngoài (domain khác)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST.OPTIONS,"/**");
	}
	
}
