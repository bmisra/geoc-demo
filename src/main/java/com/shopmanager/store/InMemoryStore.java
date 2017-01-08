package com.shopmanager.store;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.maps.model.LatLng;
import com.shopmanager.dao.Shop;
import com.shopmanager.util.Utils;

@Service
public class InMemoryStore implements Store {
	
	private List<Shop> shopList = new ArrayList<Shop>();
	
	/* (non-Javadoc)
	 * @see com.shopmanager.store.Store#addShop(com.shopmanager.dao.Shop)
	 */
	@Override
	public Shop addShop(Shop shop){
		shopList.add(shop);
		return shop;
	}
	
	/* (non-Javadoc)
	 * @see com.shopmanager.store.Store#findNearestShop(com.google.maps.model.LatLng)
	 */
	@Override
	public Shop findNearestShop(LatLng geoCode){
		double originalLat  = geoCode.lat;
		double originalLng = geoCode.lng;
		double nearestDistance = 0;
		Shop nearestShop = null;
		for(Shop shop: shopList){
			//System.out.println("Shop:"+ shop);
			double targetLat = shop.getShopLatitude();
			double targetLng = shop.getShopLongitude();
			
			double dist = Utils.hoversineDisatnceFormula(originalLat, originalLng, targetLat, targetLng);
			//System.out.println("dist:"+dist);
			if(dist < nearestDistance || nearestDistance == 0){
				nearestShop = shop;
				nearestDistance = dist;
			}
		}
		return nearestShop;
	}

}
