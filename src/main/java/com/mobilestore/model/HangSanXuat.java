package com.mobilestore.model;

import java.util.List;

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
	private String maHang;
	private String tenHang;
	
	//san pham
	@OneToMany(mappedBy = "maHang")
	List<SanPham> sanpham;
}
