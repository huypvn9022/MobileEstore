package com.mobilestore.model;

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
@Table(name = "ChiTietDH")
public class ChiTietDonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maCTDH;
	private int madon;
	private int maSP;
	private Double donGia;
	private int soLuong;

	// san pham
	@ManyToOne
	@JoinColumn(name = "Ma_SP")
	SanPham masp;
	
	// don hang
	@ManyToOne
	@JoinColumn(name = "Ma_Don")
	DonHang maDon;
}
