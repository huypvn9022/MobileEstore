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
	SanPhamDAO dao;

	@Autowired
	HangSXService hangsxService;

	@RequestMapping
	public String list(Model model, @RequestParam("sort") Optional<String> sort1,
			@RequestParam("field") Optional<String> field, @RequestParam("p") Optional<Integer> p) {
		Sort sort = Sort.by("donGia");
		String sr = sort1.orElse(sessionService.get("sort", ""));

		if (sr.equals("DESC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.descending());

			Page<SanPham> products = dao.findAll(pageable);
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else if (sr.equals("ASC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.ascending());
			Page<SanPham> products = dao.findAll(pageable);
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);
			Page<SanPham> products = dao.findAll(pageable);
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		}

	}

	@RequestMapping("/search")
	public String sp_Search(Model model, @RequestParam("Keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p) {

		String kwords = kw.orElse(sessionService.get("keywords", ""));
		sessionService.get("keywords", kwords);
		Pageable pageable = PageRequest.of(p.orElse(0), 15);
		Page<SanPham> products = spService.findAllByKeyword(pageable, "%" + kwords + "%");
		List<HinhAnh> images = hinhanhService.findAll();

		Set<Integer> imgSet = new HashSet<Integer>();
		images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

		model.addAttribute("listsp", products);
		model.addAttribute("images", images);

		return "layout/shop-grid";

	}

	@RequestMapping("/{mahang}")
	public String sp_Hang(Model model,
			@PathVariable("mahang") String mahang, 
			@RequestParam("sort") Optional<String> sort1,
			@RequestParam("field") Optional<String> field,
			@RequestParam("p") Optional<Integer> p) {
		System.out.println(mahang);
				
		Sort sort = Sort.by("donGia");
		String sr = sort1.orElse(sessionService.get("sort", ""));
		System.out.print(sort1);

		if (sr.equals("DESC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.descending());

			Page<SanPham> products = spService.findAllByMaHang(mahang, pageable);
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else if (sr.equals("ASC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.ascending());
			Page<SanPham> products = spService.findAllByMaHang(mahang, pageable);
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);
			Page<SanPham> products = spService.findAllByMaHang(mahang, pageable);
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		}	
	}

	@RequestMapping("/dongia/{min}den{max}")
	public String sp_DonGia(Model model,
			@RequestParam("sort") Optional<String> sort1,
			@RequestParam("field") Optional<String> field, 
			@RequestParam("p") Optional<Integer> p, 
			@PathVariable("min") Double min, 
			@PathVariable("max") Double max) {
		Sort sort = Sort.by("donGia");
		String sr = sort1.orElse(sessionService.get("sort", ""));
		System.out.print(sort1);

		if (sr.equals("DESC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.descending());

			Page<SanPham> products = spService.findByDongia(pageable, min, max);
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else if (sr.equals("ASC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.ascending());
			Page<SanPham> products = spService.findByDongia(pageable, min, max);
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);
			Page<SanPham> products = spService.findByDongia(pageable, min, max);
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		}	
	}

	@RequestMapping("/ram/{ram}")
	public String sp_Ram(Model model, 
			@RequestParam("sort") Optional<String> sort1,
			@RequestParam("field") Optional<String> field, 
			@RequestParam("p") Optional<Integer> p,
			@PathVariable("ram") String ram) {

		Sort sort = Sort.by("donGia");
		String sr = sort1.orElse(sessionService.get("sort", ""));
		System.out.print(sort1);

		if (sr.equals("DESC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.descending());

			Page<SanPham> products = dao.findAllByRam(pageable, "%" + ram + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else if (sr.equals("ASC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.ascending());
			Page<SanPham> products = dao.findAllByRam(pageable, "%" + ram + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);
			Page<SanPham> products = dao.findAllByRam(pageable, "%" + ram + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		}
	}

	@RequestMapping("/rom/{rom}")
	public String sp_Rom(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("sort") Optional<String> sort1, @RequestParam("field") Optional<String> field,
			@PathVariable("rom") String rom) {
		Sort sort = Sort.by("donGia");
		String sr = sort1.orElse(sessionService.get("sort", ""));
		System.out.print(sort1);

		if (sr.equals("DESC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.descending());

			Page<SanPham> products = dao.findAllByRom(pageable, "%" + rom + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else if (sr.equals("ASC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.ascending());
			Page<SanPham> products = dao.findAllByRom(pageable, "%" + rom + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);
			Page<SanPham> products = dao.findAllByRom(pageable, "%" + rom + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		}
	}

	@RequestMapping("/hedh/{hedh}")
	public String sp_HeDh(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("sort") Optional<String> sort1, @RequestParam("field") Optional<String> field,
			@PathVariable("hedh") String hedh) {
		Sort sort = Sort.by("donGia");
		String sr = sort1.orElse(sessionService.get("sort", ""));
		System.out.print(sort1);

		if (sr.equals("DESC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.descending());

			Page<SanPham> products = dao.findAllByHDH(pageable, "%" + hedh + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else if (sr.equals("ASC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.ascending());
			Page<SanPham> products = dao.findAllByHDH(pageable, "%" + hedh + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);
			Page<SanPham> products = dao.findAllByHDH(pageable, "%" + hedh + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		}
	}

	@RequestMapping("/loaisp/{maloai}")
	public String sp_LoaiSP(Model model, 
			@RequestParam("p") Optional<Integer> p,
			@RequestParam("sort") Optional<String> sort1, 
			@RequestParam("field") Optional<String> field,
			@PathVariable("maloai") String maloai) {
		Sort sort = Sort.by("donGia");
		String sr = sort1.orElse(sessionService.get("sort", ""));
		System.out.print(sort1);

		if (sr.equals("DESC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.descending());

			Page<SanPham> products = spService.findAllByMaLoai2(pageable, "%" + maloai + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else if (sr.equals("ASC")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 15, sort.ascending());
			Page<SanPham> products = spService.findAllByMaLoai2(pageable, "%" + maloai + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		} else {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);
			Page<SanPham> products = spService.findAllByMaLoai2(pageable, "%" + maloai + "%");
			List<HinhAnh> images = hinhanhService.findAll();

			Set<Integer> imgSet = new HashSet<Integer>();
			images = images.stream().filter(img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());

			model.addAttribute("hangsx", hangsxService.findAll());
			model.addAttribute("images", images);
			model.addAttribute("listsp", products);
			return "layout/shop-grid";
		}
	}
}