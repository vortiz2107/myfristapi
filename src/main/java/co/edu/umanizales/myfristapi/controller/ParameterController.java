package co.edu.umanizales.myfristapi.controller;


import co.edu.umanizales.myfristapi.dto.ProductDTO;
import co.edu.umanizales.myfristapi.model.*;
import co.edu.umanizales.myfristapi.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/parameter")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;


    @GetMapping(path = "/typedocuments")
    public List<Parameter> getTypeDocuments() {
        return parameterService.getParametersByType(1);
    }

    @GetMapping(path = "/typeproducts")
    public List<Parameter> getTypeProducts() {
        return parameterService.getParametersByType(2);
    }

    @GetMapping(path = "/products")
    public List<Parameter> getProducts() {
        return parameterService.getParametersByType(3);
    }

    @PostMapping(path = "/product")
    public String addProduct(@RequestBody ProductDTO product) {
        TypeProduct type = parameterService.getTypeProductByCode(product.getCodeTypeProduct());
        if (type == null) {
            return "Type Product with code " + product.getCode() + " does not exist";
        } else {
            return parameterService.addProduct(
                    new Product(product.getCode(), product.getDescription(),
                            product.getPrice(), product.getStock(), type));
        }

    }


    @PostMapping("/listproducts")
    public String saveProducts(@RequestBody List<ProductDTO> productsDTO) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : productsDTO) {
            TypeProduct type = parameterService.getTypeProductByCode(productDTO.getCodeTypeProduct());
            if (type == null) {
                return "Type Product with code " + productDTO.getCode() + " does not exist";
            } else {
                products.add(
                        new Product(productDTO.getCode(), productDTO.getDescription(),
                                productDTO.getPrice(), productDTO.getStock(), type));

            }
        }
        return parameterService.addProducts(products);
    }

    @GetMapping("product/{code}")
    public ResponseEntity<?> getProductByCode(@PathVariable String code) {
        Parameter product = parameterService.getProductByCode(code);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No product found with code: " + code);
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping(path = "typedocument/{code}")
    public Parameter getTypeDocumentBycode(@PathVariable String code) {
        return parameterService.getTypeDocumentByCode(code);
    }
}