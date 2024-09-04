package br.com.lfm.hollywood.modelos.repositorios;

import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.modelos.entidades.FilmeProdutor;
import br.com.lfm.hollywood.modelos.entidades.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeProdutorRepositorio extends JpaRepository<FilmeProdutor, Integer> {
    FilmeProdutor findByFilmeAndProdutor(Filme filme, Produtor produtor);
    List<FilmeProdutor> findByFilmeTpVencedorOrderByFilmeVlAno(String tpVencedor);
}
