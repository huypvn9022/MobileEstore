package com.mobilestore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.CauHinh;
import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.SanPham;
import com.mobilestore.model.Top5SP;
import com.mobilestore.service.CTDHService;
import com.mobilestore.service.CauHinhService;
import com.mobilestore.service.HangSXService;
import com.mobilestore.service.HinhAnhService;
import com.mobilestore.service.LoaiSPService;
import com.mobilestore.service.SanPhamService;

@Controller
public class HomeController {
	
	@Autowired
	HangSXService hangSXService;
	
	@Autowired
	SanPhamService spService;
	
	@Autowired
	LoaiSPService loaispService;
	
	@Autowired
	HinhAnhService hinhanhService;
	
	@Autowired
	CauHinhService cauhinhService;
	
	@Autowired
	CTDHService chitietdonhangService;
	
	@RequestMapping("/index")
	public String list(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageableTop5SP = PageRequest.of(0, 5);
		Pageable pageableListSP = PageRequest.of(p.orElse(0), 15);
		List<Top5SP> top5sp = chitietdonhangService.getTop5(pageableTop5SP);
		Page<SanPham> listsp = spService.findAll(pageableListSP);
		List<HinhAnh> images = hinhanhService.findAll();
		
		model.addAttribute("top5sp", top5sp);
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

	@RequestMapping("/shop-grid")
	public String shopGrid(Model model) {
		return "layout/shop-grid";
	}
	
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		return "admin/index";
	}
}
