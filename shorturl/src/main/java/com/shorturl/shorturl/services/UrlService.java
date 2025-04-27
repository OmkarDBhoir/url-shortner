package com.shorturl.shorturl.services;

public interface UrlService {
	
	public String getLongUrl(String shortUrl);
	
	public String getShortUrl(String longUrl);
	
	public String generateRandomString(int length);
	
	public String generateShortUrl(String longUrl);
}
