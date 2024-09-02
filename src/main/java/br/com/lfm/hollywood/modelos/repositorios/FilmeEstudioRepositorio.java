package br.com.lfm.hollywood.modelos.repositorios;

import br.com.lfm.hollywood.modelos.entidades.Estudio;
import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.modelos.entidades.FilmeEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeEstudioRepositorio extends JpaRepository<FilmeEstudio, Integer>  {
    FilmeEstudio findByFilmeAndEstudio(Filme filme, Estudio estudio);
}
