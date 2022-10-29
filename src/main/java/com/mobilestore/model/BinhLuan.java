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

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="BinhLuan")
public class BinhLuan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Mabl")
	private int maBL;
	@Column(name = "NoiDung")
	private String noiDung;
	
	//sanpham
	@ManyToOne @JoinColumn(name = "MaSP")
	SanPham sp;
	
	// khach hang
	@ManyToOne @JoinColumn(name = "Taikhoan")
	KhachHang tk;
}
