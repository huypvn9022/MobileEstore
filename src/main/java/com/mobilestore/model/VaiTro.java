package com.mobilestore.model;


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
@Table(name="Vaitro")
public class VaiTro {
	
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "tenvaitro")
	private String tenVaiTro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vaiTroNV")
	List<NhanVien> vaiTroNV; 

	@JsonIgnore
	@OneToMany(mappedBy = "vaiTroKH")
	List<KhachHang> vaiTroKH;



















}
