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
import com.mobilestore.service.CookieService;
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
	CookieService cookie;
	
	@Autowired
	NhanVienService nvservice;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(taikhoan -> {
			try {
				KhachHang khachhang = khservice.findById(taikhoan);
				String passwordkh = pe.encode(khachhang.getMatKhau());
				String role = khachhang.getVaiTroKH().getId();
				session.set("taiKhoanKH", khachhang.getTaiKhoan());
				cookie.add("taiKhoanKH", khachhang.getTaiKhoan(), 5);
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

				return User.withUsername(taikhoanNV).username(nhanvien.getHoTen()).password(passwordnv).roles(role).build();
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
			.antMatchers("/admin/**").hasAnyRole("NV", "QL")
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
}
