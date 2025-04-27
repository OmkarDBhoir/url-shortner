package com.shorturl.shorturl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shorturl.shorturl.modals.UrlMaster;

@Repository
public interface UrlMasterRepo extends JpaRepository<UrlMaster, Long> {
	
	List<UrlMaster> findByType(String type);
	
	UrlMaster findByTypeAndKey(String type, String key);

}
