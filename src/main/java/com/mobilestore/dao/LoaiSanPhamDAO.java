package com.mobilestore.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.LoaiSanPham;
import com.mobilestore.model.NhanVien;

public interface LoaiSanPhamDAO extends JpaRepository<LoaiSanPham, String>{
	
	@Query(value = "SELECT o FROM LoaiSanPham o WHERE o.maLoai LIKE ?1 OR o.tenLoai LIKE ?1")
	Page <LoaiSanPham> findAllByKeyword(Pageable pageable, String keywords);
	
	Page<LoaiSanPham> findAllByDaXoaFalse(Pageable pageable);

}