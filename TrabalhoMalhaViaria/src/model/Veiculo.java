package model;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Veiculo implements Runnable {

    private int velocidade;
    private Campo campo;

    public Veiculo(int velocidade) {
        this.velocidade = velocidade;
    }
    
    @Override
    public void run() {
        while(!campo.isCampoDeSaida()) {
            campo.avancaVeiculo(this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }
        
}
