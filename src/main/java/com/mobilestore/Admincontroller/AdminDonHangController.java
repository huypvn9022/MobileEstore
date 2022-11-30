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

import com.mobilestore.service.CTDHService;
import com.mobilestore.service.DonHangService;
import com.mobilestore.service.SanPhamService;
import com.mobilestore.model.ChiTietDonHang;
import com.mobilestore.model.DonHang;
import com.mobilestore.model.SanPham;
import com.mobilestore.model.TongTienCTDH;

@Controller
@RequestMapping("/admin")
public class AdminDonHangController {
	@Autowired
	DonHangService dhService;

	@Autowired
	SanPhamService spService;

	@Autowired
	CTDHService ctdhService;

	@RequestMapping("/order")
	public String order(Model model, @RequestParam("p") Optional<Integer> page,
			@ModelAttribute("donhang") DonHang donhang) {
		Pageable pageAble = PageRequest.of(page.orElse(0), 5);
		Page<DonHang> listDH = dhService.findAll(pageAble);
		List<SanPham> listSP = spService.findAll();
		
		TongTienCTDH tongTien = new TongTienCTDH();
		tongTien.setTongTien(0.0);
		
		model.addAttribute("listdh", listDH);
		model.addAttribute("listsp", listSP);
		model.addAttribute("tongtien", tongTien);
		return "admin/index";
	}                                                 

	@RequestMapping("/order/edit/{madon}")
	public String orderEdit(Model model, @PathVariable("madon") Integer maDon, @RequestParam("p") Optional<Integer> page) {
		Pageable pageAble = PageRequest.of(page.orElse(0), 5);
		Page<DonHang> listDH = dhService.findAll(pageAble);
		List<SanPham> listSP = spService.findAll();

		DonHang donhang = dhService.findById(maDon);
		TongTienCTDH tongtien = ctdhService.getTongTienByMadon(maDon);

		model.addAttribute("listdh", listDH);
		model.addAttribute("listsp", listSP);
		model.addAttribute("donhang", donhang);
		model.addAttribute("tongtien", tongtien);

		return "admin/index";
	}
//
	@RequestMapping("/order/reset")
	public String oderReset() {
		return "redirect:/admin/order";
	}
//
	@RequestMapping("/order/update")
	public String doPostUpdate(Model model,@Valid @ModelAttribute("donhang") DonHang donhang, BindingResult bind,
			@RequestParam("p") Optional<Integer> page,@RequestParam("tongtien") Double tongtien) {

		Pageable pageAble = PageRequest.of(page.orElse(0), 5);
		Page<DonHang> listDH = dhService.findAll(pageAble);
//		System.out.println(donhang);
//		return "redirect:/admin/order/edit/" + donhang.getMaDon();			

		if (!dhService.existsById(donhang.getMaDon())) {
			model.addAttribute("message", "Đơn hàng không tồn tại");
			model.addAttribute("listdh", listDH);
			return "admin/index";
		}else if(bind.hasErrors()) {
			model.addAttribute("message", "Đơn hàng không tồn tại");
			model.addAttribute("listdh", listDH);
			return "admin/index";
		}
		
		else {
			donhang.setTongTien(tongtien);		
			dhService.save(donhang);
			model.addAttribute("donhang", donhang);
			return "redirect:/admin/order/edit/" + donhang.getMaDon();			
		}
	}
//
	@RequestMapping("/order/delete/{maDon}")
	public String orderDelete(Model model, @ModelAttribute("donhang") DonHang donhang,
			@RequestParam("p") Optional<Integer> page, @PathVariable("maDon") Integer maDon) {
		Pageable pageAble = PageRequest.of(page.orElse(0), 5);
		Page<DonHang> listDH = dhService.findAll(pageAble);
		List<ChiTietDonHang> ctdh = ctdhService.getAllOrderDetail(maDon);

		if (maDon.equals(0)) {
			model.addAttribute("message", "Vui lòng chọn đơn hàng để xóa");
			model.addAttribute("listdh", listDH);
			return "admin/index";
		} else {
			ctdhService.deleteAll(ctdh);
			dhService.delete(maDon);
			return "redirect:/admin/order";
		}
	}
//
	@RequestMapping("/order/delete/product/{mactdh}/{maDon}")
	public String orderDeleteProduct(Model model, @PathVariable("mactdh") Integer mactdh,@PathVariable("maDon") Integer maDon,
			 @ModelAttribute("donhang") DonHang donhang) {

		ctdhService.delete(mactdh);
		TongTienCTDH tongtien = ctdhService.getTongTienByMadon(maDon);

		donhang.setTongTien(tongtien.getTongTien());
		dhService.save(donhang);
		System.out.println(donhang);
		return "redirect:/admin/order/edit/" + maDon;
	}
//
	@RequestMapping("/order/add/product/{maDon}")
	public String orderUpdateProduct(Model model, @PathVariable("maDon") Integer maDon, @RequestParam("masp") Integer maSP,
			@ModelAttribute("donhang") DonHang donhang, @RequestParam("soluong") Integer soLuong) {
		SanPham sp = spService.findById(maSP);
		DonHang dh = dhService.findById(maDon);

		ChiTietDonHang ctdh = new ChiTietDonHang();
		ctdh.setMadh(dh);
		ctdh.setMasp(sp);
		ctdh.setDonGia(sp.getDonGia());
		ctdh.setSoLuong(soLuong);
		ctdhService.save(ctdh);

		TongTienCTDH tongtien = ctdhService.getTongTienByMadon(maDon);

		model.addAttribute("tongtien", tongtien);

		return "redirect:/admin/order/edit/" + maDon;
	}
}
