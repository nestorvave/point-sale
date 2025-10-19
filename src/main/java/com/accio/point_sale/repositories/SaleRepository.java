package com.accio.point_sale.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accio.point_sale.domain.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, UUID> {

}
