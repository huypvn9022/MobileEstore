package com.mobilestore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobilestore.dao.ChiTietDonHangDAO;
import com.mobilestore.dao.DonHangDAO;
import com.mobilestore.model.ChiTietDonHang;
import com.mobilestore.model.DonHang;
import com.mobilestore.model.KhachHang;

@Service("donhang")
public class DonHangService {
	@Autowired
	DonHangDAO dao;

	@Autowired
	KhachHangService khService;

	@Autowired
	SessionService session;

	@Autowired
	ChiTietDonHangDAO ctdhdao;

	public List<DonHang> findAll() {
		return dao.findAll();
	}

	public DonHang findById(Integer maDon) {
		return dao.findById(maDon).get();
	}

	public Page<DonHang> findAllByTrangThaiLikeAndDaXoaFalse(Pageable pageable, String trangThai) {
		return dao.findAllByTrangThaiLikeAndDaXoaFalse(pageable, trangThai);
	}
	public Integer getCountChuaXuLy() {
		return dao.getCountChuaXuLy();
	}
	
	public Integer getCountHoanThanh() {
		return dao.getCountHoanThanh();
	}
	
	public Integer getCountHuyDon() {
		return dao.getCountHuyDon();
	}

	public DonHang save(DonHang maDon) {
		return dao.save(maDon);
	}

	public DonHang update(DonHang maDon) {
		return dao.save(maDon);
	}

	public void delete(Integer maDon) {
		dao.deleteById(maDon);
	}

	public Page<DonHang> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	public Boolean existsById(Integer maDon) {
		return dao.existsById(maDon);
	}

	public DonHang create(JsonNode order) {
		ObjectMapper mapper = new ObjectMapper();
		DonHang donhang = mapper.convertValue(order, DonHang.class);
		String tk = session.get("taiKhoanKH");
		KhachHang kh = khService.findById(tk);
		donhang.setMakh(kh);
		dao.save(donhang);
		TypeReference<List<ChiTietDonHang>> type = new TypeReference<List<ChiTietDonHang>>() {
		};
		List<ChiTietDonHang> chitietdh = mapper.convertValue(order.get("ctdh"), type).stream()
				.peek(d -> d.setMadh(donhang)).collect(Collectors.toList());
		ctdhdao.saveAll(chitietdh);
		return donhang;
	}
	
	public List<DonHang> findByUsername(String makh) {
		return dao.findByUsername(makh);
	}
}
