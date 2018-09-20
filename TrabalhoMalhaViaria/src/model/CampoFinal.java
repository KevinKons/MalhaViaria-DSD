package model;

import java.util.Random;

/**
 *
 * @author Avell
 */
public class CampoFinal extends Campo {

    public CampoFinal(Coordenada coordenada) {
        super(coordenada);
    }

    @Override
    public boolean isCampoDeSaida() {
        MalhaViaria malhaViaria = MalhaViaria.getInstance();
        if (this.getCoordenada().getX() == 0
                || this.getCoordenada().getX() == malhaViaria.getTamanhoX() - 1
                || this.getCoordenada().getY() == 0
                || this.getCoordenada().getY() == malhaViaria.getTamanhoY() - 1) {
            return true;
        }

        return false;
    }

    @Override
    public void avancaVeiculo(Veiculo veiculo) {
        Random random = new Random();

        Campo campo = null;
        while (campo == null) {
            System.out.println(via.getViasSeguintes().size());
            int posicaoAleatoria = random.nextInt(via.getViasSeguintes().size());
            if (!via.getViasSeguintes().get(posicaoAleatoria).getCampos()[0].isOcupado()) {
                campo = via.getViasSeguintes().get(posicaoAleatoria).getCampos()[1];
            }
        }

        veiculo.setCampo(campo);
        campo.setOcupado(true);
        this.setOcupado(false);
    }

    @Override
    public boolean equals(Object obj) {
        Campo outro = (Campo) obj;
        if (this.coordenada.equals(outro.getCoordenada())) {
            return true;
        }

        return false;
    }

}
