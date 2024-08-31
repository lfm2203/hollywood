package br.com.lfm.hollywood.modelo.dto;

import lombok.Data;

@Data
public class FilmeDto {
    private Integer vlAno;
    private String dsTitulo;
    private String dsEstudio;
    private String dsProdutor;
    private boolean tpVencedor;
}
