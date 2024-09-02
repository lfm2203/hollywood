package br.com.lfm.hollywood.servicos;

import br.com.lfm.hollywood.modelos.dto.EstudioDto;
import br.com.lfm.hollywood.modelos.dto.FilmeDto;
import br.com.lfm.hollywood.modelos.dto.ProdutorDto;
import br.com.lfm.hollywood.modelos.entidades.Estudio;
import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.modelos.entidades.Produtor;
import br.com.lfm.hollywood.modelos.repositorios.FilmeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmeServico {
    @Autowired
    FilmeRepositorio filmeRepositorio;

    @Autowired
    EstudioServico estudioServico;

    @Autowired
    FilmeEstudioServico filmeEstudioServico;

    @Autowired
    ProdutorServico produtorServico;

    @Autowired
    FilmeProdutorServico filmeProdutorServico;

    public List<Filme> listarTodos() {
        return filmeRepositorio.findAll();
    }

    public Optional<Filme> listarPorId(Integer id) {
        return filmeRepositorio.findById(id);
    }

    public List<String> validarFilme(FilmeDto filme) {
        List<String> retorno = new ArrayList<>();
        if (filme.getVlAno() == null) {
            retorno.add("Campo ano não informado");
        }
        if (filme.getVlAno() != null && filme.getVlAno() <= 0) {
            retorno.add("Campo ano com formato inválido");
        }
        if (filme.getDsTitulo() == null || filme.getDsTitulo().isEmpty()) {
            retorno.add("Campo título não informado");
        }
        if (!Objects.equals(filme.getTpVencedor(), "S") && !Objects.equals(filme.getTpVencedor(), "N")) {
            retorno.add("Campo vencedor com faixa incorreta, aceitável: S ou N");
        }
        if  (filme.getEstudios() == null || filme.getEstudios().isEmpty()) {
            retorno.add("Para adicionar um filme é necessário informar ao menos um estúdio");
        }
        if  (filme.getProdutores() == null || filme.getProdutores().isEmpty()) {
            retorno.add("Para adicionar um filme é necessário informar ao menos um produtor");
        }

        return retorno;
    }

    private void salvarEstudiosFilme(Filme filme, List<EstudioDto> estudios) {
        for (EstudioDto novoEstudio : estudios) {
            Estudio estudio = estudioServico.salvarEstudio(novoEstudio);
            filmeEstudioServico.salvarEstudioFilme(filme, estudio);
        }
    }

    private void salvarProdutoresFilme(Filme filme, List<ProdutorDto> produtores) {
        for (ProdutorDto novoProdutor : produtores) {
            Produtor produtor = produtorServico.salvarProdutor(novoProdutor);
            filmeProdutorServico.salvarProdutorFilme(filme, produtor);
        }
    }

    public Filme salvarFilme(FilmeDto novoFilme) throws Exception {
        Filme filme = new Filme();
        filme.setVlAno(novoFilme.getVlAno());
        filme.setDsTitulo(novoFilme.getDsTitulo());
        filme.setTpVencedor(novoFilme.getTpVencedor() != null && !novoFilme.getTpVencedor().isEmpty() ?
                novoFilme.getTpVencedor() : "N");
        filme = filmeRepositorio.save(filme);
        salvarEstudiosFilme(filme, novoFilme.getEstudios());
        salvarProdutoresFilme(filme, novoFilme.getProdutores());

        return filme;
    }

    public Filme atualizarFilme(Integer id, FilmeDto filmeAtualizado) {
        Optional<Filme> filmeObtido = filmeRepositorio.findById(id);
        if (filmeObtido.isEmpty()) return null;

        Filme filme = filmeObtido.get();
        filme.setVlAno(filmeAtualizado.getVlAno());
        if (filmeAtualizado.getDsTitulo() != null && !filmeAtualizado.getDsTitulo().isEmpty()) {
            filme.setDsTitulo(filmeAtualizado.getDsTitulo());
        }
        if (filmeAtualizado.getTpVencedor() != null && !filmeAtualizado.getTpVencedor().isEmpty()) {
            filme.setTpVencedor(filmeAtualizado.getTpVencedor());
        }
        return filmeRepositorio.save(filme);
    }

    public boolean excluirFilme(Integer id) {
        if (!filmeRepositorio.existsById(id)) {
            return false;
        }
        filmeRepositorio.deleteById(id);
        return true;
    }
}
