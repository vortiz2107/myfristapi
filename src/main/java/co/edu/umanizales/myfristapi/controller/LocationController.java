package co.edu.umanizales.myfristapi.controller;

import co.edu.umanizales.myfristapi.model.Location;
import co.edu.umanizales.myfristapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> getLocations() {

        return locationService.getLocations();

    }

    @GetMapping(path = "/{code}")
    public Location getLocationByCode(@PathVariable String code) {
        return locationService.getLocationByCode(code);
    }

    @GetMapping(path = "/states")
    public List<Location> getLocationsByStates() {
        return locationService.getStates();
    }

    @GetMapping(path = "/{name}")
    public Location getLocationByName(@PathVariable String name) {
        return locationService.getLocationByName(name);

    }

    @GetMapping(path = "/letters")
    public List<Location> getLocationByInicialLetters() {
        return locationService.getLocationByInicialLetters();
    }

    @GetMapping(path = "/codeLetters")
    public List<Location> getLocationByCodeAndLetters() {
        return locationService.getLocationByInicialLetters();
    }

    @GetMapping(path = "/capitals")
    public List<Location> getLocationByCapitals(){
        return locationService.getLocationByCapitals();
    }

}

