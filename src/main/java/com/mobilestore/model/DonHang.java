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
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Positive(message = "Vui lòng chọn đơn hàng")
	private int maDon;
	
	@Column(name = "ngaytao")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
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
	@JsonIgnore
	@OneToMany(mappedBy = "madh")
	List<ChiTietDonHang> ctdh;
}
