package com.mobilestore.model;

import java.util.Date;
import java.util.List;

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
	private String taiKhoan;
	private String matKhau;
	private String hoTen;
	private String email;
	private int SDT;
	private String diaChi;

	// binh luan
	@OneToMany(mappedBy = "taiKhoan")
	List<BinhLuan> binhluan;

	// don hang
	@OneToMany(mappedBy = "taikhoan")
	List<DonHang> donhang;
}
