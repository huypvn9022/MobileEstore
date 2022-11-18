package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.ChiTietDonHang;
import com.mobilestore.model.DoanhThuNam;
import com.mobilestore.model.DoanhThuNgay;
import com.mobilestore.model.DoanhThuThang;
import com.mobilestore.model.Top5SP;

public interface ChiTietDonHangDAO extends JpaRepository<ChiTietDonHang, Integer>{
	
	@Query("SELECT  new Top5SP(o.masp,count(o.soLuong)) "
			+ " FROM ChiTietDonHang o "
			+ " GROUP BY o.masp"
			+ " ORDER BY count(o.soLuong) DESC")
			Page<Top5SP> getTop5(Pageable pageable);
	
	@Query("SELECT  new DoanhThuThang(MONTH(o.madh.ngayTao),COUNT(o.madh.maDon), COUNT(o.masp.maSP),"
			+ " MIN(o.madh.tongTien) , MAX(o.madh.tongTien) , AVG(o.madh.tongTien) ,SUM(o.donGia)) "
			+ " FROM ChiTietDonHang o "
			+ " GROUP BY MONTH(o.madh.ngayTao)")
			Page<DoanhThuThang> getDoanhThuThang(Pageable pageable);
	
	@Query("SELECT  new DoanhThuNam(YEAR(o.madh.ngayTao),COUNT(o.madh.maDon), COUNT(o.masp.maSP),"
			+ " MIN(o.madh.tongTien) , MAX(o.madh.tongTien) , AVG(o.madh.tongTien) ,SUM(o.donGia)) "
			+ " FROM ChiTietDonHang o "
			+ " GROUP BY YEAR(o.madh.ngayTao)")
			Page<DoanhThuNam> getDoanhThuNam(Pageable pageable);
	
	@Query("SELECT  new DoanhThuNgay(COUNT(o.madh.maDon),COUNT(o.masp.maSP),SUM(o.donGia)) "
			+ " FROM ChiTietDonHang o "
			+ " WHERE o.madh.ngayTao = getdate()")
			List<DoanhThuNgay> getDoanhThuNgay();
}
