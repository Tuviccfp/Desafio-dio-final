package org.example.desafiodiopadroes.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(value = "endereco")
public class Enderess implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String street;
    private String state;
    private Integer number_house;

    public Enderess(String id, String street, String state, int number_house) {
        this.id = id;
        this.street = street;
        this.state = state;
        this.number_house = number_house;
    }
}
