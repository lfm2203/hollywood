package br.com.lfm.hollywood.modelo.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "VL_ANO")
    private Integer vlAno;

    @Column(name = "DS_TITULO")
    private String dsTitulo;

    @Column(name = "DS_ESTUDIO")
    private String dsEstudio;

    @Column(name = "DS_PRODUTOR")
    private String dsProdutor;

    @Column(name = "TP_VENCEDOR", length = 1)
    private String tpVencedor;
}
