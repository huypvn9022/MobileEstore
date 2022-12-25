package com.mobilestore.Admincontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.DonHang;
import com.mobilestore.model.NhanVien;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.DonHangService;
import com.mobilestore.service.NhanVienService;
import com.mobilestore.service.SanPhamService;
import com.mobilestore.service.SessionService;

@Controller
@RequestMapping("/admin")
public class AdminDonHangController {
	@Autowired
	DonHangService dhService;
	
	@Autowired
	NhanVienService nvService;
	
	@Autowired
	SanPhamService spService;
	
	@Autowired
	SessionService session;
	


	@RequestMapping("/order/{trangThai}")
	public String order(Model model, @RequestParam("p") Optional<Integer> page,@PathVariable("trangThai") String trangThai,
			@ModelAttribute("donhang") DonHang donhang) {
		Pageable pageAble = PageRequest.of(page.orElse(0), 5);
		Page<DonHang> listDH = dhService.findAllByTrangThaiLikeAndDaXoaFalse(pageAble,trangThai);
		List<SanPham> listSP = spService.findAll();
		
		session.set("trangthaidh", trangThai);
		
		String tk = session.get("taiKhoanNV");		
		NhanVien nv = nvService.findById(tk);
		donhang.setManv(nv);
		
		
		model.addAttribute("nvban", nv.getHoTen());
		model.addAttribute("listdh", listDH);
		model.addAttribute("listsp", listSP);
		return "admin/index";
	}                                                 

	@RequestMapping("/order/edit/{trangThai}/{madon}")
	public String orderEdit(Model model, @PathVariable("madon") Integer maDon,
			@PathVariable("trangThai") String trangThai	,@RequestParam("p") Optional<Integer> page) {
		Pageable pageAble = PageRequest.of(page.orElse(0), 5);
		Page<DonHang> listDH = dhService.findAllByTrangThaiLikeAndDaXoaFalse(pageAble,trangThai);
		List<SanPham> listSP = spService.findAll();

		DonHang donhang = dhService.findById(maDon);
		NhanVien nv = nvService.findById(session.get("taiKhoanNV"));

		model.addAttribute("listdh", listDH);
		model.addAttribute("listsp", listSP);
		model.addAttribute("donhang", donhang);
		model.addAttribute("nvban", nv.getHoTen());
		return "admin/index";
	}
	
	@RequestMapping("/order/reset")
	public String orderReset() {
		return"redirect:/admin/order/"+session.get("trangthaidh").toString();
	}

	@RequestMapping("/order/update/{trangthai}")
	public String orderUpdate(Model model,@Valid @ModelAttribute("donhang") DonHang donhang, BindingResult bind,
			@RequestParam("p") Optional<Integer> page,@RequestParam("tongtien") Double tongtien,@PathVariable("trangthai") String trangThai) {

		Pageable pageAble = PageRequest.of(page.orElse(0), 5);
		Page<DonHang> listDH = dhService.findAllByTrangThaiLikeAndDaXoaFalse(pageAble,session.get("trangthaidh"));
	
		NhanVien nv = nvService.findById(session.get("taiKhoanNV"));
		donhang.setManv(nv);
		
		if (!dhService.existsById(donhang.getMaDon())) {
			model.addAttribute("message", "Đơn hàng không tồn tại");
			model.addAttribute("listdh", listDH);
			model.addAttribute("nvban", nv.getHoTen());
			return "admin/index";
		}else {
			donhang.setTongTien( tongtien);		
			dhService.save(donhang);
			model.addAttribute("listdh", listDH);
			model.addAttribute("nvban", nv.getHoTen());
			model.addAttribute("donhang", donhang);
			return"redirect:/admin/order/edit/"+session.get("trangthaidh").toString()+"/"+ donhang.getMaDon();
		}
	}

	@RequestMapping("/order/delete/{maDon}")
	public String orderDelete(Model model, @ModelAttribute("donhang") DonHang donhang,@RequestParam("tongtien") Double tongtien,
			@RequestParam("p") Optional<Integer> page, @PathVariable("maDon") Integer maDon) {
		Pageable pageAble = PageRequest.of(page.orElse(0), 5);
		Page<DonHang> listDH = dhService.findAllByTrangThaiLikeAndDaXoaFalse(pageAble,session.get("trangthaidh"));
		
		NhanVien nv = nvService.findById(session.get("taiKhoanNV"));
		donhang.setManv(nv);
		
		if (maDon.equals(0)) {
			model.addAttribute("message", "Vui lòng chọn đơn hàng để xóa");
			model.addAttribute("listdh", listDH);
			model.addAttribute("nvban", nv.getHoTen());
			return "admin/index";
		} else {
			donhang.setTongTien(tongtien);
			donhang.setDaXoa(true);
			dhService.save(donhang);
			return "redirect:/admin/order/"+session.get("trangthaidh").toString();
		}
	}
}
