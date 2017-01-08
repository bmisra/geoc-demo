package com.shopmanager.service.impl;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.shopmanager.dao.Address;
import com.shopmanager.dao.Shop;
import com.shopmanager.service.GeoCodeService;

@Service
public class GeoCodeServiceImpl implements GeoCodeService {
	
	private static GeoApiContext context;
	
	private final static String apiKey = "AIzaSyD4RZZNt0fs8RE8xSjk8Dd2SpzVOF95GkI";
	
	private void initializeGeoCodeApi(){
		context = new GeoApiContext();
		context.setApiKey(apiKey);
	}
	/* (non-Javadoc)
	 * @see com.shopmanager.service.impl.GeoCodeService#getGeoCode(com.shopmanager.dao.Shop)
	 */
	@Override
	public LatLng getGeoCode(Shop shop){
		initializeGeoCodeApi();
		GeocodingResult[] geoCodingResults = null;
		LatLng geoCode = null;
		try{
			geoCodingResults = GeocodingApi.geocode(context, getGeoCodingAddress(shop)).await();
			geoCode = geoCodingResults[0].geometry.location;
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return geoCode;
	}

	private String getGeoCodingAddress(Shop shop){
		Address address = shop.getShopAddress();
		StringBuilder geoCodingAddress = new StringBuilder();
/*		if(shop.getShopName()!=null && shop.getShopName().trim()!=""){
			geoCodingAddress.append(shop.getShopName()).append(",");
		}*/
		if(address.getNumber()!=null && address.getNumber().trim()!=""){
			geoCodingAddress.append(address.getNumber()).append(",");
		}
		if(address.getAddressLine1()!=null && address.getAddressLine1().trim()!=""){
			geoCodingAddress.append(address.getAddressLine1()).append(",");
		}
		if(address.getAddressLine2()!=null && address.getAddressLine2().trim()!=""){
			geoCodingAddress.append(address.getAddressLine2()).append(",");
		}
		if(address.getPostCode()!=null && address.getPostCode().trim()!=""){
			geoCodingAddress.append(address.getPostCode());
		}
		
		return geoCodingAddress.toString();
	}
}
