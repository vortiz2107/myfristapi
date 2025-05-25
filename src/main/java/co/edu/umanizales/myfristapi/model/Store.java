package co.edu.umanizales.myfristapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Store {
    private String code;
    private String name;
    private String address;
    private Location city;
}
