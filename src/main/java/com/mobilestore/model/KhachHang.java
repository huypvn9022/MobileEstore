package com.mobilestore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "KhachHang")
public class KhachHang {
	@Id
	private String taiKhoan;
	private String matKhau;
	private String hoTen;
	private String email;
	private String sdt;
	private String diaChi;
}
