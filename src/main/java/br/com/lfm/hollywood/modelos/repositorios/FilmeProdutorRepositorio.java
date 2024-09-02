package br.com.lfm.hollywood.modelos.repositorios;

import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.modelos.entidades.FilmeProdutor;
import br.com.lfm.hollywood.modelos.entidades.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeProdutorRepositorio extends JpaRepository<FilmeProdutor, Integer> {
    FilmeProdutor findByFilmeAndProdutor(Filme filme, Produtor produtor);

    @Query("Select fp from FilmeProdutor fp join fp.filme f where f.tpVencedor = 'S' and fp.produtor.id = :idProdutor order by f.vlAno")
    List<FilmeProdutor> obterFilmesVencedoresPorProdutor(@Param("idProdutor") Integer idProdutor);
}
