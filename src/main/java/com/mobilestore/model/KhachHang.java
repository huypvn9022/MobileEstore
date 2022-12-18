package com.mobilestore.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Khachhang")
public class KhachHang implements Serializable{
	@Id
	@Column(name = "taikhoan")
	@NotEmpty(message="Tài khoản không được bỏ trống")
	private String taiKhoan;
	
	@Column(name = "matkhau")
	@NotEmpty(message="Mật khẩu không được bỏ trống")
	private String matKhau;
	
	@Column(name = "hoten")
	@NotEmpty(message = "Họ và tên không được bỏ trống")
	private String hoTen;
	
	@Column(name = "email")
	@NotEmpty(message = "Email Không được bỏ trống")
	@Email(message = "Email không đúng định dạng")
	private String email;
	
	@Column(name = "SĐT")
	@NotEmpty(message = "Số điện thoại không được bỏ trống")
	private String SDT;
	
	@Column(name = "diachi")
	@NotEmpty(message = "Địa chỉ không được bỏ trống")
	private String diaChi;

	@Column(name = "daxoa")
	private boolean daXoa;
	
	// binh luan
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<BinhLuan> binhluan;
	
	// vai tro
	@ManyToOne 
	@JoinColumn(name="vaitrokh")
	VaiTro vaiTroKH;
	
	// don hang
	@JsonIgnore
	@OneToMany(mappedBy = "makh", cascade = {CascadeType.ALL})
	List<DonHang> donhang;
}
