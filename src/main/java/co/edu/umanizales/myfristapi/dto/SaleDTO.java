package co.edu.umanizales.myfristapi.dto;

import co.edu.umanizales.myfristapi.model.ProductSale;

import java.time.LocalDate;
import java.util.List;

public class SaleDTO {
    private String seller;
    private String store;
    private List<ProductSale> products;
    private LocalDate saleDate;

}
