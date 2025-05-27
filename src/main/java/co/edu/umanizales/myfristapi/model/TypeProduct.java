package co.edu.umanizales.myfristapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeProduct extends Parameter {
    public TypeProduct(String code, String description) {
        super(code, description);
    }
}
