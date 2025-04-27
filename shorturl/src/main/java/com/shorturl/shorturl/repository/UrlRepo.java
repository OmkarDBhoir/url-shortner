package com.shorturl.shorturl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shorturl.shorturl.modals.Url;

@Repository
public interface UrlRepo extends JpaRepository<Url, Long>{
	
	@Query(value="select u.long_url from url u where u.short_url =?1;", nativeQuery=true)
	String getLongtUrlByShortUrl(String shortUrl);
	
	@Query(value="select u.short_url from url u where u.long_url =?1;", nativeQuery = true)
	String getShortUrlByLongUrl(String longUrl);
	
	boolean existsByShortUrl(String shortUrl);
}
