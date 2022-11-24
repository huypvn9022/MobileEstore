package com.mobilestore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "Cauhinh")
public class CauHinh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Mach")
	private int maCH;
	
	@Column(name = "Dophangiai")
	private String doPhanGiai;
	
	@Column(name = "Dorong")
	private String doRong;
	
	@Column(name = "Hedh")
	private String heDH;
	
	@Column(name = "Chip")
	private String chip;
	
	@Column(name = "Pin")
	private String pin;
	
	@Column(name = "Ram")
	private String ram;
	
	@Column(name = "Rom")
	private String rom;
	
	//san pham
	@JsonIgnore
	@OneToMany(mappedBy = "maCH")
	List<SanPham> sanpham;
}