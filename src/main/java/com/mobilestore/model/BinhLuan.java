package com.mobilestore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="BinhLuan")
public class BinhLuan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maBL;
	private String taiKhoan;
	private String noiDung;
}
