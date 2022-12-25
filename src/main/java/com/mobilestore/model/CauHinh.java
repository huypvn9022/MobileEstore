package com.mobilestore.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "Cauhinh")
public class CauHinh implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mach")
	private int maCH;
	
	@Column(name = "dophangiai")
	@NotEmpty(message = "Độ phân giải không được để trống")
	private String doPhanGiai;
	
	@Column(name = "dorong")
	@NotEmpty(message = "Độ rộng không được để trống")
	private String doRong;
	
	@Column(name = "hedh")
	@NotEmpty(message = "Hệ điều hành không được để trống")
	private String heDH;
	
	@Column(name = "chip")
	@NotEmpty(message = "Chip không được để trống")
	private String chip;
	
	@Column(name = "pin")
	@NotEmpty(message = "Pin không được để trống")
	private String pin;
	
	@Column(name = "ram")
	private String ram;
	
	@Column(name = "rom")
	private String rom;
	
	@Column(name = "daxoa")
	private boolean daXoa;
	
	//san pham
	@JsonIgnore
	@OneToMany(mappedBy = "mach")
	List<SanPham> sanpham;
}









