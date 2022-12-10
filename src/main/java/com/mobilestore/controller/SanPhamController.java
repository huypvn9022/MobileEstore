package com.mobilestore.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.dao.SanPhamDAO;
import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.HangSXService;
import com.mobilestore.service.HinhAnhService;
import com.mobilestore.service.SanPhamService;
import com.mobilestore.service.SessionService;

@Controller
@RequestMapping("/product")
public class SanPhamController {
	@Autowired
	SessionService sessionService;

	@Autowired
	SanPhamService spService;

	@Autowired
	HinhAnhService hinhanhService;

	@Autowired
	HangSXService hangsxService;

//	@RequestMapping
//	public String list(Model model,@RequestParam("sort") Optional<String> sort1,
//			@RequestParam("field") Optional<String> field,
//			@RequestParam("p") Optional<Integer> p) {	
//		String sr = sort1.orElse(sessionService.get("sort",""));
//		Pageable pageable = PageRequest.of(p.orElse(0), 20);
//
//		if(sr.equals("DESC")) {
//		Sort sort = Sort.by("donGia").descending();
//
//		List <SanPham> products = dao.findAll(sort);
//		List<HinhAnh> images = hinhanhService.findAll();
//
//		Page <SanPham> product = dao.findAll(pageable);
//		
//		model.addAttribute("hangsx", hangsxService.findAll());
//		model.addAttribute("images", images);
//		model.addAttribute("listsp", products);
//		return "layout/shop-grid";
//		}else if(sr.equals("ASC")){
//			Sort sort = Sort.by(Direction.ASC, field.orElse("donGia"));
//
//			Page <SanPham> products = dao.findAll(pageable,sort);
//			List<HinhAnh> images = hinhanhService.findAll();
//
//
//			model.addAttribute("hangsx", hangsxService.findAll());
//			model.addAttribute("images", images);
//			model.addAttribute("listsp", products);
//			return "layout/shop-grid";
//		}else {
//
//			List <SanPham> products = dao.findAll();
//			List<HinhAnh> images = hinhanhService.findAll();
//
//
//			model.addAttribute("hangsx", hangsxService.findAll());
//			model.addAttribute("images", images);
//			model.addAttribute("listsp", products);
//			return "layout/shop-grid";
//			}
//		
//	}

	@RequestMapping
	public String sp_Index( Model model) {
		List <SanPham> products = spService.findAll();
		List<HinhAnh> images = hinhanhService.findAll();


		model.addAttribute("hangsx", hangsxService.findAll());
		model.addAttribute("images", images);
		model.addAttribute("listsp", products);
		return "layout/shop-grid";
	}
	
	@RequestMapping("/search")
	public String sp_Search(Model model, @RequestParam("Keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p) {

		String kwords = kw.orElse(sessionService.get("keywords", ""));
		sessionService.get("keywords", kwords);
		Pageable pageable = PageRequest.of(p.orElse(0), 20);
		Page<SanPham> products = spService.findAllByKeyword(pageable, "%" + kwords + "%");
		List<HinhAnh> images = hinhanhService.findAll();
		if (products == null) {
			model.addAttribute("message", "Không có sản phẩm");
			return "layout/shop-grid";
		} else {
			model.addAttribute("listsp", products);
			model.addAttribute("images", images);

			return "layout/shop-grid";
		}
	}

	@RequestMapping("/{mahang}")
	public String sp_Hang(@PathVariable("mahang") String mahang, Model model, @RequestParam("p") Optional<Integer> p) {
		sessionService.set("mahang", mahang);

		Pageable pageable = PageRequest.of(p.orElse(0), 20);

		System.out.println(mahang);

		List<HinhAnh> images = hinhanhService.findAll();
		Page<SanPham> products = spService.findAllByMaHang(mahang, pageable);

		model.addAttribute("listsp", products);
		model.addAttribute("images", images);
		return "layout/shop-grid";

	}

	@RequestMapping("/dongia/{min}den{max}")
	public String sp_DonGia(Model model, @RequestParam("p") Optional<Integer> p,@PathVariable("min") Double min,@PathVariable("max") Double  max) {
		Pageable pageable = PageRequest.of(p.orElse(0), 20);
		Page<SanPham> products = spService.findByDongia(pageable,min,max);
		List<HinhAnh> images = hinhanhService.findAll();
		model.addAttribute("listsp", products);
		model.addAttribute("images", images);
		return "layout/shop-grid";
	}

	@RequestMapping("/ram/{ram}")
	public String sp_Ram(Model model, @RequestParam("p") Optional<Integer> p,@PathVariable("ram") String ram) {
		Pageable pageable = PageRequest.of(p.orElse(0), 20);

		Page<SanPham> products = spService.findAllByRam(pageable, "%" + ram + "%");
		List<HinhAnh> images = hinhanhService.findAll();

		model.addAttribute("listsp", products);
		model.addAttribute("images", images);
		return "layout/shop-grid";
	}


	@RequestMapping("/rom/{rom}")
	public String sp_Rom(Model model, @RequestParam("p") Optional<Integer> p,@PathVariable("rom") String rom) {
		Pageable pageable = PageRequest.of(p.orElse(0), 20);
		Page<SanPham> products = spService.findAllByRom(pageable, "%" + rom + "%");
		List<HinhAnh> images = hinhanhService.findAll();
		model.addAttribute("listsp", products);
		model.addAttribute("images", images);
		return "layout/shop-grid";
	}

	@RequestMapping("/hedh/{hedh}")
	public String sp_HeDh(Model model, @RequestParam("p") Optional<Integer> p,@PathVariable("hedh") String hedh) {
		Pageable pageable = PageRequest.of(p.orElse(0), 20);
		Page<SanPham> products = spService.findAllByHDH(pageable, "%" + hedh + "%");
		List<HinhAnh> images = hinhanhService.findAll();
		model.addAttribute("listsp", products);
		model.addAttribute("images", images);
		return "layout/shop-grid";
	}

	@RequestMapping("/loaisp/{maloai}")
	public String sp_LoaiSP(Model model, @RequestParam("p") Optional<Integer> p,@PathVariable("maloai") String maloai) {
		Pageable pageable = PageRequest.of(p.orElse(0), 20);
		Page<SanPham> products = spService.findAllByMaLoai2(pageable, "%" + maloai + "%");
		List<HinhAnh> images = hinhanhService.findAll();
		model.addAttribute("listsp", products);
		model.addAttribute("images", images);

		return "layout/shop-grid";
	}


}