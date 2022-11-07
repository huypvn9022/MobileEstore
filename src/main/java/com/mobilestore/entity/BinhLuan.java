package com.mobilestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BinhLuan")
public class BinhLuan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mabl")
	private int maBL;
	
	@Column(name = "noidung")
	private String noiDung;

	// sanpham
	@ManyToOne
	@JoinColumn(name = "masp")
	SanPham sp;

	// khach hang
	@ManyToOne
	@JoinColumn(name = "taikhoan")
	KhachHang tk;
}