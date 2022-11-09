package com.mobilestore.entity;

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
	@Column(name = "masp")
	private int maSP;

	@Column(name = "tensp")
	private String tenSP;

	@Column(name = "soluong")
	private int soLuong;

	@Column(name = "dongia")
	private double donGia;

	@Column(name = "mota")
	private String moTa;

	@Column(name = "trangthai")
	private boolean trangThai;

	// hang san xuat
	@ManyToOne
	@JoinColumn(name = "mahang")
	HangSanXuat maHang;

	// cau hinh
	@ManyToOne
	@JoinColumn(name = "mach")
	CauHinh maCH;
	
	// loai sp
	@ManyToOne
	@JoinColumn(name = "maloai")
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
