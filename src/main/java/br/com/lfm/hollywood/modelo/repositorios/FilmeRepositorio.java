package br.com.lfm.hollywood.modelo.repositorios;

import br.com.lfm.hollywood.modelo.entidades.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepositorio extends JpaRepository<Filme, Integer> {

}
