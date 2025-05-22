package co.edu.umanizales.myfristapi.service;

import co.edu.umanizales.myfristapi.model.Location;
import co.edu.umanizales.myfristapi.model.Parameter;
import co.edu.umanizales.myfristapi.model.Product;
import co.edu.umanizales.myfristapi.model.TypeProduct;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter

public class ProductService {
    private List<Product> products;

    @Value("")
    private String productsFilename;

    @PostConstruct
    public void readProductsFromCSV() throws IOException, URISyntaxException {
        products = new ArrayList<>();

        Path pathFile = Paths.get(ClassLoader.getSystemResource(productsFilename).toURI());
        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1);
            while ((line = csvReader.readNext()) != null) {

                products.add(new Product(line[0],line[1],Double.parseDouble(line[2]),
                        Integer.parseInt(line[3]),new TypeProduct(line[4],line[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

}





