package br.com.lfm.hollywood.configuracao;

import br.com.lfm.hollywood.modelos.entidades.Filme;
import br.com.lfm.hollywood.modelos.entidades.FilmeEstudio;
import br.com.lfm.hollywood.modelos.entidades.FilmeProdutor;
import br.com.lfm.hollywood.modelos.repositorios.FilmeEstudioRepositorio;
import br.com.lfm.hollywood.modelos.repositorios.FilmeProdutorRepositorio;
import br.com.lfm.hollywood.modelos.repositorios.FilmeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

@Component
public class DadosRunner implements CommandLineRunner {
    @Autowired
    FilmeRepositorio filmeRepositorio;

    @Autowired
    FilmeEstudioRepositorio filmeEstudioRepositorio;

    @Autowired
    FilmeProdutorRepositorio filmeProdutorRepositorio;

    private void adicionarProdutor(Filme filme, String nome) {
        FilmeProdutor produtor = new FilmeProdutor();
        produtor.setFilme(filme);
        produtor.setNmProdutor(nome.trim().replace("and ", ""));
        filmeProdutorRepositorio.save(produtor);
    }

    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/dados/movielist.csv");
        if (inputStream == null) return;

        String linha = "";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            //Pular uma linha por conta do cabeçalho
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] registro = linha.split(";");

                Filme filme = new Filme();
                filme.setVlAno(Integer.parseInt(registro[0]));
                filme.setDsTitulo(registro[1]);
                filme.setTpVencedor((registro.length == 5 && Objects.equals(registro[4].trim().toLowerCase(), "yes")) ? "S" : "N");
                filmeRepositorio.save(filme);

                if (registro.length < 3) continue;

                String[] estudios = registro[2].split(",");
                for (String e : estudios) {
                    FilmeEstudio estudio = new FilmeEstudio();
                    estudio.setFilme(filme);
                    estudio.setNmEstudio(e.trim());
                    filmeEstudioRepositorio.save(estudio);
                }

                if (registro.length < 4) continue;

                String[] produtores = registro[3].split(",");
                for (String p : produtores) {
                    if (p.trim().toLowerCase().contains(" and ")) {
                        String[] subprodutores = p.split(" and ");
                        for (String s : subprodutores) {
                            adicionarProdutor(filme, s);
                        }
                        continue;
                    }
                    adicionarProdutor(filme, p);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao executar a leitura do arquivo: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Ocorreu um erro ao executar a leitura do arquivo: " + e.getMessage());
                }
            }
        }
    }
}
