package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.MalhaViaria;
import model.fabricaDeMalha.FabricaDeMalha;

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
        
        for(Observador o : observadores) {
            o.notificaCriacaoDeMalha(malhaViaria.getTamanhoX(), malhaViaria.getTamanhoY());
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
