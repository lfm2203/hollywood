package br.com.lfm.hollywood.servicos;


import br.com.lfm.hollywood.modelos.dto.IntervaloPremioDto;
import br.com.lfm.hollywood.modelos.dto.ProdutorVencedorDto;
import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.modelos.entidades.FilmeProdutor;
import br.com.lfm.hollywood.modelos.entidades.Produtor;
import br.com.lfm.hollywood.modelos.repositorios.FilmeProdutorRepositorio;
import br.com.lfm.hollywood.modelos.repositorios.ProdutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class VencedoresServico {
    @Autowired
    ProdutorRepositorio produtorRepositorio;

    @Autowired
    FilmeProdutorRepositorio filmeProdutorRepositorio;

    private List<Filme> obterFilmesVencedoresPorProdutor(Produtor produtor) {
        List<FilmeProdutor> filmes = filmeProdutorRepositorio.obterFilmesVencedoresPorProdutor(produtor.getId());
        return filmes.stream().map(FilmeProdutor::getFilme).toList();
    }

    private List<ProdutorVencedorDto> obterProdutoresVencedores() {
        List<ProdutorVencedorDto> retorno = new ArrayList<>();

        List<Produtor> produtores = produtorRepositorio.findAll();
        for (Produtor produtor : produtores) {
            List<Filme> filmesDoProdutor = obterFilmesVencedoresPorProdutor(produtor);
            if (filmesDoProdutor.isEmpty() || filmesDoProdutor.size() < 2) continue;

            List<Integer> anos = new ArrayList<>(filmesDoProdutor.stream().map(Filme::getVlAno).toList());

            //anos.add(2001);

            for (int x = 0; x < anos.size(); x++) {
                if (x + 1 >= anos.size()) continue;

                ProdutorVencedorDto produtorVencedor = new ProdutorVencedorDto();
                produtorVencedor.setProducer(produtor.getNmProdutor());
                produtorVencedor.setPreviousWin(anos.get(x));
                produtorVencedor.setFollowingWin(anos.get(x + 1));
                produtorVencedor.setInterval(anos.get(x + 1) - anos.get(x));
                retorno.add(produtorVencedor);
            }
        }

        return retorno;
    }

    public IntervaloPremioDto obterProdutoresPorIntervaloPremio() {
        IntervaloPremioDto retorno = new IntervaloPremioDto();
        List<ProdutorVencedorDto> produtores = obterProdutoresVencedores();

        List<ProdutorVencedorDto> maiorIntervalo = produtores.stream().max(Comparator.comparingInt(ProdutorVencedorDto::getInterval)).stream().toList();
        List<ProdutorVencedorDto> menorIntervalo = produtores.stream().min(Comparator.comparingInt(ProdutorVencedorDto::getInterval)).stream().toList();
        retorno.setMax(maiorIntervalo);
        retorno.setMin(menorIntervalo);

        return retorno;
    }
}
