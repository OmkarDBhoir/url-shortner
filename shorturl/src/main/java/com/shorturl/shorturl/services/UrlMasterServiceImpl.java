package com.shorturl.shorturl.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shorturl.shorturl.modals.UrlMaster;
import com.shorturl.shorturl.repository.UrlMasterRepo;

@Service
public class UrlMasterServiceImpl implements UrlMasterService {
	
	@Autowired
	private UrlMasterRepo urlMasterRepo;

	@Override
	public List<UrlMaster> findByType(String type) {
		List<UrlMaster> result = null;
		
		try {
			
			result = urlMasterRepo.findByType(type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UrlMaster findByTypeAndKey(String type, String key) {
		UrlMaster result = null;
		
		try {
			
			result = urlMasterRepo.findByTypeAndKey(type, key);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String getValueByTypeAndKey(String type, String key) {
		String result = null;
		
		try {
			
			UrlMaster entity = findByTypeAndKey(type, key);
			if(entity != null) {
				result = entity.getValue();
			} else {
				System.out.println("No result found!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<String> getValuesByType(String type) {
		List<String> result = null;
		
		try {
			
			List<UrlMaster> masterData = findByType(type);
			if(masterData != null && !masterData.isEmpty()) {
				result = new ArrayList<String>();
				for(UrlMaster obj: masterData) {
					result.add(obj.getValue());
				}
			} else {
				System.out.print("No values found!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Map<String, UrlMaster> fetchByType(String type) {
		Map<String, UrlMaster> result = null;
		
		try {
			List<UrlMaster> masterData = findByType(type);
			
			if(masterData != null) {
				result = new HashMap<String, UrlMaster>();
				for(UrlMaster obj: masterData) {
					result.put(obj.getKey(), obj);
				}
			} else {
				System.out.println("No data found!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Map<String, String> fetchKeyAndValueByType(String type) {
		Map<String, String> result = null;
		
		try {
			
			List<UrlMaster> masterData = findByType(type);
			
			if(masterData != null) {
				result = new HashMap<String, String>();
				for(UrlMaster obj: masterData) {
					result.put(obj.getKey(), obj.getValue());
				}
			} else {
				System.out.println("No data found!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
