package com.shorturl.shorturl.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.shorturl.shorturl.services.UrlService;


@RestController
@RequestMapping("/")
public class UrlController {
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping(value="/api/url/shorten", consumes="text/plain")
	@ResponseBody
	public String shortUrl(@RequestBody String longUrl) {
		String shortUrl = null;
		
		try {
			
			shortUrl = urlService.generateShortUrl(longUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return shortUrl;
		
	}
	
	@GetMapping(value="/{shortUrl}")
	public RedirectView redirectToOriginalUrl(@PathVariable String shortUrl) {
		
		RedirectView redirectView = new RedirectView();
		
		try {
			System.out.println("shorUrl" + shortUrl);
			
			String longUrl = urlService.getLongUrl(shortUrl);
			
			System.out.println("Long url: " + longUrl);
			
			redirectView.setUrl(longUrl);
			redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
			return redirectView;
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectView.setUrl(shortUrl);
			redirectView.setStatusCode(HttpStatus.BAD_REQUEST);
		}
		
		return redirectView;
	}
}
