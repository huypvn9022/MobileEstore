package com.mobilestore.model;

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
@Table(name = "ChiTietDH")
public class ChiTietDonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Mactdh")
	private int maCTDH;
	@Column(name = "DonGia")
	private Double donGia;
	@Column(name = "SoLuong")
	private int soLuong;

	// san pham
	@ManyToOne @JoinColumn(name = "Masp")
	SanPham masp;
	
	// don hang
	@ManyToOne @JoinColumn(name = "MaDon")
	DonHang madon;
}
