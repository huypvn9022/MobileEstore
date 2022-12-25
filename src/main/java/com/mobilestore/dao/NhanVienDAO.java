package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.NhanVien;

public interface NhanVienDAO extends JpaRepository<NhanVien, String>{
	
	@Query(value = "SELECT o FROM NhanVien o WHERE o.taiKhoan LIKE ?1 OR o.hoTen LIKE ?1")
	List <NhanVien> findAllByKeyword( String keywords);

	@Query("FROM NhanVien nv WHERE nv.taiKhoan=?1 OR nv.email=?1")
	NhanVien findAccountOrEmail(String taikhoan);	
	
	Page<NhanVien> findAllByDaXoaFalse(Pageable pageable);

}
