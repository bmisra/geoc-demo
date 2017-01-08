package com.shopmanager.store;

import org.springframework.stereotype.Service;

import com.google.maps.model.LatLng;
import com.shopmanager.dao.Shop;


public interface Store {

	Shop addShop(Shop shop);

	Shop findNearestShop(LatLng geoCode);

}