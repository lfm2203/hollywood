package br.com.lfm.hollywood.servicos;


import br.com.lfm.hollywood.modelos.dto.IntervaloPremioDto;
import br.com.lfm.hollywood.modelos.dto.ProdutorVencedorDto;
import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.modelos.entidades.FilmeProdutor;
import br.com.lfm.hollywood.modelos.entidades.Produtor;
import br.com.lfm.hollywood.modelos.repositorios.FilmeProdutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VencedoresServico {
    @Autowired
    FilmeProdutorRepositorio filmeProdutorRepositorio;

    private List<ProdutorVencedorDto> obterProdutoresVencedores() {
        List<ProdutorVencedorDto> retorno = new ArrayList<>();

        List<FilmeProdutor> filmesProdutores = filmeProdutorRepositorio.findByFilmeTpVencedorOrderByFilmeVlAno("S");
        Map<Produtor, List<Filme>> filmesPorProdutor = filmesProdutores.stream().collect(Collectors.groupingBy(
                FilmeProdutor::getProdutor,
                Collectors.mapping(FilmeProdutor::getFilme, Collectors.toList())
        ));

        for (Map.Entry<Produtor, List<Filme>> produtor : filmesPorProdutor.entrySet()) {
            if (produtor.getValue().isEmpty() || produtor.getValue().size() < 2) continue;

            List<Integer> anos = new ArrayList<>(produtor.getValue().stream().map(Filme::getVlAno).toList());

            for (int x = 0; x < anos.size(); x++) {
                if (x + 1 >= anos.size()) continue;

                ProdutorVencedorDto produtorVencedor = new ProdutorVencedorDto();
                produtorVencedor.setProducer(produtor.getKey().getNmProdutor());
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

        Optional<ProdutorVencedorDto> maiorIntervalo = produtores.stream().max(Comparator.comparingInt(ProdutorVencedorDto::getInterval));
        if (maiorIntervalo.isPresent()) {
            List<ProdutorVencedorDto> produtoresMaiorIntervalor = produtores.stream().filter(f -> Objects.equals(f.getInterval(), maiorIntervalo.get().getInterval())).toList();
            retorno.setMax(produtoresMaiorIntervalor);
        }

        Optional<ProdutorVencedorDto> menorIntervalo = produtores.stream().min(Comparator.comparingInt(ProdutorVencedorDto::getInterval));
        if (menorIntervalo.isPresent()) {
            List<ProdutorVencedorDto> produtoresMenorIntervalor = produtores.stream().filter(f -> Objects.equals(f.getInterval(), menorIntervalo.get().getInterval())).toList();
            retorno.setMin(produtoresMenorIntervalor);
        }

        return retorno;
    }
}
