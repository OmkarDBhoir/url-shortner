package com.shorturl.shorturl.services;

import java.util.Calendar;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shorturl.shorturl.constants.UrlServiceConstants;
import com.shorturl.shorturl.modals.Url;
import com.shorturl.shorturl.repository.UrlRepo;
import com.shorturl.shorturl.utils.FLogger;

@Service
public class UrlServiceImpl implements UrlService {
	
	private static final String LOG_CAT = UrlServiceConstants.LOGGER_CONSTANT;
	private static final String THIS_CLASS = UrlServiceImpl.class.getName();
	
	@Autowired
	private UrlRepo urlRepo;
	
	@Autowired
	private UrlMasterService urlMasterService;

	@Override
	public String getLongUrl(String shortUrl) {
		String methodName = "getLongUrl";
		String longUrl = null;
		try {
			
			longUrl = urlRepo.getLongtUrlByShortUrl(shortUrl);
			
			if(longUrl == null) {
				FLogger.error(LOG_CAT, THIS_CLASS,  methodName, "No Long URL found!");
			}
			
		} catch (Exception e) {
			
			FLogger.error(LOG_CAT, THIS_CLASS, methodName, e);
		}
		
		return longUrl;
	}

	@Override
	public String getShortUrl(String longUrl) {
		String methodName = "getShortUrl";
		String shortUrl = null;
		
		try {
			
			shortUrl = urlRepo.getShortUrlByLongUrl(longUrl);
			
			if(shortUrl == null) {
				FLogger.error(LOG_CAT, THIS_CLASS, methodName, "No Short URL found!");
			}
			
		} catch (Exception e) {
			
			FLogger.error(LOG_CAT, THIS_CLASS, methodName, e);
		}
		
		return shortUrl;
	}

	@Override
	public String generateRandomString(int length) {
		String methodName = "generateRandomString";
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
			FLogger.error(LOG_CAT, THIS_CLASS, methodName, e);
			
		}
		
		return generatedString;
	}

	@Override
	public String generateShortUrl(String longUrl) {
		String methodName = "generateShortUrl";
		String hash = null;
		Url url = null;
		String baseUrl = null;
		String shortUrl = null;
		
		try {
			
			shortUrl = urlRepo.getShortUrlByLongUrl(longUrl);
			
			baseUrl = urlMasterService.getValueByTypeAndKey(UrlServiceConstants.BASE_URL, UrlServiceConstants.BASE_URL);
			if(shortUrl != null) {
				FLogger.info(LOG_CAT, THIS_CLASS, methodName, "Short URL Already exist");
				return baseUrl + shortUrl;
			}
			
			FLogger.debug(LOG_CAT, THIS_CLASS, methodName, "Generating randomHash");
			
			shortUrl = generateRandomString(6);
			
			
			while(urlRepo.existsByShortUrl(shortUrl)) {
				shortUrl = generateRandomString(6);
			}
			
			FLogger.debug(LOG_CAT, THIS_CLASS, methodName, "Short Url generated and storing in DB: " + shortUrl);
			if(shortUrl != null) {
				url = new Url();
				url.setShortUrl(shortUrl);
				url.setLongUrl(longUrl);
				url.setCreateOn(Calendar.getInstance().getTime());
				urlRepo.saveAndFlush(url);
			}
			
			shortUrl = baseUrl + shortUrl;
			
			FLogger.error(LOG_CAT, THIS_CLASS, methodName, "URL stored in DB");
			
		} catch (Exception e) {
			FLogger.error(LOG_CAT, THIS_CLASS, methodName, e);
		}
		
		return shortUrl;
	}

}
