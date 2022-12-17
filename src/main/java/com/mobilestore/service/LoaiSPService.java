package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.LoaiSanPhamDAO;
import com.mobilestore.model.LoaiSanPham;

@Service
public class LoaiSPService {
	@Autowired
	LoaiSanPhamDAO dao;
	
	public List<LoaiSanPham> findAll() {
		return dao.findAll();
	}
	
	public LoaiSanPham findById(String maLoai) {
		return dao.findById(maLoai).get();
	}
	
	public LoaiSanPham save(LoaiSanPham maLoai) {
		return dao.save(maLoai);
	}
	
	public LoaiSanPham update(LoaiSanPham maLoai) {
		return dao.save(maLoai);
	}
	
	public void delete(String maLoai) {
		 dao.deleteById(maLoai);
	}
	
	public Page<LoaiSanPham> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	public Page<LoaiSanPham> findAllByDaXoaFalse(Pageable pageable) {
		return dao.findAllByDaXoaFalse(pageable);
	}
	
	public Boolean existsById(String maLoai) {
		return dao.existsById(maLoai);
	}
	
	public Page<LoaiSanPham> findAllByKeyword(Pageable pageable, String keyword) {
		return dao.findAllByKeyword(pageable,keyword);
	}
	
}
