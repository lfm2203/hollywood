package br.com.lfm.hollywood.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmeProdutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_FILME")
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTOR")
    private Produtor produtor;
}
