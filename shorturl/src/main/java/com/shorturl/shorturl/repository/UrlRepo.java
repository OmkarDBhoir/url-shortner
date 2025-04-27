package com.shorturl.shorturl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shorturl.shorturl.modals.Url;

@Repository
public interface UrlRepo extends JpaRepository<Url, Long>{
	
	String getUrlByShortUrl(String shortUrl);
	
	String getUrlByLongUrl(String longUrl);
	
	boolean existsByShortUrl(String shortUrl);
}
