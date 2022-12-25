package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.HinhAnh;

public interface HinhAnhDAO extends JpaRepository<HinhAnh, Integer>{
	
	@Query("SELECT o FROM HinhAnh o WHERE o.masp.tenSP like ?1")
	List<HinhAnh> findAllByKeyword(String tensp);
	
	@Query("SELECT o FROM HinhAnh o WHERE o.masp.maSP = ?1")
	List<HinhAnh> findByMaSP(int masp);
	
	Page<HinhAnh> findAllByDaXoaFalse(Pageable pageable);

	
}
