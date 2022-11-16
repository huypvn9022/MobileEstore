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
@Table(name = "Hinhanh")
public class HinhAnh implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mahinh")
	private int maHinh;
	
	@Column(name = "hinhanh1")
	private String hinhAnh1;
	
	@Column(name = "hinhanh2")
	private String hinhAnh2;
	
	@Column(name = "hinhanh3")
	private String hinhAnh3;
	
	@Column(name = "hinhanh4")
	private String hinhAnh4;
	
	@Column(name = "hinhanh5")
	private String hinhAnh5;
	
	@Column(name = "hinhanh6")
	private String hinhAnh6;
	
	@Column(name = "hinhanh7")
	private String hinhAnh7;

	// san pham
	@ManyToOne
	@JoinColumn(name = "masp")
	SanPham masp;
}
