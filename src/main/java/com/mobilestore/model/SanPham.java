package com.mobilestore.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SanPham")
public class SanPham {
	@Id
	private int maSP;
	private String tenSP;
	private int soLuong;
	private double donGia;
	private String moTa;
	private boolean trangThai;
	private String mahang;
	private int maCH;
	private String maloai;
	

	// loai sp
	@ManyToOne
	@JoinColumn(name = "Ma_Loai")
	LoaiSanPham maLoai;

	// hang san xuat
	@ManyToOne
	@JoinColumn(name = "Ma_Hang")
	HangSanXuat maHang;
	
	// hinh anh
	@OneToMany(mappedBy = "maSp")
	List<HinhAnh> hinhanh;
	
	// cau hinh
	@ManyToOne
	@JoinColumn(name = "Ma_CH")
	CauHinh maCh;
	
	// chi tiet don hang
	@OneToMany(mappedBy = "masp")
	List<ChiTietDonHang> ctdh;

}
