package co.edu.umanizales.myfristapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Seller {
    private String name;
    private String lastName;
    private byte age;
    private String identification;
    private Location city;
    private char gender;
}

