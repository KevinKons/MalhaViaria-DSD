package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Campo;
import model.MalhaViaria;
import model.Via;
import model.fabricaDeMalha.FabricaDeMalha;
import model.Coordenada;
import model.InsereVeiculo;
import model.Veiculo;

public class MalhaViariaController implements ControllerMalhaViaria {

    private List<Observador> observadores = new ArrayList<>();
    private MalhaViaria malhaViaria;
    private final String[] opcoesDeMalha = {"malha1", "malha2", "malha3"};
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

        List<Campo[]> vias = new ArrayList<>();
        for (Via via : malhaViaria.getVias()) {
            Campo[] viaRetorno = new Campo[via.getTamanho()];
            int i = 0;
            for (Campo campo : via.getCampos()) {
                viaRetorno[i] = campo;
                i++;
            }
            vias.add(viaRetorno);
        }
        for (Observador o : observadores) {
            o.notificaCriacaoDeMalha(malhaViaria.getTamanhoX(), malhaViaria.getTamanhoY(), vias);
        }
        
        iniciarSimulacaoComSemaforo();
    }

    @Override
    public String[] getOpcoesDeMalhas() {
        return opcoesDeMalha;
    }

    @Override
    public void selecionaMalha(int indexMalhaSelecionada) {
        this.malhaSelecionada = indexMalhaSelecionada;
    }

    public void iniciarSimulacaoComSemaforo() {
        InsereVeiculo insereVeiculo = new InsereVeiculo(buscaCamposDeInsercao(), 1);
        Thread insereVeiculoThread = new Thread(insereVeiculo);
        insereVeiculoThread.start();
    }

    private List<Campo> buscaCamposDeInsercao() {
        List<Campo> camposDeInsercao = new ArrayList<>();
        for (Via via : malhaViaria.getVias()) {
            if (via.getCampos()[0].getCoordenada().getX() == 0
                    || via.getCampos()[0].getCoordenada().getX() == malhaViaria.getTamanhoX() - 1
                    || via.getCampos()[0].getCoordenada().getY() == 0
                    || via.getCampos()[0].getCoordenada().getY() == malhaViaria.getTamanhoY() - 1) {
                camposDeInsercao.add(via.getCampos()[0]);
            }
        }
        return camposDeInsercao;
    }


}
