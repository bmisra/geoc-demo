#Shops near you API suite contains  two APIs.
1)
A Retail Manager (using a RESTful client e.g. Chrome's Postman), can post a JSON payload to a shops endpoint containing details of the shop they want to add. The server side API uses the Google Maps Geocoding API to retrieve the longitude and latitude for the provided shopAddress.

Sample URL: http://localhost:8080/shop/

Request one:
{
  "shopId": 4932,
  "shopName": "Grocery",
  "shopAddress": {
    "number": "546",
    "addressLine1": "Main St NE",
    "addressLine2": "Atlanta, GA",
    "postCode": "30324"
  }

}

request two:

{
  "shopId": 4921,
  "shopName": "Grocery",
  "shopAddress": {
    "number": "2539",
    "addressLine1": "Piedmont Rd NE",
    "addressLine2": "Atlanta, GA",
    "postCode": "30324"
  }
}

request three:

{
  "shopId": 4486,
  "shopName": "Grocery",
  "shopAddress": {
    "number": "25",
    "addressLine1": "Brentwood Promenade Ct",
    "addressLine2": "Brentwood, MO",
    "postCode": "63144"
  }
}


2) The server side API accepts two URL parameters for the request: latitude & longitude and API determines the nearest shop.

http://localhost:8080/shops/38.6275948/-90.34350839999999

Response:

{
  "shopId": 4486,
  "shopName": "Grocery",
  "shopAddress": {
    "number": "25",
    "addressLine1": "Brentwood Promenade Ct",
    "addressLine2": "Brentwood, MO",
    "postCode": "63144"
  },
  "shopLatitude": 38.6275948,
  "shopLongitude": -90.34350839999999
}

Info:
A) The google geocoding API is used for this demo. Further information can be found in https://developers.google.com/maps/documentation/geocoding/intro

B) The hoversine formula for calculating distance between two point have been used in this demo.
https://en.wikipedia.org/wiki/Great-circle_distance#Computational_formulas
http://www.movable-type.co.uk/scripts/latlong.html
https://bigdatanerd.wordpress.com/2011/11/03/java-implementation-of-haversine-formula-for-distance-calculation-between-two-points/

C)Scope of imporvements:
#Improved error handling for different scenarios. (address not found/ no shop entered / shops with same id etc)
#Proper use of Logger
#Improved responsiveness for address finding
# Use of database instead of inmomery collection.
# Use of caching to improve performance & less geocoding API invocation.






