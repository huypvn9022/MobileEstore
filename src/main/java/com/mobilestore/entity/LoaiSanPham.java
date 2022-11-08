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
@Table(name = "LoaiSP")
public class LoaiSanPham {
	@Id
	@Column(name = "maloai")
	private String maLoai;
	
	@Column(name = "tenloai")
	private String tenLoai;
	
	@OneToMany(mappedBy = "maLoai")
	List<SanPham> sanpham;
}
