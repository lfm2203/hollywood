package br.com.lfm.hollywood.servicos;

import br.com.lfm.hollywood.modelos.entidades.Estudio;
import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.modelos.entidades.FilmeEstudio;
import br.com.lfm.hollywood.modelos.repositorios.FilmeEstudioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeEstudioServico {
    @Autowired
    FilmeEstudioRepositorio filmeEstudioRepositorio;

    public void salvarEstudioFilme(Filme filme, Estudio estudio) {
        FilmeEstudio filmeEstudio = filmeEstudioRepositorio.findByFilmeAndEstudio(filme, estudio);
        if (filmeEstudio == null) {
            filmeEstudio = new FilmeEstudio();
            filmeEstudio.setFilme(filme);
            filmeEstudio.setEstudio(estudio);
            filmeEstudioRepositorio.save(filmeEstudio);
        }
    }
}
