package com.mobilestore.entity;

import java.util.List;

import javax.persistence.Column;
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
	@Column(name = "mach")
	private int maCH;
	
	@Column(name = "dophangiai")
	private String doPhanGiai;
	
	@Column(name = "dorong")
	private String doRong;
	
	@Column(name = "hedh")
	private String heDH;
	
	@Column(name = "chip")
	private String chip;
	
	@Column(name = "pin")
	private String pin;
	
	@Column(name = "ram")
	private String ram;
	
	@Column(name = "rom")
	private String rom;
	
	@Column(name = "thuonghieu")
	private String thuongHieu;
	
	//san pham
	@OneToMany(mappedBy = "mach")
	List<SanPham> sp;
}