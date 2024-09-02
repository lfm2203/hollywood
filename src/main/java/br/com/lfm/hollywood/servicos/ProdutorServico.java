package br.com.lfm.hollywood.servicos;

import br.com.lfm.hollywood.modelos.dto.ProdutorDto;
import br.com.lfm.hollywood.modelos.entidades.Produtor;
import br.com.lfm.hollywood.modelos.repositorios.ProdutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutorServico {
    @Autowired
    ProdutorRepositorio produtorRepositorio;

    public Produtor salvarProdutor(ProdutorDto novoProdutor) {
        Produtor produtor = produtorRepositorio.findByNmProdutor(novoProdutor.getNmProdutor());
        if (produtor == null) {
            produtor = new Produtor();
            produtor.setNmProdutor(novoProdutor.getNmProdutor());
            produtor = produtorRepositorio.save(produtor);
        }
        return produtor;
    }
}
