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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sanpham")
public class SanPham implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "masp")
	private long maSP;
	
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
	

	// loai sp
	@ManyToOne @JoinColumn(name = "maloai")
	LoaiSanPham maloai;

	// hang san xuat
	@ManyToOne @JoinColumn(name = "mahang")
	HangSanXuat mahang;
	
	// hinh anh
	@OneToMany(mappedBy = "masp")
	List<HinhAnh> hinhanh;
	
	// cau hinh
	@ManyToOne @JoinColumn(name = "mach")
	CauHinh mach;
	
	// chi tiet don hang
	@OneToMany(mappedBy = "masp")
	List<ChiTietDonHang> ctdh;

}
