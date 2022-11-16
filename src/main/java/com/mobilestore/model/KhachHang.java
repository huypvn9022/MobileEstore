package com.mobilestore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Khachhang")
public class KhachHang {
	@Id
	@Column(name = "taikhoan")
	@NotBlank(message="Tài khoản không được bỏ trống")
	private String taiKhoan;
	
	@Column(name = "matkhau")
	@NotBlank(message="Mật khẩu không được bỏ trống")
	private String matKhau;
	
	@Column(name = "hoten")
	@NotBlank(message = "Tài khoản không được bỏ trống")
	private String hoTen;
	
	@Column(name = "email")
	@Email(message = "Email không đúng định dạng")
	private String email;
	
	@Column(name = "sdt")
	@NotBlank(message = "Số điện thoại không được bỏ trống")
	private String SDT;
	
	@Column(name = "diachi")
	@NotBlank(message = "Địa chỉ không được bỏ trống")
	private String diaChi;

	// binh luan
	@OneToMany(mappedBy = "tk")
	List<BinhLuan> bl;

	// don hang
	@OneToMany(mappedBy = "makh")
	List<DonHang> donhang;
}
