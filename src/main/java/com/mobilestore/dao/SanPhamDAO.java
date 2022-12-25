package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Integer>{
	
	@Query(value = "SELECT o FROM SanPham o WHERE o.daXoa = 0 and o.tenSP LIKE ?1")
	List <SanPham> findAllByKeyword(String keywords);
	
	@Query(value = "SELECT o FROM SanPham o WHERE o.daXoa = 0 ")
	List <SanPham> findAllSPDelete();
	
	@Query(value = "SELECT o FROM SanPham o WHERE o.daXoa = 0 and o.mahang.maHang like ?1")
	Page<SanPham> findAllByMaHang(String mahang,Pageable pageable);
	
	@Query(value = "SELECT o FROM SanPham o WHERE o.daXoa = 0 and o.tenSP LIKE ?1")
	Page <SanPham> findAllByKeywords(Pageable pageable, String keywords);
		
	@Query(value = "SELECT o FROM SanPham o WHERE o.daXoa = 0 and o.donGia  BETWEEN ?1 and ?2 ")
	Page <SanPham> findByDongia(Pageable pageable , Double  min, Double  max);
			
	@Query(value = "select o from SanPham o where o.daXoa = 0 and o.mach.ram like ?1 ")
	Page <SanPham> findAllByRam(Pageable pageable,String ram);
	
	@Query(value = "select o from SanPham o where o.daXoa = 0 and o.mach.rom like ?1")
	Page <SanPham> findAllByRom(Pageable pageable,String rom);

	@Query(value = "select o from SanPham o where o.daXoa = 0 and o.mach.heDH like ?1")
	Page <SanPham> findAllByHDH(Pageable pageable,String hedh);

	@Query(value = "SELECT o FROM SanPham o WHERE o.daXoa = 0 and o.maloai.maLoai like ?1")
	Page<SanPham> findAllByMaLoai2(Pageable pageable,String maloai);

	@Query(value = "SELECT o FROM SanPham o WHERE o.daXoa = 0 and o.maloai.maLoai like 'DT'")
	Page<SanPham> findAllByMaLoai(Pageable pageable);

	@Query(value = "SELECT o FROM SanPham o WHERE  o.daXoa = 0 and o.maloai.maLoai like 'PK'")
	List<SanPham> findAllByMaLoai1();
	

}
