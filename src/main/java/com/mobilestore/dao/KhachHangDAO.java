package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.KhachHang;


public interface KhachHangDAO extends JpaRepository<KhachHang, String>{
	
	@Query(value = "SELECT o FROM KhachHang o WHERE o.taiKhoan LIKE ?1 OR o.hoTen LIKE ?1")
	List <KhachHang> findAllByKeyword( String keywords);
	
	@Query("FROM KhachHang kh WHERE kh.taiKhoan=?1 OR kh.email=?1")
	KhachHang findAccountOrEmail(String taikhoan);
	
	Page<KhachHang> findAllByDaXoaFalse(Pageable pageable);
	
}
