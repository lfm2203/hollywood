package br.com.lfm.hollywood.modelos.repositorios;

import br.com.lfm.hollywood.modelos.entidades.Estudio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudioRepositorio extends JpaRepository<Estudio, Integer> {
    Estudio findByNmEstudio(String nome);
}