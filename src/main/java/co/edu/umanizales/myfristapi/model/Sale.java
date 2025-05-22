package co.edu.umanizales.myfristapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor

public class Sale {
    private Store store;
    private Seller seller;
    private LocalDate dateSale;
    private int quantity;
    private List products;


}
