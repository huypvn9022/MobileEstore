package com.mobilestore.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.entity.KhachHang;

public interface KhachHangDAO extends JpaRepository<KhachHang, String>{
	@Query(value = "SELECT o FROM KhachHang o WHERE o.taiKhoan LIKE ?1")
	Page <KhachHang> findAllByKeyword(Pageable pageable, String keywords);
}
