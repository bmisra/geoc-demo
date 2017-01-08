package com.shopmanager.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.shopmanager.dao.Address;
import com.shopmanager.dao.Shop;
import com.shopmanager.service.GeoCodeService;
import com.shopmanager.service.impl.GeoCodeServiceImpl;
import com.shopmanager.store.Store;

@RestController
public class ProcessReqResController {

	@Autowired
	private GeoCodeService getCodeService;
	
	@Autowired
	private Store store;
	@RequestMapping(path="/shop", method = RequestMethod.PUT)
	public Shop addShop(@RequestBody Shop shop, HttpServletResponse response){
		LatLng geoCode = getCodeService.getGeoCode(shop);
		if(geoCode != null){
			shop.setShopLatitude(geoCode.lat);
			shop.setShopLongitude(geoCode.lng);
			store.addShop(shop);
		}
		
		if(geoCode==null){
			//returning HTTP 404 in case the address/shop is not found in geocoding
			System.out.println("setting error 404");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return shop;
	}
	
	@RequestMapping(path = "/shops/{latitude}/{longitude}", method = RequestMethod.GET)
	public Shop findNearestShop(@PathVariable double latitude, @PathVariable double longitude, HttpServletResponse response){
		LatLng geoCode =  new LatLng(latitude, longitude);
		Shop shop = store.findNearestShop(geoCode);
		//TODO: Handle no shops entered yet scenario
		if(shop==null){
			//returning HTTP 404 in case the address/shop is not found in geocoding
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return shop;
	}
	
/*	@RequestMapping(path="/getshop", method = RequestMethod.GET)
	public Shop getShop(){
		Shop shop = new Shop();
		shop.setShopId(1234);shop.setShopName("My New Shop");
		Address address = new Address();
		address.setAddressLine1("address line 1");
		address.setAddressLine2("address line 2");
		address.setNumber("234");
		address.setPostCode("700026");
		shop.setShopAddress(address);
		return shop;
		
	}*/
	
}
