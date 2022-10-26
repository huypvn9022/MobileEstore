package com.mobilestore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	private int masp;
	private String tensp;
	private int soLuong;
	private double donGia;
	private String moTa;
	private boolean trangThai;
}
