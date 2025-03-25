package co.edu.umanizales.myfristapi.controller;

import co.edu.umanizales.myfristapi.model.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/location")
public class LocationController {
    @GetMapping
    public String getLocation(){

        Location Manizales = mew Location()



        return "Manizales";
    }
}
