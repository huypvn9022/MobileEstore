package com.mobilestore.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "CauHinh")
public class CauHinh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maCH;
	private String doPhanGiai;
	private String doRong;
	private String heDH;
	private String chip;
	private String pin;
	private String ram;
	private String rom;
	private String thuongHieu;
	
	//san pham
	@OneToMany(mappedBy = "maCh")
	List<SanPham> sanpham;
}