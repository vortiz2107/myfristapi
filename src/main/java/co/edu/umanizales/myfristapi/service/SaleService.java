package co.edu.umanizales.myfristapi.service;

import co.edu.umanizales.myfristapi.model.Sale;
import co.edu.umanizales.myfristapi.model.Store;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SaleService {
    private List<Sale> sales;

    @Value("SALES.csv")
    private String salesFilename;

    @Autowired
    private StoreService storeService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ParameterService parameterService;


    @PostConstruct
    public void readSalesFromCSV() throws IOException, URISyntaxException {
        sales = new ArrayList<>();

        Path pathFile = Paths.get(ClassLoader.getSystemResource(salesFilename).toURI());
        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1);
            while ((line = csvReader.readNext()) != null) {

                sales.add(new Sale (sellerService.getSellerByIdentification(line[0]),storeService.getStoreByCode(line[1]),Integer.parseInt(line[2]), line[3], LocalDate.parse(line[4]),Double.parseDouble(line[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

}


