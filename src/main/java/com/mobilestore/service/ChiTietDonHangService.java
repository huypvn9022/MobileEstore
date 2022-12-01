package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.ChiTietDonHangDAO;
import com.mobilestore.model.ChiTietDonHang;
import com.mobilestore.model.SanPham;
import com.mobilestore.model.Top5SP;

@Service
public class ChiTietDonHangService {
	@Autowired
	ChiTietDonHangDAO chitietdonhangdao;

	public List<Top5SP> getTop5(Pageable pageable) {
		return chitietdonhangdao.getTop5(pageable).getContent();
	}
	
	public List<ChiTietDonHang> getAllOrderDetail(Integer maDon) {
		return chitietdonhangdao.getAllOrderDetail(maDon);
	}
	
	public ChiTietDonHang findById(Integer id) {
		return chitietdonhangdao.findById(id).get();
	}
	
	public List<ChiTietDonHang> findAll() {
		return chitietdonhangdao.findAll();
	}
	
}
