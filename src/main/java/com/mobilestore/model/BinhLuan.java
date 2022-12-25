package com.mobilestore.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Binhluan")
public class BinhLuan implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mabl")
	private int maBL;
	
	@Column(name = "noidung")
	private String noiDung;
	
	@Column(name = "ngaybl")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-M-yyyy hh:mm:ss")
	private Date ngayBL;
	
	@ManyToOne
	@JoinColumn(name = "masp")
	SanPham masp;
	
	@ManyToOne
	@JoinColumn(name = "taikhoan")
	KhachHang taikhoan;
	
}
