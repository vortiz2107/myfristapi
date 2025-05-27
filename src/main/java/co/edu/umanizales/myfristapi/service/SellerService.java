package co.edu.umanizales.myfristapi.service;


import co.edu.umanizales.myfristapi.model.Location;
import co.edu.umanizales.myfristapi.model.Seller;
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
public class SellerService {
    private List<Seller> sellers;

    @Value("${sellers_filename}")
    private String sellersFilename;

    @Autowired
    private LocationService locationService;
    @Autowired
    private ParameterService parameterService;

    @PostConstruct
    public void readsellersFromCSV() throws IOException, URISyntaxException {

        sellers = new ArrayList<>();

        Path pathFile = Paths.get(ClassLoader.getSystemResource(sellersFilename).toURI());
        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1);

            while ((line = csvReader.readNext()) != null) {
                sellers.add(new Seller(line[0],parameterService.getTypeDocumentByCode(line[1]),line[2],line[3],
                        Byte.parseByte(line[4]),locationService.getLocationByCode(line[5])));

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public Seller getSellerByIdentification(String identification) {
        for (Seller seller : sellers) {
            if (seller.getIdentification().equals(identification)) {
                return seller;
            }
        }
        return null;
    }
}

