package com.mobilestore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "KhachHang")
public class KhachHang {
	@Id
	@Column(name = "taikhoan")
	private String taiKhoan;
	
	@Column(name = "matkhau")
	private String matKhau;
	
	@Column(name = "hoten")
	private String hoTen;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "sdt")
	private int SDT;
	
	@Column(name = "diachi")
	private String diaChi;

	// binh luan
	@OneToMany(mappedBy = "tk")
	List<BinhLuan> bl;

	// don hang
	@OneToMany(mappedBy = "makh")
	List<DonHang> donhang;
}
