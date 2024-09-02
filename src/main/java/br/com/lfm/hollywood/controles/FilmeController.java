package br.com.lfm.hollywood.controles;

import br.com.lfm.hollywood.modelos.dto.FilmeDto;
import br.com.lfm.hollywood.modelos.dto.RetornoPadraoDto;
import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.servicos.FilmeServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filme")
@CrossOrigin("*")
public class FilmeController {

    @Autowired
    private FilmeServico filmeServico;

    @GetMapping("/listar")
    public RetornoPadraoDto listar() {
        RetornoPadraoDto retorno = new RetornoPadraoDto();
        try {
            List<Filme> filmes = filmeServico.listarTodos();
            retorno.setTpStatus(HttpStatus.OK);
            retorno.setListaObjetos(Collections.singletonList(filmes));
        } catch (Exception e) {
            retorno.setTpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            retorno.setDsMensagem("Ocorreu um erro ao listar os filmes: " + e.getMessage());
        }
        return retorno;
    }

    @GetMapping("/listar/{id}")
    public RetornoPadraoDto listarPorId(@PathVariable Integer id) {
        RetornoPadraoDto retorno = new RetornoPadraoDto();
        try {
            Optional<Filme> filme = filmeServico.listarPorId(id);
            if (filme.isEmpty()) {
                retorno.setTpStatus(HttpStatus.NO_CONTENT);
                retorno.setDsMensagem("Filme não encontrado");
                return retorno;
            }
            retorno.setTpStatus(HttpStatus.OK);
            retorno.setListaObjetos(Collections.singletonList(filme));
        } catch (Exception e) {
            retorno.setTpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            retorno.setDsMensagem("Ocorreu um erro ao listar o filme: " + e.getMessage());
        }
        return retorno;
    }

    @PostMapping
    public RetornoPadraoDto salvarFilme(@RequestBody FilmeDto novoFilme) {
        RetornoPadraoDto retorno = new RetornoPadraoDto();
        try {
            List<String> validacao = filmeServico.validarFilme(novoFilme);
            if (!validacao.isEmpty()) {
                retorno.setTpStatus(HttpStatus.BAD_REQUEST);
                retorno.setDsMensagem("Requisição inválida");
                retorno.setListaObjetos(Collections.singletonList(validacao));
                return retorno;
            }
            Filme filme = filmeServico.salvarFilme(novoFilme);
            retorno.setTpStatus(HttpStatus.CREATED);
            retorno.setListaObjetos(Collections.singletonList(filme));
        } catch (Exception e) {
            retorno.setTpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            retorno.setDsMensagem("Ocorreu um erro ao inserir o filme: " + e.getMessage());
        }
        return retorno;
    }

    @PutMapping("/{id}")
    public RetornoPadraoDto atualizarFilme(@PathVariable Integer id, @RequestBody FilmeDto filmeAtualizar) {
        RetornoPadraoDto retorno = new RetornoPadraoDto();
        try {
            Filme filme = filmeServico.atualizarFilme(id, filmeAtualizar);
            if (filme == null) {
                retorno.setTpStatus(HttpStatus.NO_CONTENT);
                retorno.setDsMensagem("Filme não encontrado");
                return retorno;
            }
            retorno.setTpStatus(HttpStatus.OK);
            retorno.setListaObjetos(Collections.singletonList(filme));
        } catch (Exception e) {
            retorno.setTpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            retorno.setDsMensagem("Ocorreu um erro ao atualizar o filme: " + e.getMessage());
        }
        return retorno;
    }

    @DeleteMapping("/{id}")
    public RetornoPadraoDto excluirFilme(@PathVariable Integer id) {
        RetornoPadraoDto retorno = new RetornoPadraoDto();
        try {
            boolean excluido = filmeServico.excluirFilme(id);
            if (!excluido) {
                retorno.setTpStatus(HttpStatus.NOT_FOUND);
                retorno.setDsMensagem("Filme não encontrado, não foi possível excluir");
                return retorno;
            }
            retorno.setDsMensagem("Filme excluído com sucesso");
            retorno.setTpStatus(HttpStatus.OK);
        } catch (Exception e) {
            retorno.setTpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            retorno.setDsMensagem("Ocorreu um erro ao excluir o filme: " + e.getMessage());
        }
        return retorno;
    }
}
