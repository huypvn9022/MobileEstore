package com.mobilestore.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "Hangsx")
public class HangSanXuat implements Serializable{
	@Id
	@Column(name = "Mahang")
	private String maHang;
	
	@Column(name = "Tenhang")
	private String tenHang;
	
	@Column(name = "daxoa")
	private boolean daXoa;
	
	//san pham
	@JsonIgnore
	@OneToMany(mappedBy = "mahang")
	List<SanPham> sanpham;
}
