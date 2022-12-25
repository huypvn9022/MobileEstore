package com.mobilestore.Admincontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.HinhAnhService;
import com.mobilestore.service.SanPhamService;
import com.mobilestore.service.SessionService;

@Controller
@RequestMapping("/admin")
public class AdminHinhAnhController {
	@Autowired
	HinhAnhService haService;

	@Autowired
	SessionService sessionService;

	@Autowired
	SanPhamService spService;

	Path currenPath = Paths.get(".");
	Path asolutePath = currenPath.toAbsolutePath();
	Path path = Paths.get(asolutePath + "\\src\\main\\resources\\static\\client\\img\\product\\");

	@RequestMapping("/hinhanh")
	public String image(Model model, @RequestParam("p") Optional<Integer> page,
			@ModelAttribute("hinhanh") HinhAnh hinhanh) {
		Sort sort = Sort.by("masp.maSP").ascending();
		Pageable pageAble = PageRequest.of(page.orElse(0), 5, sort);
		Page<HinhAnh> pages = haService.findAllByDaXoaFalse(pageAble);
		List<SanPham> listSP = spService.findAll();

		model.addAttribute("listsp", listSP);
		model.addAttribute("pages", pages);
		return "admin/hinhanh";
	}

	@RequestMapping("/hinhanh/edit/{maHinh}")
	public String imageEdit(Model model, @RequestParam("p") Optional<Integer> page,
			@PathVariable("maHinh") Integer maHinh) {
		Sort sort = Sort.by("masp.maSP").ascending();
		Pageable pageAble = PageRequest.of(page.orElse(0), 5, sort);
		Page<HinhAnh> pages = haService.findAllByDaXoaFalse(pageAble);
		List<SanPham> listSP = spService.findAll();

		HinhAnh anh = haService.findById(maHinh);

		model.addAttribute("listsp", listSP);
		model.addAttribute("hinhanh", anh);
		model.addAttribute("pages", pages);
		return "admin/hinhanh";
	}

	@RequestMapping("/hinhanh/create")
	public String imageCreate(Model model, @Valid @ModelAttribute("hinhanh") HinhAnh hinhAnh,
			BindingResult bindingResult, @RequestParam("p") Optional<Integer> page,
			@RequestParam("file1") MultipartFile file1) {

		Sort sort = Sort.by("masp.maSP").ascending();
		Pageable pageAble = PageRequest.of(page.orElse(0), 5, sort);
		Page<HinhAnh> pages = haService.findAllByDaXoaFalse(pageAble);
		List<SanPham> listSP = spService.findAll();

		String fileName1 = file1.getOriginalFilename().toString();
		hinhAnh.setHinhAnh(fileName1);

		if (file1.isEmpty()) {
			model.addAttribute("message", "Vui lòng chọn hình ảnh");
			model.addAttribute("pages", pages);
			model.addAttribute("listsp", listSP);
			return "admin/hinhanh";
		} else {
			try {
				FileCopyUtils.copy(file1.getBytes(), new File(path + "\\" + fileName1));

				haService.save(hinhAnh);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:/admin/hinhanh";
		}
	}


	@RequestMapping("/hinhanh/update/{mahinh}")
	public String imageUpdate(Model model, @ModelAttribute("hinhanh") HinhAnh hinhAnh,
			@RequestParam("p") Optional<Integer> page, @RequestParam("file1") MultipartFile file1,
			@PathVariable("mahinh") Integer maHinh) {

		Sort sort = Sort.by("masp.maSP").ascending();
		Pageable pageAble = PageRequest.of(page.orElse(0), 5, sort);
		Page<HinhAnh> pages = haService.findAllByDaXoaFalse(pageAble);
		List<SanPham> listSP = spService.findAll();

		String fileName1 = file1.getOriginalFilename().toString();
		hinhAnh.setHinhAnh(fileName1);

		if (haService.existsById(maHinh)) {
			try {
				hinhAnh.setMaHinh(maHinh);
				if (fileName1.isEmpty()) {
					Files.deleteIfExists(Paths.get(
							asolutePath + "\\src\\main\\resources\\static\\client\\img\\product\\" + "\\" + fileName1));
					FileCopyUtils.copy(file1.getBytes(), new File(path + "\\" + fileName1));
					haService.save(hinhAnh);
				} else {
					FileCopyUtils.copy(file1.getBytes(), new File(path + "\\" + fileName1));
					haService.save(hinhAnh);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:/admin/hinhanh/edit/" + maHinh;
		} else {
			model.addAttribute("message", "Vui lòng chọn hình ảnh để chỉnh sửa");
			model.addAttribute("pages", pages);
			model.addAttribute("listsp", listSP);
			model.addAttribute("hinhanh", hinhAnh);
			return "admin/hinhanh";
		}
	}


	@RequestMapping("/hinhanh/delete/{mahinh}")
	public String imageDelete(Model model, @ModelAttribute("hinhanh") HinhAnh hinhAnh,
			@RequestParam("p") Optional<Integer> page, @PathVariable("mahinh") Integer maHinh) {

		Sort sort = Sort.by("masp.maSP").ascending();
		Pageable pageAble = PageRequest.of(page.orElse(0), 5, sort);
		Page<HinhAnh> pages = haService.findAllByDaXoaFalse(pageAble);
		List<SanPham> listSP = spService.findAll();

		
		
		if (maHinh.equals(0)) {
			model.addAttribute("message", "Vui lòng chọn hình ảnh để xóa");
			model.addAttribute("listsp", listSP);
			model.addAttribute("pages", pages);
			return "admin/hinhanh";
		} else {
			HinhAnh ha = haService.findById(maHinh);
			ha.setDaXoa(true);
			haService.update(ha);
			model.addAttribute("message", "Xóa thành công");
			return "redirect:/admin/hinhanh";
		}
	}


	@RequestMapping("/hinhanh/reset")
	public String imageNew(Model model) {
		return "redirect:/admin/hinhanh";
	}


	@RequestMapping("/hinhanh/search")
	public String Search(Model model, @RequestParam("p") Optional<Integer> page,
			@ModelAttribute("hinhanh") HinhAnh product, @RequestParam("Keyword") Optional<String> kw) {

		String kwords = kw.orElse(sessionService.get("keywords", ""));
		sessionService.get("keywords", kwords);
		List<SanPham> listSP = spService.findAll();

		List<HinhAnh> pages = haService.findAllByKeyword( "%" + kwords + "%");

		if (kwords.equals("")) {
			return "redirect:/admin/hinhanh";
		} else {
			model.addAttribute("pages", pages);
			model.addAttribute("listsp", listSP);
			model.addAttribute("deletePage", 1);
			return "admin/hinhanh";
		}

	}

}
