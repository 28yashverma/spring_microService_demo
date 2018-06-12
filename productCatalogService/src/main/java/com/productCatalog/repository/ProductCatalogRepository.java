package com.productCatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productCatalog.model.ProductCatalog;

/**
 * Repository for Product Catalog
 * @author yeshendrav
 *
 */
public interface ProductCatalogRepository
		extends JpaRepository<ProductCatalog, Integer>, ProductCatalogCustomRepository {

	public List<ProductCatalog> findByid(Integer id);

	public List<ProductCatalog> findByproductName(String productName);

	public List<ProductCatalog> findByproductType(String type);

}
