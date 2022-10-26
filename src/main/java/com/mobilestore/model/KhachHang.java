package com.mobilestore.model;

<<<<<<< HEAD
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="KhachHang")
public class KhachHang {
	@Id
	private String TaiKhoan;
	private String MatKhau;
	private String HoTen;
	private String Email;
	private int SDT;
	private String DiaChi;
=======
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
>>>>>>> 904bf5d8eb90b4728b842232e90cbec00d09c450
}
