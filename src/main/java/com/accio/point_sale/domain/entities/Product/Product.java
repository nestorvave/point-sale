package com.accio.point_sale.domain.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.accio.point_sale.domain.entities.SaleItem.SaleItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = true)
	private String description;

	@Column(nullable = false, precision = 10, scale = 2)
	@Builder.Default
	private BigDecimal price = BigDecimal.ZERO;

	@Builder.Default
	private Integer stock = 0;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<SaleItem> saleItems;

}
