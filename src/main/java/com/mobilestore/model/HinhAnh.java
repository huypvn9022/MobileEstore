package com.mobilestore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="HinhAnh")
public class HinhAnh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int MaHinh;
	private int MaSP;
	private String HinhAnh;
} 
