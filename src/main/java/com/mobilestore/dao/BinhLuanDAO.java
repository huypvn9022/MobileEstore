package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.BinhLuan;


public interface BinhLuanDAO extends JpaRepository<BinhLuan, Integer>{

	@Query("SELECT o FROM BinhLuan o WHERE o.masp.maSP = ?1")
	List<BinhLuan> findAllComment(int masp);
	
}
