package co.edu.umanizales.myfristapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public abstract class Parameter {
    private String code;
    private String description;
}
