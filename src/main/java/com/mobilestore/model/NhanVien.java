package com.mobilestore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="Nhanvien")
public class NhanVien implements Serializable {
	@Id
	@Column(name = "taikhoan")
	@NotEmpty(message = "Tài khoản không được để trống")
	private String taiKhoan;
	
	@Column(name = "matkhau")
	@NotEmpty(message = "Mật khẩu không được để trống")
	private String matKhau;
	
	@Column(name = "hoten")
	@NotEmpty(message = "Họ và tên không được để trống")
	private String hoTen;
	
	@Column(name = "email")
	@NotEmpty(message = "Email không được để trống")
	@Email(message = "Email không đúng định dạng")
	private String email;
	
	@Column(name = "SĐT")
	@NotEmpty(message = "Số điện thoại không được để trống")
	private String sdt;
	
	@Column(name = "diachi")
	@NotEmpty(message = "Địa chỉ không được để trống")
	private String diaChi;
	
	@Column(name = "ngaysinh")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Ngày sinh không được để trống")
	private Date ngaySinh;
	
	@Column(name = "daxoa")
	private boolean daXoa;
	
	//vai tro
	@ManyToOne @JoinColumn(name="vaitronv")
	VaiTro vaiTroNV;
	
	// don hang
	@OneToMany(mappedBy = "manv")
	List<DonHang> donhang;
}
