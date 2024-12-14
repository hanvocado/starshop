package com.starshop.services.impl;

import java.util.Map;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starshop.entities.Address;
import com.starshop.services.GeoService;
import com.starshop.utils.Constants;

@Service
public class GeoServiceImpl implements GeoService {
	private final String NOMINATIM_API_URL = "https://nominatim.openstreetmap.org/search.php";
	private final String OSRM_DISTANCE_MATRIX_API_URL = "https://routing.openstreetmap.de/routed-car/table/v1/driving/";

    @Override
	public double[] getLatLongFromAddress(Address address) {
    	int retries = 3;
    	while (retries > 0) {    		
    		try {
    			String url = NOMINATIM_API_URL + "?q=" + address.toString() + "&format=jsonv2";
    			
    			// Call the API
    			RestTemplate restTemplate = new RestTemplate();
    			ResponseEntity<Map<String, Object>[]> responseEntity = restTemplate.exchange(
    					url,
    					HttpMethod.GET,
    					null,
    					new ParameterizedTypeReference<Map<String, Object>[]>() {}
    					);
    			Map<String, Object>[] response = responseEntity.getBody();
    			
    			if (response != null && response.length > 0) {
    				double latitude = Double.parseDouble(response[0].get("lat").toString());
    				double longitude = Double.parseDouble(response[0].get("lon").toString());
    				return new double[]{latitude, longitude};
    			}
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    			retries--;
    		}
    	}
        return null;
    }
    
    @Override
	public double getDistanceKm(Address address) {
    	int retries = 3;
    	while (retries > 0) {    		
    		try {
    			if (Constants.storeLatitude == null || Constants.storeLongitude == null) {
    				double[] storeLatLong = getLatLongFromAddress(Constants.storeAddress);    	
    				Constants.storeLatitude = storeLatLong[0];
    				Constants.storeLongitude = storeLatLong[1];
    			}
    			
    			Double userLatitude = address.getLatitude();
    			Double userLongitude = address.getLongitude();    			
    			if (userLatitude == null || userLongitude == null) {
    				double[] userLatLong = getLatLongFromAddress(address);
    				userLatitude = userLatLong[0];
    				userLongitude = userLatLong[1];
    			}
    			
    			String coordinates = Constants.storeLongitude + "," + Constants.storeLatitude + ";" + userLongitude + "," + userLatitude;
    			String url = OSRM_DISTANCE_MATRIX_API_URL + coordinates + "?annotations=distance";
    			
    			RestTemplate restTemplate = new RestTemplate();
    			@SuppressWarnings("unchecked")
				Map<String, Object> response = restTemplate.getForObject(url, Map.class);
    			
    			if (response != null && response.containsKey("distances")) {
    				@SuppressWarnings("unchecked")
    				List<List<Double>> distances = (List<List<Double>>) response.get("distances");
    				return distances.get(0).get(1)/1000.0; 
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    			retries--;
    		}
    	}
        return -1;
    }
}
