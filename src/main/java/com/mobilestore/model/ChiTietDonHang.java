package com.mobilestore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="ChiTietDH")
public class ChiTietDonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maCTDH;
	private int maDon;
	private int maSP;
	private Double donGia;
	private int soLuong;
}
