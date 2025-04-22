package co.edu.umanizales.myfristapi.service;

import co.edu.umanizales.myfristapi.model.Location;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
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
public class LocationService {

    private List<Location> locations;

    @Value("${locations_filename}")
    private String locationsFilename;

    @PostConstruct
    public void readLocationsFromCSV() throws IOException, URISyntaxException {
        locations = new ArrayList<>();


        Path pathFile = Paths.get(ClassLoader.getSystemResource(locationsFilename).toURI());

        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1);
            while ((line = csvReader.readNext()) != null) {

                locations.add(new Location(line[2], line[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public Location getLocationByCode(String code) {
        for (Location location : locations) {
            if (location.getCode().equals(code)) {
                return location;
            }
        }
        return null;
    }

    public Location getLocationByName(String name) {
        for (Location location : locations) {
            if (location.getDescription().equals(name)) {
                return location;
            }
        }
        return null;
    }

    public List<Location> getLocationByInitialLetters(String initials) {
        List<Location> initial = new ArrayList<>();
        for (Location location : locations) {
            if (location.getDescription().startsWith(initials)) {
                initial.add(location);
            }
        }
        return initial;
    }
    

    public List<Location> getLocationByStateCode(String code) {
        List<Location> states = new ArrayList<>();
        for (Location location : locations) {
            if (location.getCode().equals(code)) {
                states.add(location);
            }
        }
        return states;
    }

    public List<Location> getStates() {
        List<Location> states = new ArrayList<>();
        for (Location location : locations) {
            if (location.getCode().length() == 2) {
                states.add(location);
            }
        }
        return states;
    }


    public List<Location> getCapitals() {
        List<Location> capitals = new ArrayList<>();
        for (Location location : locations) {
            if (location.getCode().endsWith("01") && location.getCode().length()==5) {
                capitals.add(location);
            }
        }
        return capitals;
    }

}


