package com.mobilestore.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.BinhLuan;
import com.mobilestore.model.KhachHang;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.BinhLuanService;
import com.mobilestore.service.KhachHangService;
import com.mobilestore.service.SanPhamService;

@Controller
public class BinhLuanController {
	@Autowired
	BinhLuanService blservice;
	
	@Autowired
	SanPhamService spservice;
	
	@Autowired
	KhachHangService khservice;

	@PostMapping("/binhluan")
	public String addComment(Model model,@RequestParam Integer maSP,@RequestParam String taiKhoan,
			@RequestParam String noiDung ) {
		BinhLuan binhluan = new BinhLuan();
		SanPham sanpham = spservice.findById(maSP);
		KhachHang khachhang = khservice.findById(taiKhoan);
		if(noiDung.isBlank()) {
			model.addAttribute("message", "Vui lòng nhập nội dung bình luận");
			return "forward:/product/detail/" + maSP;
		}else {
			Date date = new Date();
			binhluan.setTaikhoan(khachhang);
			binhluan.setNoiDung(noiDung);
			binhluan.setMasp(sanpham);
			binhluan.setNgayBL(date);
			blservice.addComment(binhluan);
			return "redirect:/product/detail/" + maSP;
		}

	}
}
