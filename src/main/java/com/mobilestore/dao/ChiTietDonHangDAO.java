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
	
	@Query("SELECT new Top5SP(o.masp,count(o.soLuong)) "
			+ " FROM ChiTietDonHang o "
			+ " GROUP BY o.masp"
			+ " ORDER BY count(o.soLuong) DESC")
			Page<Top5SP> getTop5(Pageable pageable);
	
	@Query("SELECT new Top5SP(o.masp,count(o.soLuong)) "
			+ " FROM ChiTietDonHang o "
			+ " GROUP BY o.masp"
			+ " ORDER BY count(o.soLuong) DESC")
			List<Top5SP> getTopSP();
	
	@Query("SELECT  new DoanhThuThang(MONTH(o.madh.ngayTao),COUNT(o.madh.maDon), COUNT(o.masp.maSP),"
			+ " MIN(o.madh.tongTien) , MAX(o.madh.tongTien) , AVG(o.madh.tongTien) ,SUM(o.donGia)) "
			+ " FROM ChiTietDonHang o "
			+ " GROUP BY MONTH(o.madh.ngayTao)")
			List<DoanhThuThang> getDoanhThuThang();
	
	@Query("SELECT  new DoanhThuNam(YEAR(o.madh.ngayTao),COUNT(o.madh.maDon), COUNT(o.masp.maSP),"
			+ " MIN(o.madh.tongTien) , MAX(o.madh.tongTien) , AVG(o.madh.tongTien) ,SUM(o.donGia)) "
			+ " FROM ChiTietDonHang o "
			+ " GROUP BY YEAR(o.madh.ngayTao)")
			List<DoanhThuNam> getDoanhThuNam();
	
	@Query("SELECT  new DoanhThuNgay(COUNT(DISTINCT(o.madh.maDon)),COUNT(o.masp.maSP),SUM(o.donGia)) "
			+ " FROM ChiTietDonHang o "
			+ " WHERE DAY(o.madh.ngayTao) = DAY(getdate())")
			List<DoanhThuNgay> getDoanhThuNgay();
	
	@Query("SELECT SUM(o.donGia * o.soLuong) FROM ChiTietDonHang o " + " WHERE o.madh.maDon = ?1")
	public Double getTongTienByMadon(int maDon);
	
	@Query("SELECT o from ChiTietDonHang o where o.madh.maDon = ?1")
	public List<ChiTietDonHang> getAllOrderDetail(int maDon);
}
