package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Integer>{

}
