package com.mobilestore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="DonHang")
public class DonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maDon;
	private String maKH;
	private String maNV;
	private Date ngayTao;
	private String trangThai;
	
	// khach hang
	@ManyToOne
	@JoinColumn(name = "Ma_KH")
	KhachHang taikhoan;
	
	// nhan vien
	@ManyToOne
	@JoinColumn(name = "Ma_NV")
	NhanVien manv;
	
	// chi tiet don hang
	@OneToMany(mappedBy = "maDon")
	List<ChiTietDonHang> ctdh;
}
