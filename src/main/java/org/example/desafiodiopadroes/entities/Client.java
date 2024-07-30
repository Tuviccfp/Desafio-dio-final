package org.example.desafiodiopadroes.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(value = "cliente")
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nome;
    private String email;
    private String cpf;
    private int matricula;
    @CreatedDate
    private Date dataCadastro;

    @DBRef
    private Enderess enderess;

    public Client(String id, String nome, String email, String cpf, Enderess enderess) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataCadastro = new Date();
        this.enderess = enderess;
        generateMatricula();
    }

    protected void generateMatricula() {
        this.matricula = (int) (Math.random() * 9000 + 1);
    }
}
