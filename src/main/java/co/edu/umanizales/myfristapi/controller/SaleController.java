package co.edu.umanizales.myfristapi.controller;

import co.edu.umanizales.myfristapi.model.Store;
import co.edu.umanizales.myfristapi.service.ParameterService;
import co.edu.umanizales.myfristapi.service.SaleService;
import co.edu.umanizales.myfristapi.service.SellerService;
import co.edu.umanizales.myfristapi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ParameterService parameterService;

    @GetMapping
    public String getSale(){
        return "venta";
    }

}
