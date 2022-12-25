package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.ChiTietDonHangDAO;
import com.mobilestore.model.ChiTietDonHang;
import com.mobilestore.model.DoanhThuNam;
import com.mobilestore.model.DoanhThuNgay;
import com.mobilestore.model.DoanhThuThang;
import com.mobilestore.model.Top5SP;

@Service
public class ThongKeService {
	@Autowired
	ChiTietDonHangDAO ctdhDAO;
	
	
	public Page<Top5SP> getTop5(Pageable pageable){
		return ctdhDAO.getTop5(pageable);
	}
	
	public List<Top5SP> getTopSP(){
		return ctdhDAO.getTopSP();
	}
	
	public List<DoanhThuThang> getDoanhThuThang(){
		return ctdhDAO.getDoanhThuThang();
	}
	
	public List<DoanhThuNam> getDoanhThuNam(){
		return ctdhDAO.getDoanhThuNam();
	}
	
	public List<DoanhThuNgay> getDoanhThuNgay(){
		return ctdhDAO.getDoanhThuNgay();
	}
	
	public List<ChiTietDonHang> findAll(){
		return ctdhDAO.findAll();
	}
}
