package com.mobilestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Binhluan")
public class BinhLuan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mabl")
	private int maBL;
	
	@Column(name = "noidung")
	private String noiDung;

	@ManyToOne
	@JoinColumn(name = "taikhoan")
	KhachHang taikhoan;
}
