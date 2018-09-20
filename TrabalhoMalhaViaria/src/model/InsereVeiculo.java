package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Avell
 */
public class InsereVeiculo implements Runnable {

    private List<Campo> camposDeInsercao = new ArrayList<>();
    private int qntMaximaDeVeiculos;

    public InsereVeiculo(List<Campo> camposDeInsercao, int qntMaximaDeVeiculo) {
        this.camposDeInsercao = camposDeInsercao;
        this.qntMaximaDeVeiculos = qntMaximaDeVeiculo;
    }

    @Override
    public void run() {
        MalhaViaria malhaViaria = MalhaViaria.getInstance();
        Random random = new Random();
        while (malhaViaria.getQntVeiculos() <= qntMaximaDeVeiculos) {
            Veiculo veiculo = new Veiculo(500);
            malhaViaria.veiculoEntrouNaMalha();

            Campo campo = null;
            while (campo == null) {
                int posicaoAleatoria = random.nextInt(camposDeInsercao.size());
                if (!camposDeInsercao.get(posicaoAleatoria).isOcupado()) {
                    campo = camposDeInsercao.get(posicaoAleatoria);
                }
            }
            veiculo.setCampo(campo);
            campo.setOcupado(true);

            Thread veiculoThread = new Thread(veiculo);
            veiculoThread.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private List<Veiculo> criaVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            veiculos.add(new Veiculo(10));
        }
        return veiculos;
    }

}
