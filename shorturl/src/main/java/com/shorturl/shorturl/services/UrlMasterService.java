package com.shorturl.shorturl.services;

import java.util.List;
import java.util.Map;

import com.shorturl.shorturl.modals.UrlMaster;

public interface UrlMasterService {
	
	public List<UrlMaster> findByType(String type);
	
	public UrlMaster findByTypeAndKey(String type, String key);
	
	public String getValueByTypeAndKey(String type, String key);
	
	public List<String> getValuesByType(String type);
	
	public Map<String, UrlMaster> fetchByType(String type);
	
	public Map<String, String> fetchKeyAndValueByType(String type);

}
