package com.mobilestore.dao;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Integer>{
	@Query(value = "SELECT o FROM SanPham o WHERE o.tenSP LIKE ?1")
	List <SanPham> findAllByKeyword(String keywords);

	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = ?1")
	Page<SanPham> findAllByMaHang(String mahang,Pageable pageable);
	
	@Query(value = "SELECT o FROM SanPham o WHERE o.tenSP LIKE ?1")
	Page <SanPham> findAllByKeywords(Pageable pageable, String keywords);
	
	@Query(value = "SELECT o FROM SanPham o WHERE o.donGia  BETWEEN '0' and '000000' ")
	Page <SanPham> findByDongia(Pageable pageable, Integer donGia);
	@Query(value = "SELECT o FROM SanPham o WHERE o.donGia  BETWEEN '2000000' and '4000000' ")
	Page <SanPham> findByDongia1(Pageable pageable, Integer donGia);
	@Query(value = "SELECT o FROM SanPham o WHERE o.donGia  BETWEEN '4000000' and '7000000' ")
	Page <SanPham> findByDongia2(Pageable pageable, Integer donGia);
	@Query(value = "SELECT o FROM SanPham o WHERE o.donGia  BETWEEN '7000000' and '13000000' ")
	Page <SanPham> findByDongia3(Pageable pageable, Integer donGia);
	@Query(value = "SELECT o FROM SanPham o WHERE o.donGia  BETWEEN '13000000' and '20000000' ")
	Page <SanPham> findByDongia4(Pageable pageable, Integer donGia);
	
	@Query(value = "SELECT o FROM SanPham o Order BY o.donGia  ")
	Page <SanPham> findByDongia5(Pageable pageable, Integer donGia);
	
	@Query(value = "SELECT o FROM SanPham o Order BY o.donGia  DESC")
	Page <SanPham> findByDongia6(Pageable pageable, Integer donGia);
	
	@Query(value = "select o from SanPham o where o.mach.ram like '4 GB'")
	Page <SanPham> findAllByRam(Pageable pageable);
	@Query(value = "select o from SanPham o where o.mach.ram like '6 GB'")
	Page <SanPham> findAllByRam1(Pageable pageable);
	@Query(value = "select o from SanPham o where o.mach.ram like '8 GB'")
	Page <SanPham> findAllByRam2(Pageable pageable);
	
	@Query(value = "select o from SanPham o where o.mach.rom like '32 GB'")
	Page <SanPham> findAllByRom(Pageable pageable);
	@Query(value = "select o from SanPham o where o.mach.rom like '64 GB'")
	Page <SanPham> findAllByRom1(Pageable pageable);
	@Query(value = "select o from SanPham o where o.mach.rom like '128 GB'")
	Page <SanPham> findAllByRom2(Pageable pageable);
	
	@Query(value = "select o from SanPham o where o.mach.heDH like 'iPhone (iOS)'")
	Page <SanPham> findAllByHDH(Pageable pageable);
	@Query(value = "select o from SanPham o where o.mach.heDH like 'Android'")
	Page <SanPham> findAllByHDH1(Pageable pageable);

	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = 'IP'")
	Page<SanPham> findAllByMaHang1(Pageable pageable);
	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = 'HW'")
	Page<SanPham> findAllByMaHang2(Pageable pageable);
	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = 'NK'")
	Page<SanPham> findAllByMaHang3(Pageable pageable);
	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = 'OP'")
	Page<SanPham> findAllByMaHang4(Pageable pageable);
	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = 'RM'")
	Page<SanPham> findAllByMaHang5(Pageable pageable);
	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = 'SN'")
	Page<SanPham> findAllByMaHang6(Pageable pageable);
	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = 'SS'")
	Page<SanPham> findAllByMaHang7(Pageable pageable);
	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = 'VV'")
	Page<SanPham> findAllByMaHang8(Pageable pageable);
	@Query(value = "SELECT o FROM SanPham o WHERE o.mahang.maHang = 'XM'")
	Page<SanPham> findAllByMaHang9(Pageable pageable);
	
	@Query(value = "SELECT o FROM SanPham o WHERE o.maloai.maLoai = 'DT'")
	Page<SanPham> findAllByMaLoai(Pageable pageable);
	
	@Query(value = "SELECT o FROM SanPham o WHERE o.maloai.maLoai = 'PK'")
	Page<SanPham> findAllByMaLoai1(Pageable pageable);
}
