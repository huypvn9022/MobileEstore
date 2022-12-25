package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.DonHang;

public interface DonHangDAO extends JpaRepository<DonHang, Integer>{
	
	@Query("SELECT o FROM DonHang o WHERE o.makh.taiKhoan LIKE ?1")
	List<DonHang> findByUsername(String makh);

	Page<DonHang> findAllByTrangThaiLikeAndDaXoaFalse(Pageable pageable,String trangThai);
	
	@Query("SELECT COUNT(o.maDon)  FROM DonHang o WHERE o.trangThai LIKE 'Processing' AND o.daXoa = 0")
	public Integer getCountChuaXuLy();
	
	@Query("SELECT COUNT(o.maDon)  FROM DonHang o WHERE o.trangThai LIKE 'Complete' AND o.daXoa = 0")
	public Integer getCountHoanThanh();
	
	@Query("SELECT COUNT(o.maDon)  FROM DonHang o WHERE o.trangThai LIKE 'Cancel' AND o.daXoa = 0")
	public Integer getCountHuyDon();
}

