package com.mobilestore.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import com.mobilestore.dao.HangSanXuatDAO;
import com.mobilestore.dao.SanPhamDAO;
import com.mobilestore.model.HangSanXuat;
import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.HangSanXuatService;
import com.mobilestore.service.HinhAnhService;
import com.mobilestore.service.SessionService;



@Controller
@RequestMapping("/product")
public class SanPhamController {
	@Autowired
	SessionService sessionService;
	
	@Autowired
	HangSanXuatService hangService;
	
	@Autowired
	SanPhamDAO spdao;
	
	@Autowired
	HinhAnhService hinhanhService;
	
	
	@GetMapping("/{mahang}")
	public String iphone(@PathVariable("mahang") String mahang,Model model,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 8);
		System.out.println(mahang);
	
		List<HinhAnh> images = hinhanhService.findAll();
		Page<SanPham> products = spdao.findAllByMaHang(mahang,pageable);
		model.addAttribute("listsp",products);
		model.addAttribute("images", images);
		return "layout/shop-grid";
	}
	 @RequestMapping("/search")
	 public String search(Model model,@RequestParam("Keywords") Optional<String> kw,
				@RequestParam("p") Optional<Integer> p) {
		 List<HinhAnh> images = hinhanhService.findAll();
		 String kwords = kw.orElse(sessionService.get("keywords",""));
			sessionService.get("keywords",kwords);
			Pageable pageable = PageRequest.of(p.orElse(0), 15);
			Page<SanPham> products = spdao.findAllByKeywords(pageable,"%"+kwords+"%");
			model.addAttribute("listsp",products);
			model.addAttribute("images", images);
		 return "layout/shop-grid";
	 }
}