package com.mobilestore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoanhThuNam {
	@Id
	Serializable ngayTao;
	Long tongDon;
	Long tongSP;
	Double giaThapNhat;
	Double giaCaoNhat;
	Double giaTB;
	Double tongTien;
}
