package br.com.lfm.hollywood.api;

import br.com.lfm.hollywood.modelo.dto.FilmeDto;
import br.com.lfm.hollywood.modelo.entidades.Filme;
import br.com.lfm.hollywood.servicos.FilmeServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filme")
@CrossOrigin("*")
public class FilmeController {

    @Autowired
    private FilmeServico filmeServico;

    @GetMapping("/listar")
    public List<Filme> listar() {
        return filmeServico.listarTodos();
    }
}
