package co.edu.umanizales.myfristapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductSale {
    private Product product;
    private int quantity;
    private double subtotal;

    public ProductSale(Product product, int quantity, double subtotal) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
}