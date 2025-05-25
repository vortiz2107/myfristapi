package co.edu.umanizales.myfristapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Seller {
    private String identification;
    private TypeDocument typeDocument;
    private String name;
    private String lastName;
    private byte age;
    private Location city;
}

