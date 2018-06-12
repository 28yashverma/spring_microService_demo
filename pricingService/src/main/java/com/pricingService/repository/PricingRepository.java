package com.pricingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pricingService.model.Price;

/**
 * Repository
 * @author yeshendrav
 *
 */
public interface PricingRepository extends JpaRepository<Price, Integer>, PricingRepositoryCustom {

}
