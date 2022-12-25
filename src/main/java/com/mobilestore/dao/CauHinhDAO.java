package com.mobilestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobilestore.model.CauHinh;

public interface CauHinhDAO extends JpaRepository<CauHinh, Integer>{
	
	@Query(value = "SELECT o FROM CauHinh o WHERE o.doPhanGiai LIKE ?1 OR o.heDH LIKE ?1 OR o.chip LIKE ?1")
	List<CauHinh> findAllByKeyword( String keywords);
	
	Page<CauHinh> findAllByDaXoaFalse(Pageable pageable);

}
