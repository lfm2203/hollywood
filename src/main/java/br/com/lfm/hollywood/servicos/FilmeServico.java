package br.com.lfm.hollywood.servicos;

import br.com.lfm.hollywood.modelo.entidades.Filme;
import br.com.lfm.hollywood.modelo.repositorios.FilmeRepositorio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeServico {
    @Autowired
    private FilmeRepositorio filmeRepositorio;

    public List<Filme> listarTodos() {
        return filmeRepositorio.findAll();
    }
}
