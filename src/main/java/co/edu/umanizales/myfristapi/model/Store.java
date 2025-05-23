package co.edu.umanizales.myfristapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Store {
    private String code;
    private String name;
    private String address;
    private Location city;
}
