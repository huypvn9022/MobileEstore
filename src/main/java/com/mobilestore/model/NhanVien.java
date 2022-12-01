package com.mobilestore.model;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="Nhanvien")
public class NhanVien {
	@Id
	@Column(name = "taikhoan")
	private String taiKhoan;
	
	@Column(name = "matkhau")
	private String matKhau;
	
	@Column(name = "hoten")
	private String hoTen;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "sđt")
	private String SĐT;
	
	@Column(name = "diachi")
	private String diaChi;
	
	@Column(name = "ngaysinh")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaySinh;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "vaitronv")
	VaiTro vaiTroNV;
	
	// don hang
	@JsonIgnore
	@OneToMany(mappedBy = "manv")
	List<DonHang> donhang;

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return null;
//	}
//
//	@Override
//	public String getPassword() {
//		return this.getMatKhau();
//	}
//
//	@Override
//	public String getUsername() {
//		return this .getTaiKhoan();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}

	

}
