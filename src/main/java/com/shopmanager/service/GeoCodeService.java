package com.shopmanager.service;

import com.google.maps.model.LatLng;
import com.shopmanager.dao.Shop;

public interface GeoCodeService {

	LatLng getGeoCode(Shop shop);

}