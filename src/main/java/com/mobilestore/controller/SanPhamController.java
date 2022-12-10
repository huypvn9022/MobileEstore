package com.mobilestore.controller;

<<<<<<< HEAD
=======

import java.util.HashSet;
>>>>>>> fa02798044427e5e6946b3bb9ec00cf041470175
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


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
<<<<<<< HEAD


		model.addAttribute("hangsx", hangsxService.findAll());
=======
		
		Set<Integer> imgSet = new HashSet<Integer>();
		images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		
>>>>>>> fa02798044427e5e6946b3bb9ec00cf041470175
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
<<<<<<< HEAD
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


=======
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
>>>>>>> fa02798044427e5e6946b3bb9ec00cf041470175
}