package br.com.lfm.hollywood.modelos.repositorios;

import br.com.lfm.hollywood.modelos.entidades.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutorRepositorio extends JpaRepository<Produtor, Integer> {
    Produtor findByNmProdutor(String nome);
}
