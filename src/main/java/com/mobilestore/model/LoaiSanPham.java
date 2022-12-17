package com.mobilestore.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Loaisp")
@AllArgsConstructor
@NoArgsConstructor
public class LoaiSanPham implements Serializable {
	@Id
	@Column(name = "maloai")
	@NotEmpty(message = "Mã loại không được bỏ trống")
	private String maLoai;
	
	@Column(name = "tenloai")
	@NotEmpty(message = "Tên loại không được bỏ trống")
	private String tenLoai;
	
	@Column(name = "daxoa")
	private boolean daXoa;
	
	@JsonIgnore
	@OneToMany(mappedBy = "maloai")
	List<SanPham> sanpham;
}
