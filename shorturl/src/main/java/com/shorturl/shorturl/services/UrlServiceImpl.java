package com.shorturl.shorturl.services;

import java.util.Calendar;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shorturl.shorturl.constants.UrlServiceConstants;
import com.shorturl.shorturl.modals.Url;
import com.shorturl.shorturl.repository.UrlRepo;

@Service
public class UrlServiceImpl implements UrlService {
	
	@Autowired
	private UrlRepo urlRepo;
	
	@Autowired
	private UrlMasterService urlMasterService;

	@Override
	public String getLongUrl(String shortUrl) {
		String longUrl = null;
		try {
			
			longUrl = urlRepo.getLongtUrlByShortUrl(shortUrl);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return longUrl;
	}

	@Override
	public String getShortUrl(String longUrl) {
		String shortUrl = null;
		
		try {
			
			shortUrl = urlRepo.getShortUrlByLongUrl(longUrl);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return shortUrl;
	}

	@Override
	public String generateRandomString(int length) {
		String generatedString = null;
		StringBuilder sb = null;
		Random random  = null;
		try {
			
			random = new Random();
			
			sb = new StringBuilder(length);
			
			for(int i=0; i< length; i++) {
				int index = random.nextInt(UrlServiceConstants.CHARS.length());
				sb.append(UrlServiceConstants.CHARS.charAt(index));
			}
			
			generatedString = sb.toString();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return generatedString;
	}

	@Override
	public String generateShortUrl(String longUrl) {
		String hash = null;
		Url url = null;
		String baseUrl = null;
		String shortUrl = null;
		
		try {
			
			shortUrl = urlRepo.getShortUrlByLongUrl(longUrl);
			
			baseUrl = urlMasterService.getValueByTypeAndKey(UrlServiceConstants.BASE_URL, UrlServiceConstants.BASE_URL);
			if(shortUrl != null) {
				return baseUrl + shortUrl;
			}
			
			shortUrl = generateRandomString(6);
			
			
			while(urlRepo.existsByShortUrl(shortUrl)) {
				shortUrl = generateRandomString(6);
			}
			
			System.out.println("Storing shortUrl");
			if(shortUrl != null) {
				url = new Url();
				url.setShortUrl(shortUrl);
				url.setLongUrl(longUrl);
				url.setCreateOn(Calendar.getInstance().getTime());
				urlRepo.saveAndFlush(url);
			}
			
			shortUrl = baseUrl + shortUrl;
			
			System.out.println("ShortUrl stored");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return shortUrl;
	}

}
