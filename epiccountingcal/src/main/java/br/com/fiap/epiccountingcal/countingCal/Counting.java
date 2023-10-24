package br.com.fiap.epiccountingcal.countingCal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Counting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank
    String nome;

    @Size(min = 5, message = "{countingCal.description.size}")
    String descricao;

    @Min(1) @Max(100)
    Integer qtd;

    @Min(0) @Max(1000)
    Integer calorias;

    
}
