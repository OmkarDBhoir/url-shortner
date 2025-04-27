package com.shorturl.shorturl.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.shorturl.services.UrlService;


@RestController
@RequestMapping("/api/url")
public class UrlController {
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping("/shorten")
	@ResponseBody
	public String shortUrl(@RequestParam String longUrl) {
		String shortUrl = null;
		
		try {
			System.out.print("Long url: " + longUrl);
			
			shortUrl = urlService.generateShortUrl(longUrl);
			
			System.out.print("Short Url: " + shortUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return shortUrl;
		
	}
}
