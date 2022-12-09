package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.DonHang;

public interface DonHangDAO extends JpaRepository<DonHang, Integer>{
	@Query("SELECT o FROM DonHang o WHERE o.makh.taiKhoan LIKE ?1")
	List<DonHang> findByUsername(String makh);
}
