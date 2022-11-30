package com.mobilestore.service;

import java.util.Collection;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import com.mobilestore.model.KhachHang;
public class TaiKhoanKHService implements UserDetails{
	
	@Autowired
	KhachHangService khservice;
	
	KhachHang kh;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return kh.getMatKhau();
	}

	@Override
	public String getUsername() {
		return kh.getTaiKhoan();
	}
	
	public String getFullname() {
		return kh.getHoTen();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
//	public KhachHang getAccount(Model model, Request request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Object account = auth.getPrincipal();
//		model.addAttribute("account", account);
//		return account instanceof KhachHang ? (KhachHang) account : null;
//	}
	
	
}
