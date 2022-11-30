package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.NhanVien;

public interface NhanVienDAO extends JpaRepository<NhanVien, String>{
	@Query(value = "SELECT o FROM NhanVien o WHERE o.taiKhoan LIKE ?1")
	List <NhanVien> findAllByKeyword( String keywords);
}
