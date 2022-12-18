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
	
	@Column(name = "hinhanh")
	private String hinhAnh;
	
	@Column(name = "daxoa")
	private boolean daXoa;

	// san pham
	@ManyToOne
	@JoinColumn(name = "Masp")
	SanPham masp;
}
