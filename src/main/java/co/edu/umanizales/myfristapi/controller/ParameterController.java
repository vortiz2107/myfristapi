package co.edu.umanizales.myfristapi.controller;


import co.edu.umanizales.myfristapi.model.*;
import co.edu.umanizales.myfristapi.service.LocationService;
import co.edu.umanizales.myfristapi.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/parameter")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(path = "/typedocuments")
    public List<Parameter> getTypeDocuments() {
        return parameterService.getParametersByType(1);
    }

    @RequestMapping(path = "/typeproducts")
    public List<Parameter> getTypeProducts() {
        return parameterService.getParametersByType(2);
    }

    @RequestMapping(path = "/products")
    public List<Parameter> getProducts() {
        return parameterService.getParametersByType(3);
    }

}