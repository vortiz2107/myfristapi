package co.edu.umanizales.myfristapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TypeDocument extends Parameter{

    public TypeDocument(String code, String description) {
        super(code, description);
    }
}
