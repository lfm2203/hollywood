package br.com.lfm.hollywood.servicos;

import br.com.lfm.hollywood.modelos.dto.EstudioDto;
import br.com.lfm.hollywood.modelos.entidades.Estudio;
import br.com.lfm.hollywood.modelos.repositorios.EstudioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudioServico {
    @Autowired
    EstudioRepositorio estudioRepositorio;

    public Estudio salvarEstudio(EstudioDto novoEstudio) {
        Estudio estudio = estudioRepositorio.findByNmEstudio(novoEstudio.getNmEstudio());
        if  (estudio == null) {
            estudio = new Estudio();
            estudio.setNmEstudio(novoEstudio.getNmEstudio());
            estudio = estudioRepositorio.save(estudio);
        }
        return estudio;
    }
}
