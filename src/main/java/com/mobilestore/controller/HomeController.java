package com.mobilestore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilestore.model.CauHinh;
import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.CauHinhService;
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
	
	@Autowired
	CauHinhService cauhinhService;
	
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
		double priceOld = sanpham.getDonGia() - ((sanpham.getDonGia() / 100) * 5);
		model.addAttribute("priceOld", priceOld);
		
		// hiện thị ảnh chính theo id
		HinhAnh anh = hinhanhService.findById(id);
		
		// Hiện thị ảnh chính giỏ hàng
		List<HinhAnh> anhCart = hinhanhService.findAll();
		
		// lưu các sản phẩm thành một list
		List<String> listAnh = new ArrayList<String>();
		listAnh.add(anh.getHinhAnh1());
		listAnh.add(anh.getHinhAnh2());
		listAnh.add(anh.getHinhAnh3());
		listAnh.add(anh.getHinhAnh4());
		
		// hiện thị cấu hình sản phẩm
		CauHinh cauHinh = cauhinhService.findById(id);
		
		// hiện thị các ảnh sản phẩm cùng hãng
		List<HinhAnh> listImg = hinhanhService.findAll(); 
		
		model.addAttribute("hangsx", hangSXService.findAll());
		model.addAttribute("anhs", anh);
		model.addAttribute("sanpham", sanpham);
		model.addAttribute("listAnh", listAnh);
		model.addAttribute("cauHinh", cauHinh);
		model.addAttribute("listImg", listImg);
		model.addAttribute("anhCart", anhCart);
		return "layout/ChiTietSanPham";
	}
	

	@RequestMapping("/cart")
	public String cart(Model model) {
		// hiện thị ảnh giỏ hàng
		List<HinhAnh> lisImg = hinhanhService.findAll();
		model.addAttribute("listImg" ,lisImg);	
		return "layout/giohang";
	} 
	
	// thanh toán
	@RequestMapping("/user/checkout")
	public String pay(Model model) {
		return "layout/thanhtoan";
	}

	
	@RequestMapping("/product-details")
	public String productDetails(Model model) {
		return "layout/ChiTietSanPham";
	}
	@RequestMapping("/register")
	public String register(Model model) {
		return "layout/dangky";
	}
	@RequestMapping("/login")
	public String login(Model model) {
		return "layout/dangnhap-dangky";
	}
	@RequestMapping("/change-password")
	public String changePassword(Model model) {
		return "layout/dmatkhau";
	}
	
	@RequestMapping("/order-management")
	public String orderManagement(Model model) {
		return "layout/qldonhang";
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
