package co.edu.umanizales.myfristapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {
    @GetMapping
    public String getSale(){
        return "venta";
    }

}
