package com.mobilestore.model;

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
	@OneToMany(mappedBy = "tk")
	List<BinhLuan> bl;

	// don hang
	@OneToMany(mappedBy = "makh")
	List<DonHang> donhang;
}
