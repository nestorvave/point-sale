package com.accio.point_sale.services.Sale;

import java.util.List;

import com.accio.point_sale.domain.dtos.Sale.SaleCreateDtoRequest;
import com.accio.point_sale.domain.entities.Sale.Sale;
import com.accio.point_sale.domain.entities.User.User;

public interface SaleService {

	Sale createSale(SaleCreateDtoRequest saleRequest, User user);

	List<Sale> getAllSales();

}
