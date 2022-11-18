package com.mobilestore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="Donhang")
public class DonHang  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "madon")
	private int maDon;
	
	@Column(name = "ngaytao")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayTao = new Date();
	
	@Column(name = "tongtien")
	private double tongTien;
	
	@Column(name = "trangthai")
	private String trangThai;
	
	// khach hang
	@ManyToOne
	@JoinColumn(name = "makh")
	KhachHang makh;
	
	// nhan vien
	@ManyToOne
	@JoinColumn(name = "manv")
	NhanVien manv;
	
	// chi tiet don hang
	@OneToMany(mappedBy = "madh")
	List<ChiTietDonHang> ctdh;
}
