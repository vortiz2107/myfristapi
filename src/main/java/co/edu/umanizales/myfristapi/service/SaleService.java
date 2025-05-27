package co.edu.umanizales.myfristapi.service;

import co.edu.umanizales.myfristapi.dto.SaleDTO;
import co.edu.umanizales.myfristapi.model.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class SaleService {


    private final List<Sale> sales = new ArrayList<>();

    @Value("${sales_filename}")
    private String salesFilename;

    private final SellerService sellerService;
    private final StoreService storeService;
    private final ParameterService parameterService;

    @Autowired
    public SaleService(SellerService sellerService, StoreService storeService, ParameterService parameterService) {
        this.sellerService = sellerService;
        this.storeService = storeService;
        this.parameterService = parameterService;
    }


    public void readSalesFromCSV() throws IOException, URISyntaxException {
        sales.clear();
        Path pathFile = Paths.get(ClassLoader.getSystemResource(salesFilename).toURI());

        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1); //

            while ((line = csvReader.readNext()) != null) {
                if (line.length < 6) {
                    System.out.println("Invalid line: " + Arrays.toString(line));
                    continue;
                }

                String sellerId = line[0].trim();
                String storeCode = line[1].trim();
                int quantity = Integer.parseInt(line[2].trim());
                String productCode = line[3].trim();
                LocalDate dateSale = LocalDate.parse(line[4].trim());
                double totalSale = Double.parseDouble(line[5].trim());

                Seller seller = sellerService.getSellerByIdentification(sellerId);
                Store store = storeService.getStoreByCode(storeCode);
                Product product = parameterService.getProductByCode(productCode);

                if (product == null) {
                    System.out.println(" Product not found for code: " + productCode);
                    continue;
                }

                List<ProductSale> productList = new ArrayList<>();
                productList.add(new ProductSale(product, quantity, totalSale));

                Sale sale = new Sale(seller, store, quantity, productList, dateSale, totalSale);
                sales.add(sale);
            }

        } catch (CsvValidationException e) {
            throw new RuntimeException("CSV validation error", e);
        }
    }

    public List<Sale> getAllSales() {
        return sales;
    }

    public List<Sale> getSalesBySeller(String identification) {
        List<Sale> result = new ArrayList<>();

        for (Sale sale : sales) {
            if (sale.getSeller() != null &&
                    sale.getSeller().getIdentification().equalsIgnoreCase(identification)) {
                result.add(sale);
            }
        }

        return result;
    }


    public List<Sale> getSalesByStore(String code) {
        List<Sale> result = new ArrayList<>();

        for (Sale sale : sales) {
            if (sale.getStore() != null &&
                    sale.getStore().getCode().equalsIgnoreCase(code)) {
                result.add(sale);
            }
        }

        return result;
    }

    public List<Sale> getSalesByDate(LocalDate date) {
        List<Sale> result = new ArrayList<>();

        for (Sale sale : sales) {
            if (sale.getDateSale() != null && sale.getDateSale().equals(date)) {
                result.add(sale);
            }
        }

        return result;
    }
}