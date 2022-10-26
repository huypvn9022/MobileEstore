package com.mobilestore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="NhanVien")
public class NhanVien {
	@Id
	private String taiKhoan;
	private String matKhau;
	private String hoTen;
	private String email;
	private String SDT;
	private String diaChi;
	private Date ngaySinh;
	private boolean chucVu;
	
	
}
