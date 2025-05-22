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

        TypeProduct pcs = new TypeProduct("01", "Computadores");
        parameters.add(pcs);
        parameters.add(new TypeProduct("02", "Pantalla"));


        parameters.add(new Product("A", "MAC", 1000000, 8, pcs));


    }

    public List<Parameter> getParametersByType(int type) {
        List<Parameter> result = new ArrayList<>();
        for (Parameter p : parameters) {
            switch (type){
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
}