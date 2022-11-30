package com.mobilestore.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.LoaiSanPham;

public interface LoaiSanPhamDAO extends JpaRepository<LoaiSanPham, String>{
	@Query(value = "SELECT o FROM LoaiSanPham o WHERE o.maLoai LIKE ?1")
	Page <LoaiSanPham> findAllByKeyword(Pageable pageable, String keywords);
}