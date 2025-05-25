package co.edu.umanizales.myfristapi.service;

import co.edu.umanizales.myfristapi.model.*;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class ParameterService {


    private List<Parameter> parameters;

    @Value("")
    private String parametersFilename;


    @PostConstruct
    public void readLocationsFromCSV() throws IOException, URISyntaxException {
        parameters = new ArrayList<>();

        parameters.add(new TypeDocument("CC", "Cédula de Ciudadanía"));
        parameters.add(new TypeDocument("NIT", "Número Identificación Tributaria"));

        TypeProduct pcs = new TypeProduct("01", "Computers");
        parameters.add(pcs);
        parameters.add(new TypeProduct("02", "Monitors"));
        parameters.add(new TypeProduct("03", "Memoirs"));
        parameters.add(new TypeProduct("04","Mouses"));




    }

    public List<Parameter> getParametersByType(int type) {
        List<Parameter> result = new ArrayList<>();
        for (Parameter p : parameters) {
            switch (type) {
                case 1:
                    if (p instanceof TypeDocument) {
                        result.add(p);
                    }
                    break;
                case 2:
                    if (p instanceof TypeProduct) {
                        result.add(p);
                    }
                    break;
                case 3:
                    if (p instanceof Product) {
                        result.add(p);
                    }
            }
        }
        return result;
    }

    public Product getProductByCode(String code) {
        for (Parameter p : parameters) {
            if (p.getCode().equals(code)) {
                return (Product) p;
            }
        }
        return null;
    }


    public String addProduct(Product product) {
        /// deberia validar que ya no exista
        if( getProductByCode(product.getCode()) == null ) {
            parameters.add(product);
            return "Product added";
        }
        else {
            return "Product already exists";
        }

    }

    public String addProducts(List<Product> products) {
        for(Product p : products) {
            String result =addProduct(p);
            if(result.equals("Product already exists")) {
                return "Product already exists "+ p.getCode();
            }
        }
        return "Products addeds";
    }

    public TypeProduct getTypeProductByCode(String code) {
        for (Parameter p : parameters) {
            if (p instanceof TypeProduct && p.getCode().equalsIgnoreCase(code)) {
                return (TypeProduct) p;
            }
        }
        return null;
    }

    public TypeDocument getTypeDocumentByCode (String code) {
        for (Parameter p : parameters) {
            if (p instanceof TypeDocument && p.getCode().equalsIgnoreCase(code)) {
                return (TypeDocument) p;
            }
        }
        return null;
    }
}

