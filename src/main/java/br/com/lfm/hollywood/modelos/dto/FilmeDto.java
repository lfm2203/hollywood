package br.com.lfm.hollywood.modelos.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilmeDto {
    private Integer vlAno;
    private String dsTitulo;
    private String tpVencedor;
    private List<EstudioDto> estudios;
    private List<ProdutorDto> produtores;
}
