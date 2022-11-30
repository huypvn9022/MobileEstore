package com.mobilestore.service;

import org.springframework.beans.factory.annotation.Autowired;

public class TaiKhoanNVService{

	@Autowired
	NhanVienService nvservice;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		try {
//			return nvservice.findAccountorEmail(username);
//		} catch (Exception e) {
//			throw new UsernameNotFoundException(username + " not found !");
//		}
//	}
//
//	@Override
//	public void createUser(UserDetails user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateUser(UserDetails user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteUser(String username) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void changePassword(String oldPassword, String newPassword) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean userExists(String username) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	public NhanVien getAccount() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Object account = auth.getPrincipal();
//		return account instanceof NhanVien ? (NhanVien) account : null; 
//	}
//	
//	public NhanVien setAccount() {
//		return null;
//	}

}
