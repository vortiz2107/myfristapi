package co.edu.umanizales.myfristapi.controller;

import co.edu.umanizales.myfristapi.model.Product;
import co.edu.umanizales.myfristapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

        @GetMapping
        public List<Product> getProducts() {
            return productService.getProducts();

        }
}
