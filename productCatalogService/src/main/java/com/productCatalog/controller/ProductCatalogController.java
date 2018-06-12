package com.productCatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productCatalog.model.ProductCatalog;
import com.productCatalog.repository.ProductCatalogRepository;

@RestController
@RequestMapping("/productCatalog/")

/**
 * 
 * @author yeshendrav Product Catalog controller contains information of
 *         products
 *
 */
public class ProductCatalogController {

	@Autowired
	private ProductCatalogRepository productCatalogRepo;

	@GetMapping("/hello")
	/**
	 * test controller
	 * 
	 * @return
	 */
	public String hello() {
		return "hello from product catalog controller";
	}

	@GetMapping("/products")
	/**
	 * list of all products
	 * 
	 * @return
	 */
	public List<ProductCatalog> getAllProducts() {
		return productCatalogRepo.findAll();
	}

	/**
	 * save a particular product with the given information
	 * 
	 * @param productCatalog
	 * @return
	 */
	@PostMapping("/saveProduct")
	public ProductCatalog saveProduct(@RequestBody ProductCatalog productCatalog) {
		return productCatalogRepo.save(productCatalog);
	}

	/**
	 * delete a product
	 * 
	 * @param pc
	 */
	@PostMapping("/deleteProduct")
	public void deleteProduct(@RequestBody ProductCatalog pc) {
		productCatalogRepo.delete(pc);
	}

	/**
	 * get a product by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/productById/{id}")
	public List<ProductCatalog> findProductById(@PathVariable Integer id) {
		return productCatalogRepo.findByid(id);
	}

	/**
	 * get a product by type
	 * 
	 * @param type
	 * @return
	 */
	@PostMapping("/productByType/{type}")
	public List<ProductCatalog> findProductByType(@PathVariable String type) {
		return productCatalogRepo.findByproductType(type);
	}

	/**
	 * get a product by name
	 * 
	 * @param productName
	 * @return
	 */
	@PostMapping("/productByName/{name}")
	public List<ProductCatalog> findProductByName(@PathVariable(name = "name") String productName) {
		return productCatalogRepo.findByproductName(productName);
	}

}
