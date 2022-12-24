package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobilestore.dao.BinhLuanDAO;
import com.mobilestore.model.BinhLuan;
import com.mobilestore.model.SanPham;

@Service
public class BinhLuanService {

	@Autowired
	BinhLuanDAO binhluandao;
	
	@Autowired
	SanPhamService spservice;
	
	public List<BinhLuan> findAllComments(Integer masp){
		return binhluandao.findAllComment(masp);
	}

	public BinhLuan addComment(BinhLuan binhLuan) {
		return binhluandao.save(binhLuan);
	}
	
//	ObjectMapper mapper = new ObjectMapper();
//	BinhLuan bl = mapper.convertValue(binhLuan, BinhLuan.class);
//	binhluandao.save(bl);
	
}
