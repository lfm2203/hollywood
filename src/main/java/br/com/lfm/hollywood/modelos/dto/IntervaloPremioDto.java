package br.com.lfm.hollywood.modelos.dto;

import lombok.Data;

import java.util.List;

@Data
public class IntervaloPremioDto {
    List<ProdutorVencedorDto> min;
    List<ProdutorVencedorDto> max;
}