package com.mobilestore.model;

import java.io.Serializable;

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
@Table(name = "Chitietdh")
public class ChiTietDonHang implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mactdh")
	private int maCTDH;
	
	@Column(name = "dongia")
	private Double donGia;
	
	@Column(name = "soluong")
	private int soLuong;

	// san pham
	@ManyToOne @JoinColumn(name = "masp")
	 SanPham masp;
	
	// don hang
	@ManyToOne @JoinColumn(name = "madon")
	DonHang madh;
}
