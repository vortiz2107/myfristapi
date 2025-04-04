package co.edu.umanizales.myfristapi.controller;

import co.edu.umanizales.myfristapi.model.Location;
import co.edu.umanizales.myfristapi.model.Seller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/seller")

public class SellerController {
    @GetMapping
    public Seller getSeller() {

        Location Manizales = new Location("17001", "Manizales");

        Seller Ronnie = new Seller("10252924", "Ronnie", "Jaramillo", Manizales, (byte)32, 'M');
        Seller Valentina = new Seller("30315827", "Valentina", "Manrique", Manizales,(byte) 22, 'F');
        Seller Sofia = new Seller("1007234852", "Sofia", "Suarez", Manizales, (byte)28, 'F');
        Seller Daniel = new Seller("1002654730", "Daniel", "Morales", Manizales,(byte)40, 'M');
        Seller Juan = new Seller("10876345", "Juan", "Buitrago", Manizales, (byte) 22, 'M');

        return Valentina;

    }
}
