package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobilestore.dao.BinhLuanDAO;
import com.mobilestore.model.BinhLuan;
import com.mobilestore.model.LoaiSanPham;
import com.mobilestore.model.SanPham;

@Service
public class BinhLuanService {

	@Autowired
	BinhLuanDAO binhluandao;
		
	public Page<BinhLuan> findAll(Pageable pageable) {
		return binhluandao.findAll(pageable);
	}
	
	public List<BinhLuan> findAllComments(Integer masp){
		return binhluandao.findAllComment(masp);
	}

	public BinhLuan addComment(BinhLuan binhLuan) {
		return binhluandao.save(binhLuan);
	}
	
	public void delete(Integer maBL) {
		binhluandao.deleteById(maBL);
	}
	
	public BinhLuan findByID(Integer maBL) {
		return binhluandao.findById(maBL).get();
	}
	
	public BinhLuan save(BinhLuan binhLuan) {
		return binhluandao.save(binhLuan);
	}

}
