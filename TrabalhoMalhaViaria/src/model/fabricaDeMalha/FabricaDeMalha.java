package model.fabricaDeMalha;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.Campo;
import model.Coordenada;
import model.MalhaViaria;
import model.OrientacaoGeografica;
import model.Via;

public class FabricaDeMalha extends FabricaAbstrataDeMalha {

    @Override
    public MalhaViaria criarMalha(String nomeArquivo) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));

        MalhaViaria malhaViaria = new MalhaViaria();
        int i = 0;
        String linha;
        while ((linha = reader.readLine()) != null) {
            if (i == 0) {
                malhaViaria.setTamanhoX(Integer.parseInt(linha));
                i++;
            } else if (i == 1) {
                malhaViaria.setTamanhoY(Integer.parseInt(linha));
                i++;
            } else {
                Via via = new Via();
                defineTamanhoDaVia(via, linha.split(";"));
                defineCamposInicioEFim(via, linha.split(";"));
                defineOrientacaoGeografica(via);
                defineCamposIntermediarios(via);
                defineProximos(via);
                malhaViaria.addVia(via);
            }
        }
        return malhaViaria;
    }

    private void defineTamanhoDaVia(Via via, String[] coordenadasInicioEFim) {
        if (Integer.parseInt(coordenadasInicioEFim[0]) == Integer.parseInt(coordenadasInicioEFim[2])) {
            via.setTamanho(Math.abs(
                    Integer.parseInt(coordenadasInicioEFim[1]) - Integer.parseInt(coordenadasInicioEFim[3])) + 1);
        } else if (Integer.parseInt(coordenadasInicioEFim[1]) == Integer.parseInt(coordenadasInicioEFim[3])) {
            via.setTamanho(Math.abs(
                    Integer.parseInt(coordenadasInicioEFim[0]) - Integer.parseInt(coordenadasInicioEFim[2])) + 1);
        }
    }

    private void defineCamposInicioEFim(Via via, String[] coordenadas) {
        Campo campoInicio = new Campo(new Coordenada(Integer.parseInt(coordenadas[0]),
                Integer.parseInt(coordenadas[1])));
        via.getCampos()[0] = campoInicio;
        

        via.getCampos()[via.getTamanho() - 1] = new Campo(new Coordenada(Integer.parseInt(coordenadas[2]),
                Integer.parseInt(coordenadas[3])));
    }

    private void defineOrientacaoGeografica(Via via) {
        Coordenada inicio = via.getCampos()[0].getCoordenada();
        Coordenada fim = via.getCampos()[via.getTamanho() - 1].getCoordenada();

        if (inicio.getX() == fim.getX()) {
            if (inicio.getY() > fim.getY()) {
                via.setOrientacaoGeografica(OrientacaoGeografica.NORTE);
            } else if (inicio.getY() < fim.getY()) {
                via.setOrientacaoGeografica(OrientacaoGeografica.SUL);
            }
        } else if (inicio.getY() == fim.getY()) {
            if (inicio.getX() > fim.getX()) {
                via.setOrientacaoGeografica(OrientacaoGeografica.OESTE);
            } else if (inicio.getX() < fim.getX()) {
                via.setOrientacaoGeografica(OrientacaoGeografica.LESTE);
            }
        }
    }

    private void defineCamposIntermediarios(Via via) {
        if (via.getOrientacaoGeografica() == OrientacaoGeografica.NORTE) {
            int x = via.getCampos()[0].getCoordenada().getX();
            int yDoInicio = via.getCampos()[0].getCoordenada().getY();
            for (int i = 1; i < via.getTamanho() - 1; i++) { //1 pois o inicio já está definido e 2 pq o fim já está definido
                Campo campo = new Campo(new Coordenada(x, yDoInicio - i));
                via.getCampos()[i] = campo;
            }
        } else if (via.getOrientacaoGeografica() == OrientacaoGeografica.SUL) {
            int x = via.getCampos()[0].getCoordenada().getX();
            int yDoInicio = via.getCampos()[0].getCoordenada().getY();
            for (int i = 1; i < via.getTamanho() - 1; i++) { //1 pois o inicio já está definido e 2 pq o fim já está definido
                Campo campo = new Campo(new Coordenada(x, yDoInicio + i));
                via.getCampos()[i] = campo;
            }
        } else if (via.getOrientacaoGeografica() == OrientacaoGeografica.OESTE) {
            int xDoInicio = via.getCampos()[0].getCoordenada().getX();
            int y = via.getCampos()[0].getCoordenada().getY();
            for (int i = 1; i < via.getTamanho() - 1; i++) { //1 pois o inicio já está definido e 2 pq o fim já está definido
                Campo campo = new Campo(new Coordenada(xDoInicio - i, y));
                via.getCampos()[i] = campo;
            }
        } else if (via.getOrientacaoGeografica() == OrientacaoGeografica.LESTE) {
            int xDoInicio = via.getCampos()[0].getCoordenada().getX();
            int y = via.getCampos()[0].getCoordenada().getY();
            for (int i = 1; i < via.getTamanho() - 1; i++) { //1 pois o inicio já está definido e 2 pq o fim já está definido
                Campo campo = new Campo(new Coordenada(xDoInicio + i, y));
                via.getCampos()[i] = campo;
            }
        }
    }

    private void defineProximos(Via via) {
        for(int i = 0; i < via.getTamanho() - 1; i++) {
            via.getCampos()[i].setProximo(via.getCampos()[i + 1]);
        }
    }
}
