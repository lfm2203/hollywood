package br.com.lfm.hollywood.servicos;

import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.modelos.entidades.FilmeProdutor;
import br.com.lfm.hollywood.modelos.entidades.Produtor;
import br.com.lfm.hollywood.modelos.repositorios.FilmeProdutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeProdutorServico {
    @Autowired
    FilmeProdutorRepositorio filmeProdutorRepositorio;

    public void salvarProdutorFilme(Filme filme, Produtor produtor) {
        FilmeProdutor filmeProdutor = filmeProdutorRepositorio.findByFilmeAndProdutor(filme, produtor);
        if  (filmeProdutor == null) {
            filmeProdutor = new FilmeProdutor();
            filmeProdutor.setFilme(filme);
            filmeProdutor.setProdutor(produtor);
            filmeProdutorRepositorio.save(filmeProdutor);
        }
    }
}
