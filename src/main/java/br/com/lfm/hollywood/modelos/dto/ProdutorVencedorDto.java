package br.com.lfm.hollywood.modelos.dto;

import lombok.Data;

@Data
public class ProdutorVencedorDto {
    String producer;
    Integer interval;
    Integer previousWin;
    Integer followingWin;
}
