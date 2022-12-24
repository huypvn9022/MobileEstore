package com.mobilestore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mobilestore.model.BinhLuan;
import com.mobilestore.model.KhachHang;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.BinhLuanService;
import com.mobilestore.service.KhachHangService;
import com.mobilestore.service.SanPhamService;

@RestController
@CrossOrigin("*")
public class BinhLuanRestController {
	
	@Autowired
	BinhLuanService blservice;
	
	@Autowired
	SanPhamService spservice;
	
	@Autowired
	KhachHangService khservice;

	@PostMapping("/rest/binhluan")
	public BinhLuan addComment(@RequestBody BinhLuan binhLuan, @RequestParam("noidung") String noiDung ) {
		SanPham sanpham = spservice.findById(binhLuan.getMasp().getMaSP());
		binhLuan.setMasp(sanpham);
		KhachHang khachhang = khservice.findById(binhLuan.getTaikhoan().getTaiKhoan());
		binhLuan.setTaikhoan(khachhang);
		binhLuan.setNoiDung(noiDung);
		System.out.print(binhLuan.getNoiDung());
		return blservice.addComment(binhLuan);
	}
	
}
