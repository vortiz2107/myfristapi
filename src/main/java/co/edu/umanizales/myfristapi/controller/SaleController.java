package co.edu.umanizales.myfristapi.controller;


import co.edu.umanizales.myfristapi.model.Sale;
import co.edu.umanizales.myfristapi.model.Store;
import co.edu.umanizales.myfristapi.service.SaleService;
import co.edu.umanizales.myfristapi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(path = "/sale")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // Ver todas las ventas
    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    /* Cargar ventas manualmente desde CSV ya que las tengo desde mis postman
    primero cargo la lista de mis productos
    Despues de cargarlas, solo es copiar en mi URL sale y me retorna toda las ventas con los productos cargados*/

    @GetMapping("/load")
    public String loadSales() {
        try {
            saleService.readSalesFromCSV();
            return "Sales successfully loaded.";
        } catch (IOException | URISyntaxException e) {
            return "Error loading sales:" + e.getMessage();
        }
    }

    @GetMapping("/seller/{identification}")
    public List<Sale> getSalesBySeller(@PathVariable String identification) {
        return saleService.getSalesBySeller(identification);
    }

    @GetMapping("/store/{code}")
    public List<Sale> getSalesByStore(@PathVariable String code) {
        return saleService.getSalesByStore(code);
    }

    @GetMapping("/date/{date}")
    public List<Sale> getSalesByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return saleService.getSalesByDate(localDate);
    }
}