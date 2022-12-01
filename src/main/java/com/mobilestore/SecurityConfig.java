package com.mobilestore;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mobilestore.model.KhachHang;
import com.mobilestore.model.NhanVien;
import com.mobilestore.service.KhachHangService;
import com.mobilestore.service.NhanVienService;
import com.mobilestore.service.SessionService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	KhachHangService khservice;
	
	@Autowired
	SessionService session;
	
	@Autowired
	NhanVienService nvservice;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	// cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// cung cấp nguồn dữ liệu đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(taikhoan -> {
			try {
				KhachHang khachhang = khservice.findById(taikhoan);
				String passwordkh = pe.encode(khachhang.getMatKhau());
				String role = khachhang.getVaiTroKH().getId();
				session.set("taiKhoanKH", khachhang.getTaiKhoan());
				session.set("matKhauKH", khachhang.getMatKhau());
				session.set("vaiTroKH", role);

				return User.withUsername(taikhoan).username(khachhang.getHoTen()).password(passwordkh).roles(role).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(taikhoan + " not found !");
			}
		});
		
		auth.userDetailsService(taikhoanNV -> {
			try {
				NhanVien nhanvien = nvservice.findById(taikhoanNV);
				String passwordnv = pe.encode(nhanvien.getMatKhau());
				String role = nhanvien.getVaiTroNV().getId();
				session.set("taiKhoanNV", nhanvien.getTaiKhoan());

				return User.withUsername(taikhoanNV).password(passwordnv).roles(role).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(taikhoanNV + " not found !");
			}
		});
		
	}
	
	// phân quyền
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors().disable();
		http.authorizeRequests()
			.antMatchers("/user/checkout").authenticated()
			.antMatchers("/admin/index").hasAnyRole("NV", "QL")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/security/form/login")
			.loginProcessingUrl("/form/login")
			.defaultSuccessUrl("/form/login/success", false)
			.failureUrl("/form/login/fail");
		
		http.rememberMe()
			.tokenValiditySeconds(1*24*60*60);
		
		http.exceptionHandling().accessDeniedPage("/security/access/denied");
		
		http.logout()
			.logoutUrl("/form/logout")
			.logoutSuccessUrl("/security/form/logout");
		
		
	}
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//		
//		httpSecurity.csrf().disable();
//		httpSecurity.cors().disable();
//		
//		httpSecurity.authorizeHttpRequests()
//					.antMatchers("/user/checkout").authenticated()
//					.anyRequest().permitAll();
//		
//		httpSecurity.exceptionHandling().accessDeniedPage("/security/access/denied");
//		
//		httpSecurity.formLogin()
//					.loginPage("/security/form/login")
//					.loginProcessingUrl("/form/login")
//					.defaultSuccessUrl("/form/login/success")
//					.failureUrl("/form/login/fail");
//		
//		httpSecurity.rememberMe().tokenValiditySeconds(1*24*60*60);
//		
//		/**		
//		httpSecurity.logout()
//					.logoutUrl("/form/logout")
//					.logoutSuccessUrl("/security/form/logout")
//					.addLogoutHandler(new SecurityContextLogoutHandler());
//		**/
//		
//		
//		
//		return httpSecurity.build();
//		
//	}
	
}
