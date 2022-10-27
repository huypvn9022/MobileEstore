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
@Entity
@Table(name = "LoaiSP")
@AllArgsConstructor
@NoArgsConstructor
public class LoaiSanPham {
	@Id
	private String maLoai;
	private String tenLoai;
	
	@OneToMany(mappedBy = "maLoai")
	List<SanPham> sanpham;
}
