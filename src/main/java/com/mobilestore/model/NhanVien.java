package com.mobilestore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="NhanVien")
public class NhanVien {
	@Id
	private String TaiKhoan;
	private String MatKhau;
	private String HoTen;
	private String Email;
	private int SDT;
	private String DiaChi;
	private Date NgaySinh;
	private boolean ChucVu;
	
	
}
