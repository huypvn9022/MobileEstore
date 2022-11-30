package com.mobilestore.controller;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.KhachHang;
import com.mobilestore.model.MailInfo;
import com.mobilestore.model.VaiTro;
import com.mobilestore.service.KhachHangSevice;
import com.mobilestore.service.MailerService;
import com.mobilestore.service.VaiTroService;


@Controller
@RequestMapping("/index")
public class TaiKhoanController {
	@Autowired
	KhachHangSevice khService;

	@Autowired
	MailerService mailService;
	
	@Autowired
	VaiTroService vtService;

	@GetMapping("/dangky")
	public String dangky(Model model, @ModelAttribute("dangky") KhachHang kh) {
		return "layout/DangKy";
	}

	@PostMapping("/dangky")
	public String dangky_submit(Model model, @Valid @ModelAttribute("dangky") KhachHang kh,
			BindingResult bingdingResult) {
		VaiTro vt = vtService.findById("KH");
		kh.setVaiTroKH(vt);

		if (bingdingResult.hasErrors()) {
			return "layout/DangKy";
		} else {
			khService.save(kh);
			return "redirect:/index/dangnhap";
		}
	}

	@RequestMapping("/dangnhap")
	public String dangnhap(Model model, @ModelAttribute("dangky") KhachHang kh) {
		return "layout/DangNhap";
	}

	@GetMapping("/doimk")
	public String doimk(Model model) {
		return "layout/DMatKhau";
	}

	@PostMapping("/doimk")
	public String doimk_submit(Model model, @RequestParam("taiKhoan") String tk, @RequestParam("matKhauCu") String mkCu,
			@RequestParam("matKhauMoi") String mkMoi, @RequestParam("matKhauMoi2") String mkMoi2) {

		if (tk.isBlank() || mkCu.isBlank() || mkMoi.isBlank() || mkMoi2.isBlank()) {
			model.addAttribute("message", "Vui lòng không để trống dữ liệu");
			return "layout/DMatKhau";
		} else {

			if (!khService.existsById(tk)) {
				model.addAttribute("message", "Sai thông tin tài khoản hoặc tài khoản không tồn tại");
				return "layout/DMatKhau";
			} else {
				KhachHang kh = khService.findById(tk);

				if (!kh.getMatKhau().equals(mkCu)) {
					model.addAttribute("message", "Mật khẩu cũ không khớp với tài khoản");
					return "layout/DMatKhau";
				} else if (!mkMoi.equals(mkMoi2)) {
					model.addAttribute("message", "Nhập lại mật khẩu không khớp vui lòng nhập lại");
					return "layout/DMatKhau";
				} else {
					kh.setTaiKhoan(tk);
					kh.setMatKhau(mkMoi2);
					khService.save(kh);
					model.addAttribute("message", "Đổi mật khẩu thành công");
					return "layout/DMatKhau";
				}
			}
		}
	}

	@GetMapping("/thong-tin-tai-khoan")
	public String thongTinTK(Model model, @ModelAttribute("tttaikhoan") KhachHang kh) {
		return "layout/TTTaiKhoan";
	}
	@PostMapping("/thong-tin-tai-khoan")
	public String thongTinTK_submit(Model model, @ModelAttribute("tttaikhoan") KhachHang kh) {
		return "layout/TTTaiKhoan";
	}
	
	
	@GetMapping("/quenmk")
	public String quenMK_submit() {
		return"layout/QMatKhau";
	}
	
	@PostMapping("/quenmk")
	public String quenMK(@RequestParam String username,@RequestParam String email, Model model) {
        KhachHang acc = khService.findById(username);
        if (!khService.existsById(username)) {
            model.addAttribute("message", "Tài khoản không tồn tại");
        }else {
//            String newPass = ((long) Math.floor(Math.random() * (999999999 - 100000000 + 1) + 100000000)) +"";
//            acc.setMatKhau(newPass);
//            khService.save(acc);
            try {
                MailInfo mail = new MailInfo();
                mail.setFrom("tientai9a9@gmail.com");
                mail.setTo(email);
                mail.setSubject("Mobile Estore - Lấy lại mật khẩu");
                mail.setBody("Xin chào " + acc.getHoTen() + ", mật khẩu của bạn là: " + acc.getMatKhau() + ""
                		+ ". Vui lòng không được gửi mật khẩu này cho bất cứ để tránh mất thông tin.");
                mailService.send(mail);
                model.addAttribute("message","Gửi thành công!");
            } catch (MessagingException e) {
            	 model.addAttribute("message","Gửi thất bại!");
                e.printStackTrace();
            }
            model.addAttribute("message","Chúng tôi đã gửi mật khẩu về email của bạn, vui lòng kiểm tra email!");
        }
        return "layout/QMatKhau";
	}
}
