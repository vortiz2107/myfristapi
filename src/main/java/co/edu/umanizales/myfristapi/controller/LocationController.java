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

    @GetMapping(path = "/{name}")
    public Location getLocationByName(@PathVariable String name) {
        return locationService.getLocationByName(name);

    }

    @GetMapping(path = "/letters/{initials}")
    public List<Location> getLocationByInitialLetters(@PathVariable String initials) {
        return locationService.getLocationByInitialLetters(initials);
    }

    @GetMapping(path = "/stateCode/{code}")
    public List<Location> getLocationByStateCode(@PathVariable String code) {
        return locationService.getLocationByStateCode(code);
    }

    @GetMapping(path = "/states")
    public List<Location> getStates() {
        return locationService.getStates();
    }

    @GetMapping(path = "/capitals")
    public List<Location> getCapitals() {
        return locationService.getCapitals();
    }

}

