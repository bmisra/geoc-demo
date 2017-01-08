package com.shopmanager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.shopmanager.dao.Address;
import com.shopmanager.dao.Shop;

import scala.annotation.meta.setter;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopManagerApplication.class)
@SpringBootTest
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class DemoApplicationTests {

	@Value("${local.server.port}")
	private int port;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void contextLoads() {
	}
	
	//TODO: Separate test cases & negative test cases

	@Test
	public void testShop(){
		Shop shop = new Shop();
		Address shopAddress = new Address();
		shopAddress.setNumber("2539");
		shopAddress.setAddressLine1("Piedmont Rd NE");
		shopAddress.setAddressLine2("Atlanta, GA");
		shopAddress.setPostCode("30324");
		shop.setShopAddress(shopAddress);
		//TODO: fetch from properties
		restTemplate.put("http://localhost:"+port+"/shop/", shop);
		
		//TODO: fetch from properties
		ResponseEntity<Shop> response = restTemplate.getForEntity("http://localhost:"+port+"/shops/33.82453580000001/-84.36715079999999", Shop.class);
		
		Assert.assertTrue("200 response code", response.getStatusCode().is2xxSuccessful());
		Assert.assertNotNull("latitude check", response.getBody().getShopLatitude());
		Assert.assertNotNull("longitude check", response.getBody().getShopLongitude());
		
	}
}
