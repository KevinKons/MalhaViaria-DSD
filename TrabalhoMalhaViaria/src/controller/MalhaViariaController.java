package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Campo;
import model.MalhaViaria;
import model.Via;
import model.fabricaDeMalha.FabricaDeMalha;
import utils.Coordenada;

public class MalhaViariaController implements ControllerMalhaViaria {

    private List<Observador> observadores = new ArrayList<>();
    private MalhaViaria malhaViaria;
    private final String[] opcoesDeMalha = {"malha1", "malha2", "malha3", "malha4", "malha5", "malha6", "malha7"};
    private int malhaSelecionada;

    @Override
    public void addObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public int getQntLinhas() {
        return malhaViaria.getTamanhoY();
    }

    @Override
    public int getQntColunas() {
        return malhaViaria.getTamanhoX();
    }

    @Override
    public void criarMalhaViaria() {
        try {
            malhaViaria = FabricaDeMalha.getInstance().criarMalha(opcoesDeMalha[malhaSelecionada]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        List<Coordenada[]> vias = new ArrayList<>();
        for (Via via : malhaViaria.getVias()) {
            Coordenada[] viaRetorno = new Coordenada[via.getTamanho()];
            int i = 0;
            for (Campo campo : via.getCampos()) {
                viaRetorno[i] = campo.getCoordenada();
                i++;
            }
            vias.add(viaRetorno);
        }
        for (Observador o : observadores) {
            o.notificaCriacaoDeMalha(malhaViaria.getTamanhoX(), malhaViaria.getTamanhoY(), vias);
        }
    }

    @Override
    public String[] getOpcoesDeMalhas() {
        return opcoesDeMalha;
    }

    @Override
    public void selecionaMalha(int indexMalhaSelecionada) {
        this.malhaSelecionada = indexMalhaSelecionada;
    }

}
