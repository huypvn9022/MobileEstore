package com.mobilestore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(name = "Maloai")
	private String maLoai;
	
	@Column(name = "Tenloai")
	private String tenLoai;
	
	@JsonIgnore
	@OneToMany(mappedBy = "maLoai")
	List<SanPham> sanpham;
}
