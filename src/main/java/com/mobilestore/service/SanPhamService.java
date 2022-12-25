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
	SanPhamDAO dao;
	
	public List<SanPham> findAll() {
		return dao.findAll();
	}
	
	public Page<SanPham> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	public SanPham findById(Integer maSP) {
		return dao.findById(maSP).get();
	}
	
	public SanPham save(SanPham maSP) {
		return dao.save(maSP);
	}
	
	public SanPham update(SanPham maSP) {
		return dao.save(maSP);
	}
	
	public void delete(Integer maSP) {
		 dao.deleteById(maSP);
	}
	
	public Boolean existsById(Integer maSP) {
		return dao.existsById(maSP);
	}
	
	public Page<SanPham> findAllByKeyword(Pageable pageable, String keywords) {
		return dao.findAllByKeywords(pageable, keywords);
	}
	 public Page<SanPham> findAllByMaHang(String mahang,Pageable pageable){
		 return dao.findAllByMaHang(mahang,pageable);
	 }
	 public Page <SanPham> findAllByRam(Pageable pageable,String ram){
			return dao.findAllByRam(pageable,ram);
		}
	 public Page <SanPham> findAllByRom(Pageable pageable,String rom){
			return dao.findAllByRom(pageable,rom);
		}
	public 	Page <SanPham> findByDongia(Pageable pageable , Double  min, Double  max){
		return dao.findByDongia(pageable ,min,max);
	}

	public Page<SanPham> findAllByHDH(Pageable pageable, String hedh) {
		return dao.findAllByHDH(pageable,hedh);
	}

	public Page<SanPham> findAllByMaLoai2(Pageable pageable, String maloai) {
		return dao.findAllByMaLoai2(pageable, maloai);
	}


	public Page<SanPham> findAllByMaLoai(Pageable pageable){
		return dao.findAllByMaLoai(pageable);
	}
	
	public List<SanPham> findAllByMaLoai1(){
		return dao.findAllByMaLoai1();
	}
}
