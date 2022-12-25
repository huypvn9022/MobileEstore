package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.ChiTietDonHangDAO;
import com.mobilestore.model.ChiTietDonHang;
import com.mobilestore.model.Top5SP;

@Service("ctdh")
public class CTDHService {
	@Autowired
	ChiTietDonHangDAO dao;
	
	public List<ChiTietDonHang> findAll() {
		return dao.findAll();
	}
	
	public ChiTietDonHang findById(Integer maDon) {
		return dao.findById(maDon).get();
	}
	
	public ChiTietDonHang save(ChiTietDonHang maDon) {
		return dao.save(maDon);
	}
	
	public ChiTietDonHang update(ChiTietDonHang maDon) {
		return dao.save(maDon);
	}
	
	public void delete(Integer maDon) {
		 dao.deleteById(maDon);
	}
	
	public Double getTongTienByMadon(Integer maDon) {
		 return dao.getTongTienByMadon(maDon);
	}
	
	public void deleteAll(List<ChiTietDonHang> ctdh) {
		 dao.deleteAll(ctdh);
	}
	public List<ChiTietDonHang> getAllOrderDetail(Integer maDon) {
		return dao.getAllOrderDetail(maDon);
	}
	public Page<ChiTietDonHang> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	public Boolean existsById(Integer maDon) {
		return dao.existsById(maDon);
	}
	
	public List<Top5SP> getTop5(Pageable pageable) {
		return dao.getTop5(pageable).getContent();
	}
	
}
