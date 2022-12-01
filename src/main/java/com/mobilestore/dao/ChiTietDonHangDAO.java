package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.ChiTietDonHang;
import com.mobilestore.model.Top5SP;

public interface ChiTietDonHangDAO extends JpaRepository<ChiTietDonHang, Integer>{
	@Query("SELECT  new Top5SP(o.masp,count(o.soLuong))"
			+ " FROM ChiTietDonHang o"
			+ " GROUP BY o.masp"
			+ " ORDER BY count(o.soLuong) DESC")
			Page<Top5SP> getTop5(Pageable pageable);
	
	@Query("SELECT o from ChiTietDonHang o where o.madon.maDon = ?1")
	public List<ChiTietDonHang> getAllOrderDetail(int maDon);
}
