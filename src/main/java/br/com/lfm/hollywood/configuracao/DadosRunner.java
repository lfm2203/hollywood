package br.com.lfm.hollywood.configuracao;

import br.com.lfm.hollywood.modelos.entidades.*;
import br.com.lfm.hollywood.modelos.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Objects;

@Component
public class DadosRunner implements CommandLineRunner {
    @Autowired
    FilmeRepositorio filmeRepositorio;

    @Autowired
    ProdutorRepositorio produtorRepositorio;

    @Autowired
    EstudioRepositorio estudioRepositorio;

    @Autowired
    FilmeEstudioRepositorio filmeEstudioRepositorio;

    @Autowired
    FilmeProdutorRepositorio filmeProdutorRepositorio;

    private Filme adicionarFilme(String[] registro) {
        Filme filme = new Filme();
        filme.setVlAno(Integer.parseInt(registro[0]));
        filme.setDsTitulo(registro[1]);
        filme.setTpVencedor((registro.length == 5 && Objects.equals(registro[4].trim().toLowerCase(), "yes")) ? "S" : "N");
        return filmeRepositorio.save(filme);
    }

    private void adicionarEstudio(Filme filme, String nome) {
        String nmEstudio = nome.trim();
        Estudio estudio = estudioRepositorio.findByNmEstudio(nmEstudio);
        if (estudio == null) {
            estudio = new Estudio();
            estudio.setNmEstudio(nmEstudio);
            estudio = estudioRepositorio.save(estudio);
        }

        adicionarFilmeEstudio(filme, estudio);
    }

    private void adicionarFilmeEstudio(Filme filme, Estudio estudio) {
        FilmeEstudio filmeEstudio = new FilmeEstudio();
        filmeEstudio.setFilme(filme);
        filmeEstudio.setEstudio(estudio);
        filmeEstudioRepositorio.save(filmeEstudio);
    }

    private void adicionarProdutor(Filme filme, String nome) {
        String nmProdutor = nome.trim().replace("and ", "");
        Produtor produtor = produtorRepositorio.findByNmProdutor(nmProdutor);
        if (produtor == null) {
            produtor = new Produtor();
            produtor.setNmProdutor(nmProdutor);
            produtor = produtorRepositorio.save(produtor);
        }

        adicionarFilmeProdutor(filme, produtor);
    }

    private void adicionarFilmeProdutor(Filme filme, Produtor produtor) {
        FilmeProdutor filmeProdutor = new FilmeProdutor();
        filmeProdutor.setFilme(filme);
        filmeProdutor.setProdutor(produtor);
        filmeProdutorRepositorio.save(filmeProdutor);
    }


    @Override
    public void run(String... args) {
        InputStream inputStream = getClass().getResourceAsStream("/dados/movielist.csv");
        if (inputStream == null) return;

        String linha;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            //Pular uma linha por conta do cabeçalho
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] registro = linha.split(";");
                if (registro.length < 2) continue;

                Filme filme = adicionarFilme(registro);

                if (registro.length < 3) continue;

                String[] estudios = registro[2].split(",");
                for (String e : estudios) {
                    adicionarEstudio(filme, e);
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
