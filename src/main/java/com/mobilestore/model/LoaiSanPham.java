package com.mobilestore.model;

import javax.persistence.Entity;
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
	private String maLoai;
	private String tenLoai;
}
