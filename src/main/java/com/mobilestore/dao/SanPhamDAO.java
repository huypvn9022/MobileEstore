package com.mobilestore.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Integer>{
	@Query(value = "SELECT o FROM SanPham o WHERE o.maHang.maHang = ?1")
	Page<SanPham> findAllByMaHang(String mahang,Pageable pageable);
	
	@Query(value = "SELECT o FROM SanPham o WHERE o.tenSP LIKE ?1")
	Page <SanPham> findAllByKeywords(Pageable pageable, String keywords);
}
