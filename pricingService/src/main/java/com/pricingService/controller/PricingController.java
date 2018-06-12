package com.pricingService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pricingService.model.Price;
import com.pricingService.model.ProductCatalog;
import com.pricingService.repository.PricingRepository;

/**
 * Controller
 * 
 * @author yeshendrav
 *
 */
@RestController
@RequestMapping("/pricing")
public class PricingController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PricingRepository pricingRepo;

	/**
	 * Test controller for pricing
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		return "hello from pricing controller";
	}

	/**
	 * save price of the products
	 * 
	 * @param price
	 * @return
	 */
	@PostMapping("/savePrice")
	@HystrixCommand(fallbackMethod = "defaultMessage")
	public String savePriceForProduct(@RequestBody Price price) {
		System.out.println(price);
		ResponseEntity<List<ProductCatalog>> response = restTemplate.exchange(
				"http://productCatalogService/productCatalog/products", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ProductCatalog>>() {
				});

		if (response.hasBody()) {
			for (ProductCatalog ctDo : response.getBody()) {
				if (ctDo.getProductName().equalsIgnoreCase(price.getProductName())) {
					pricingRepo.save(new Price(ctDo.getProductName(), price.getPrice()));
					return "Successfull saved the price(s) of the products";
				}
			}
		}
		return "Not able to save the prices either there are no products or you are entering wrong product name";
	}

	public String defaultMessage(Price price) {
		return "There's something wrong with Service(s)";
	}

	/**
	 * get all product with price information
	 * 
	 * @return
	 */
	@GetMapping("/pricesOfProducts")
	public List<Price> getAllProductPrices() {
		return pricingRepo.findAll();
	}

}
