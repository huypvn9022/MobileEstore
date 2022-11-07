package com.mobilestore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HangSX")
public class HangSanXuat {
	@Id
	@Column(name = "mahang")
	private String maHang;
	
	@Column(name = "tenhang")
	private String tenHang;
	
	//san pham
	@OneToMany(mappedBy = "mahang")
	List<SanPham> sanpham;
}
