package co.edu.umanizales.myfristapi.controller;

import co.edu.umanizales.myfristapi.model.Location;
import co.edu.umanizales.myfristapi.model.Seller;
import co.edu.umanizales.myfristapi.model.Store;
import co.edu.umanizales.myfristapi.service.SellerService;
import co.edu.umanizales.myfristapi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping
    public List<Store> getStores() {
        return storeService.getStores();
    }

    @GetMapping(path = "/{code}")
    public Store getStoreByCode(@PathVariable String code) {
        return storeService.getStoreByCode(code);

    }
}