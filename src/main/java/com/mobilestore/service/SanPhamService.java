package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.SanPhamDAO;
import com.mobilestore.model.SanPham;

@Service
public class SanPhamService {

	@Autowired
	SanPhamDAO spdao;
	
	public List<SanPham> findAll(){
		return spdao.findAll();
	}

	public SanPham findById(Integer id) {
		return spdao.findById(id).get();
	}
	
	public Page<SanPham> findAll(Pageable pageable) {
		return spdao.findAll(pageable);
	}
}
