package br.com.lfm.hollywood.controles;

import br.com.lfm.hollywood.modelos.dto.IntervaloPremioDto;
import br.com.lfm.hollywood.servicos.VencedoresServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vencedores")
@CrossOrigin("*")
public class VencedoresController {
    @Autowired
    VencedoresServico vencedoresServico;

    @GetMapping("/listarPorIntervalo")
    public IntervaloPremioDto obterProdutoresPorIntervaloPremio() {
        return vencedoresServico.obterProdutoresPorIntervaloPremio();
    }
}
