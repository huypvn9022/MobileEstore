package com.mobilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilestore.dao.HinhAnhDAO;
import com.mobilestore.dao.SanPhamDAO;
import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.LoaiSanPham;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.HangSanXuatService;
import com.mobilestore.service.HinhAnhService;
import com.mobilestore.service.LoaiSanPhamService;
import com.mobilestore.service.SanPhamService;

@Controller
public class HomeController {
	
	@Autowired
	HangSanXuatService hangSXService;
	
	@Autowired
	SanPhamService spService;
	
	@Autowired
	LoaiSanPhamService loaispService;
	
	@Autowired
	HinhAnhService hinhanhService;
	
	@RequestMapping("/index")
	public String list(Model model) {
		List<SanPham> listsp = spService.findAll();
		List<HinhAnh> images = hinhanhService.findAll();
	
		model.addAttribute("images", images);
		model.addAttribute("listsp", listsp);
		
		return "layout/index";
	}
	
	
	// product detail
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		SanPham sanpham = spService.findById(id);
		List<HinhAnh> anh = hinhanhService.findAll();
		model.addAttribute("anh", anh);
		model.addAttribute("sanpham", sanpham);
		return "layout/ChiTietGioHang";
	}
	
	
	@RequestMapping("/product-details")
	public String productDetails(Model model) {
		return "layout/ChiTietGioHang";
	}
	@RequestMapping("/register")
	public String dangky(Model model) {
		return "layout/dangky";
	}
	@RequestMapping("/login")
	public String register(Model model) {
		return "layout/dangnhap";
	}
	@RequestMapping("/change-password")
	public String changePassword(Model model) {
		return "layout/dmatkhau";
	}
	@RequestMapping("/cart")
	public String cart(Model model) {
		return "layout/giohang";
	}
	@RequestMapping("order-management")
	public String orderManagement(Model model) {
		return "layout/qldonhang";
	}
	@RequestMapping("/forgot-password")
	public String forgotPassword(Model model) {
		return "layout/qmatkhau";
	}
	@RequestMapping("/shop-grid")
	public String shopGrid(Model model) {
		return "layout/shop-grid";
	}
	@RequestMapping("account-information")
	public String accoutInformation(Model model) {
		return "layout/tttaikhoan";
	}
	
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		return "admin/index";
	}
	
	
}
