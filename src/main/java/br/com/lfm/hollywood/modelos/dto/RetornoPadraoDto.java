package br.com.lfm.hollywood.modelos.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class RetornoPadraoDto {
    HttpStatus tpStatus;
    String dsMensagem;
    List<Object> listaObjetos;
}
