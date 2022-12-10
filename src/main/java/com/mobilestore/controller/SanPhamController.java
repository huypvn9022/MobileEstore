package com.mobilestore.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.mobilestore.dao.SanPhamDAO;
import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.SanPham;
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
	SanPhamDAO spdao;
	
	@Autowired
	HinhAnhService hinhanhService;
	
	@RequestMapping
	public String list(Model model) {
		List<SanPham> listsp = spService.findAll();
		List<HinhAnh> images = hinhanhService.findAll();
		
		Set<Integer> imgSet = new HashSet<Integer>();
		images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		
		model.addAttribute("images", images);
		model.addAttribute("listsp", listsp);
		
		 return "layout/shop-grid";
	}
	@GetMapping("/{mahang}")
	public String iphone(@PathVariable("mahang") String mahang,Model model,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 8);
		System.out.println(mahang);
	
		List<HinhAnh> images = hinhanhService.findAll();
		Set<Integer> imgSet = new HashSet<Integer>();
		images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		
		Page<SanPham> products = spdao.findAllByMaHang(mahang,pageable);
		model.addAttribute("listsp",products);
		model.addAttribute("images", images);
		return "layout/shop-grid";
	}
	 @RequestMapping("/search")
	 public String search(Model model,@RequestParam("Keywords") Optional<String> kw,
				@RequestParam("p") Optional<Integer> p) {
		 
		 String kwords = kw.orElse(sessionService.get("keywords",""));
			sessionService.get("keywords",kwords);
			Pageable pageable = PageRequest.of(p.orElse(0), 15);
			Page<SanPham> products = spdao.findAllByKeywords(pageable,"%"+kwords+"%");
			List<HinhAnh> images = hinhanhService.findAll();
			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
			model.addAttribute("listsp",products);
			model.addAttribute("images", images);
		 return "layout/shop-grid";
	 }
	 
	 @RequestMapping("/dongia/tangdan")
	 public String dongia5(Model model,@RequestParam("p") Optional<Integer> p,Integer donGia) {
			Pageable pageable = PageRequest.of(p.orElse(0), 20);
			Page<SanPham> products = spdao.findByDongia5(pageable,donGia);
			List<HinhAnh> images = hinhanhService.findAll();
			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
			model.addAttribute("listsp",products);
			model.addAttribute("images", images);

		 return "layout/shop-grid";
	 }
	 @RequestMapping("/dongia/giamdan")
public String dongia6(Model model,@RequestParam("p") Optional<Integer> p,Integer donGia) {
			Pageable pageable = PageRequest.of(p.orElse(0), 20);
			Page<SanPham> products = spdao.findByDongia6(pageable,donGia);
			List<HinhAnh> images = hinhanhService.findAll();
			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
			model.addAttribute("listsp",products);
			model.addAttribute("images", images);

		 return "layout/shop-grid";
	 }
	 
	 
	 @RequestMapping("/dongia/0tr4tr")
	 public String dongia(Model model,@RequestParam("p") Optional<Integer> p,Integer donGia) {
			Pageable pageable = PageRequest.of(p.orElse(0), 8);
			Page<SanPham> products = spdao.findByDongia(pageable,donGia);
			List<HinhAnh> images = hinhanhService.findAll();
			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
			model.addAttribute("listsp",products);
			model.addAttribute("images", images);

		 return "layout/shop-grid";
	 }
	 @RequestMapping("/dongia/2tr4tr")
	 public String dongia1(Model model,@RequestParam("p") Optional<Integer> p,Integer donGia) {
			Pageable pageable = PageRequest.of(p.orElse(0), 8);
			Page<SanPham> products = spdao.findByDongia1(pageable,donGia);
			List<HinhAnh> images = hinhanhService.findAll();
			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
			model.addAttribute("listsp",products);
			model.addAttribute("images", images);

		 return "layout/shop-grid";
	 }
	 @RequestMapping("/dongia/4tr7tr")
	 public String dongia2(Model model,@RequestParam("p") Optional<Integer> p,Integer donGia) {
			Pageable pageable = PageRequest.of(p.orElse(0), 8);
			Page<SanPham> products = spdao.findByDongia2(pageable,donGia);
			List<HinhAnh> images = hinhanhService.findAll();
			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
			model.addAttribute("listsp",products);
			model.addAttribute("images", images);

		 return "layout/shop-grid";
	 }
	 @RequestMapping("/dongia/7tr13tr")
	 public String dongia3(Model model,@RequestParam("p") Optional<Integer> p,Integer donGia) {
			Pageable pageable = PageRequest.of(p.orElse(0), 8);
			Page<SanPham> products = spdao.findByDongia3(pageable,donGia);
			List<HinhAnh> images = hinhanhService.findAll();
			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
			model.addAttribute("listsp",products);
			model.addAttribute("images", images);

		 return "layout/shop-grid";
	 }
	 @RequestMapping("/dongia/13tr20tr")
	 public String dongia4(Model model,@RequestParam("p") Optional<Integer> p,Integer donGia) {
			Pageable pageable = PageRequest.of(p.orElse(0), 8);
			Page<SanPham> products = spdao.findByDongia4(pageable,donGia);
			List<HinhAnh> images = hinhanhService.findAll();
			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
			model.addAttribute("listsp",products);
			model.addAttribute("images", images);

		 return "layout/shop-grid";
	 }
	 
	 @RequestMapping("/ram/4gb")
	 public String ram4(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByRam(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/ram/6gb")
	 public String ram6(Model model,@RequestParam("p") Optional<Integer> p) {
Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByRam1(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/ram/8gb")
	 public String ram8(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByRam2(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 
	 @RequestMapping("/rom/32gb")
	 public String rom(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByRom(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/rom/64gb")
	 public String rom1(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByRom1(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/rom/128gb")
	 public String rom2(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByRom2(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 
	 @RequestMapping("/hedh/iphone")
	 public String hedh(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByHDH(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/hedh/android")
	 public String hedh1(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 15);
		 Page<SanPham> products = spdao.findAllByHDH1(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/hang/IP")
	 public String hangSX(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByMaHang1(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
return "layout/shop-grid";
	}
	 @RequestMapping("/hang/HW")
	 public String hangSX1(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByMaHang2(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/hang/NK")
	 public String hangSX2(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByMaHang3(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/hang/OP")
	 public String hangSX3(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByMaHang4(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/hang/RM")
	 public String hangSX4(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByMaHang5(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/hang/SN")
	 public String hangSX5(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByMaHang6(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/hang/SS")
	 public String hangSX6(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByMaHang7(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/hang/VV")
	 public String hangSX7(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
		 Page<SanPham> products = spdao.findAllByMaHang8(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/hang/XM")
	 public String hangSX8(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0), 8);
Page<SanPham> products = spdao.findAllByMaHang9(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 
	 @RequestMapping("/loaisp/DT")
	 public String searchloaiSP(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0),15);
		 Page<SanPham> products = spdao.findAllByMaLoai(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
	 @RequestMapping("/loaisp/PK")
	 public String searchloaiSP1(Model model,@RequestParam("p") Optional<Integer> p) {
		 Pageable pageable = PageRequest.of(p.orElse(0),15);
		 Page<SanPham> products = spdao.findAllByMaLoai1(pageable);
		 List<HinhAnh> images = hinhanhService.findAll();
		 Set<Integer> imgSet = new HashSet<Integer>();
		 images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		 model.addAttribute("listsp",products);
		 model.addAttribute("images", images);
		 return "layout/shop-grid";
	}
}