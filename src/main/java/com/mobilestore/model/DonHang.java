package com.mobilestore.model;

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
@Table(name="DonHang")
public class DonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Madon")
	private int maDon;
	@Column(name = "NgayTao")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayTao;
	@Column(name = "TongTien")
	private String tongTien;
	@Column(name = "TrangThai")
	private String trangThai;
	
	// khach hang
	@ManyToOne
	@JoinColumn(name = "Makh")
	KhachHang makh;
	
	// nhan vien
	@ManyToOne
	@JoinColumn(name = "Manv")
	NhanVien manv;
	
	// chi tiet don hang
	@OneToMany(mappedBy = "madon")
	List<ChiTietDonHang> ctdh;
}
