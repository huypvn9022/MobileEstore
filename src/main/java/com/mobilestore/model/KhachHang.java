package com.mobilestore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name = "sđt")
	private int SĐT;
	
	@Column(name = "diachi")
	private String diaChi;

	@JsonIgnore
	@OneToMany(mappedBy = "taiKhoan")
	List<VaiTroKhachHang> vaiTroKH;
	
	// binh luan
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<BinhLuan> binhluan;

	// don hang
	@JsonIgnore
	@OneToMany(mappedBy = "makh")
	List<DonHang> donhang;
}
