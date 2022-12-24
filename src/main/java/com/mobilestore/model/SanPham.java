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
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class SanPham implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "masp")
	private int maSP;
	
	@Column(name = "tensp")
	@NotEmpty(message = "Tên sản phẩm không được để trống")
	private String tenSP;
	
	@Column(name = "soluong")
	@NotNull(message = "Số lượng không được để trống")
	@PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
	private int soLuong;
	
	@Column(name = "dongia")
	@NotNull(message = "Đơn giá không được để trống")
	@PositiveOrZero (message = "Đơn giá phải lớn hơn hoặc bằng 0")
	private double donGia;
	
	@Column(name = "mota")
	@NotEmpty(message = "Mô tả không được để trống")
	private String moTa;
	
	@Column(name = "trangthai")
	private boolean trangThai;
	
	@Column(name = "daxoa")
	private boolean daXoa;
	
	// hang san xuat
	@ManyToOne
	@JoinColumn(name = "Mahang")
	HangSanXuat mahang;

	// cau hinh
	@ManyToOne
	@JoinColumn(name = "Mach")
	CauHinh mach;
	
	// loai sp
	@ManyToOne
	@JoinColumn(name = "Maloai")
	LoaiSanPham maloai;

	// hinh anh
	@JsonIgnore
	@OneToMany(mappedBy = "masp")
	List<HinhAnh> hinhanh;

	// chi tiet don hang
	@JsonIgnore
	@OneToMany(mappedBy = "masp")
	List<ChiTietDonHang> chiTietDonHang;

	// binh luân
	@JsonIgnore
	@OneToMany(mappedBy = "masp")
	List<BinhLuan> binhluan;
	
	
	
	
	
	
	
}
