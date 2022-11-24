package com.mobilestore.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sanpham")
public class SanPham implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Masp")
	private int maSP;

	@Column(name = "Tensp")
	private String tenSP;

	@Column(name = "Soluong")
	private int soLuong;

	@Column(name = "Dongia")
	private double donGia;

	@Column(name = "Mota")
	private String moTa;

	@Column(name = "Trangthai")
	private boolean trangThai;

	// hang san xuat
	@ManyToOne
	@JoinColumn(name = "Mahang")
	HangSanXuat maHang;

	// cau hinh
	@ManyToOne
	@JoinColumn(name = "Mach")
	CauHinh maCH;
	
	// loai sp
	@ManyToOne
	@JoinColumn(name = "Maloai")
	LoaiSanPham maLoai;

	// hinh anh
	@JsonIgnore
	@OneToMany(mappedBy = "masp")
	List<HinhAnh> hinhanh;

	// chi tiet don hang
	@JsonIgnore
	@OneToMany(mappedBy = "masp")
	List<ChiTietDonHang> chiTietDonHang;
	
}
