package co.edu.umanizales.myfristapi.service;

import co.edu.umanizales.myfristapi.model.Store;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StoreService {


    private List<Store> stores;

    @Value("STORES.csv")
    private String storesFilename;

    @Autowired
    private LocationService locationService;

    @PostConstruct
    public void readLocationsFromCSV() throws IOException, URISyntaxException {
        stores = new ArrayList<>();

        Path pathFile = Paths.get(ClassLoader.getSystemResource(storesFilename).toURI());
        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1);
            while ((line = csvReader.readNext()) != null) {

                stores.add(new Store(line[0], line[1], line[2], locationService.getLocationByCode(line[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public Store getStoreByCode(String code) {
        for (Store store : stores) {
            if (store.getCode().equals(code)) {
                return store;
            }
        }
        return null;
    }
}




