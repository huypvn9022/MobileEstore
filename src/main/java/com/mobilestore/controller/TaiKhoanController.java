	package com.mobilestore.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.ChiTietDonHang;
import com.mobilestore.model.DonHang;
import com.mobilestore.model.KhachHang;
import com.mobilestore.model.MailInfo;
import com.mobilestore.model.VaiTro;
import com.mobilestore.service.CTDHService;
import com.mobilestore.service.DonHangService;
import com.mobilestore.service.KhachHangService;
import com.mobilestore.service.MailerService;
import com.mobilestore.service.SessionService;
import com.mobilestore.service.VaiTroService;

@Controller
@RequestMapping("/index")
public class TaiKhoanController {
	@Autowired
	KhachHangService khService;

	@Autowired
	MailerService mailService;
	
	@Autowired
	SessionService session;

	@Autowired
	VaiTroService vtService;
	
	@Autowired
	DonHangService donhangService;
	
	@Autowired
	CTDHService chitietdonhangService;

	@GetMapping("/dangky")
	public String dangky(Model model, @ModelAttribute("dangky") KhachHang kh) {
		return "form/dangky";
	}

	@PostMapping("/dangky")
	public String dangky_submit(Model model, @Valid @ModelAttribute("dangky") KhachHang kh,
			BindingResult bingdingResult) {
		VaiTro vt = vtService.findById("KH");
		kh.setVaiTroKH(vt);

		if (bingdingResult.hasErrors()) {
			return "form/dangky";
		} else {
			if (khService.existsById(kh.getTaiKhoan())) {
				model.addAttribute("message", "Tài khoản này đã tồn tại vui lòng tạo tài khoản khác");
				return "form/dangky";
			} else {
				khService.save(kh);
				return "redirect:/security/form/login";
			}
		}
	}

	@GetMapping("/doimk")
	public String doimk(Model model) {
		return "form/doimatkhau";
	}

	@PostMapping("/doimk")
	public String doimk_submit(Model model, @RequestParam("taiKhoan") String tk, @RequestParam("matKhauCu") String mkCu,
			@RequestParam("matKhauMoi") String mkMoi, @RequestParam("matKhauMoi2") String mkMoi2) {

		if (tk.isBlank() || mkCu.isBlank() || mkMoi.isBlank() || mkMoi2.isBlank()) {
			model.addAttribute("message", "Vui lòng không để trống dữ liệu");
			return "form/doimatkhau";
		} else {

			if (!khService.existsById(tk)) {
				model.addAttribute("message", "Sai thông tin tài khoản hoặc tài khoản không tồn tại");
				return "form/doimatkhau";
			} else {
				KhachHang kh = khService.findById(tk);

				if (!kh.getMatKhau().equals(mkCu)) {
					model.addAttribute("message", "Mật khẩu cũ không khớp với tài khoản");
					return "form/doimatkhau";
				} else if (!mkMoi.equals(mkMoi2)) {
					model.addAttribute("message", "Nhập lại mật khẩu không khớp vui lòng nhập lại");
					return "form/doimatkhau";
				} else {
					kh.setTaiKhoan(tk);
					kh.setMatKhau(mkMoi2);
					khService.save(kh);
					model.addAttribute("message", "Đổi mật khẩu thành công");
					return "form/doimatkhau";
				}
			}
		}
	}

	@GetMapping("/thong-tin-tai-khoan")
	public String thongTinTK(Model model, @ModelAttribute("khachhang") KhachHang kh) {
		String tk  = session.get("taiKhoanKH");
		String mk =  session.get("matKhau");
		KhachHang khachhang = khService.findById(tk);
		kh.setTaiKhoan(tk);
		kh.setMatKhau(mk);

		model.addAttribute("khachhang", khachhang);
		return "form/thongtintaikhoan";
	}

	@PostMapping("/thong-tin-tai-khoan")
	public String thongTinTK_submit(Model model,@Valid @ModelAttribute("khachhang") KhachHang kh, BindingResult bindingResult) {
		String tk  = session.get("taiKhoanKH");
		VaiTro vt = vtService.findById(session.get("vaiTroKH"));
		KhachHang khachhang = khService.findById(tk);		
		
		kh.setTaiKhoan(khachhang.getTaiKhoan());
		kh.setMatKhau(khachhang.getMatKhau());
		kh.setVaiTroKH(vt);
		
		if(!bindingResult.hasErrors()) {
			model.addAttribute("message", "Cật nhập thất bại");
			model.addAttribute("khachhang", khachhang);
			return "form/thongtintaikhoan";
		}else {
			khService.save(kh);
			model.addAttribute("message", "Cật nhập thành công");
			return "form/thongtintaikhoan";
		}
		
	}

	@GetMapping("/quenmk")
	public String quenMK_submit() {
		return "form/quenmatkhau";
	}

	@PostMapping("/quenmk")
	public String quenMK(@RequestParam String username, @RequestParam String email, Model model) {

		if (username.isBlank() || email.isBlank()) {
			model.addAttribute("message", "Vui lồng không để trống dữ liệu!");
			return "form/quenmatkhau";
		} else {
			if (!khService.existsById(username)) {
				model.addAttribute("message", "Tài khoản không tồn tại");
			} else {
				KhachHang acc = khService.findById(username);
				String newPass = ((long) Math.floor(Math.random() * (999999999 - 100000000 + 1) + 100000000)) + "";
				acc.setMatKhau(newPass);
				khService.save(acc);
				try {
					MailInfo mail = new MailInfo();
					mail.setFrom("tientai9a9@gmail.com");
					mail.setTo(email);
					mail.setSubject("Mobile Estore - Lấy lại mật khẩu");
					mail.setBody("Xin chào " + acc.getHoTen() + ", mật khẩu của bạn là: " + newPass + ""
							+ ". Vui lòng không được gửi mật khẩu này cho bất cứ để tránh mất thông tin.");
					mailService.send(mail);
					model.addAttribute("message", "Gửi thành công!");
				} catch (MessagingException e) {
					model.addAttribute("message", "Gửi thất bại!");
					e.printStackTrace();
				}
				model.addAttribute("message", "Chúng tôi đã gửi mật khẩu về email của bạn, vui lòng kiểm tra email!");
			}
			return "form/quenmatkhau";
		}

	}
	
	@RequestMapping("/order-management")
	public String orderManagement(Model model) {
		String makh = session.get("taiKhoanKH");
		
		model.addAttribute("makh", donhangService.findByUsername(makh));
		return "layout/quanlydonhang";
	}
	
	@RequestMapping("/order/chiTietDonHang/{orderId}")
	public String orderDetail(Model model, @PathVariable Integer orderId) {
		List<ChiTietDonHang> ctdh = chitietdonhangService.getAllOrderDetail(orderId);

		model.addAttribute("ctdh", ctdh);
 		return "layout/chitietdonhang";
	}
	
	@RequestMapping("/order/cancel/{orderId}")
	public String orderCancel(Model model, @PathVariable Integer orderId) {
		DonHang dh = donhangService.findById(orderId);
		dh.setTrangThai("Cancel");
		donhangService.update(dh);
		
 		return "redirect:/index/order-management";
	}
}
