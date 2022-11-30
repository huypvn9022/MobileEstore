package com.mobilestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.NhanVien;

public interface NhanVienDAO extends JpaRepository<NhanVien, String>{
	
	@Query("FROM NhanVien nv WHERE nv.taiKhoan=?1 OR nv.email=?1")
	NhanVien findAccountOrEmail(String taikhoan);
	
	
}
