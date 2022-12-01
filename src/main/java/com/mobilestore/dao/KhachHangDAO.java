package com.mobilestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.KhachHang;

public interface KhachHangDAO extends JpaRepository<KhachHang, String>{
	
	@Query("FROM KhachHang kh WHERE kh.taiKhoan=?1 OR kh.email=?1")
	KhachHang findAccountOrEmail(String taikhoan);
	
	
}
