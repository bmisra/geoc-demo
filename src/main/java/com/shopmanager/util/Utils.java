package com.shopmanager.util;

public class Utils {

	
	public static double hoversineDisatnceFormula(double originalLat, double originalLng, double targetLat, double targetLng){
		final int radiusOfEarth = 6371;
		double latDiff = Math.toRadians(targetLat - originalLat);
		double lngDiff = Math.toRadians(targetLng - originalLng);
		
		double n1 = Math.pow(Math.sin(latDiff/2), 2) + Math.pow(Math.sin(lngDiff/2), 2) * Math.cos(Math.toRadians(originalLat)) * Math.cos(Math.toRadians(originalLng));
		
		double n2 = 2 * Math.atan2(Math.sqrt(n1), Math.sqrt(1 - n1));
		
		double distance  = radiusOfEarth * n2;
		
		return distance;
	}
}
