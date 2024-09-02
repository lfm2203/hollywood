package br.com.lfm.hollywood.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"produtores", "estudios"})
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "VL_ANO")
    private Integer vlAno;

    @Column(name = "DS_TITULO")
    private String dsTitulo;

    @Column(name = "TP_VENCEDOR", length = 1)
    private String tpVencedor;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilmeProdutor> produtores;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilmeEstudio> estudios;
}
