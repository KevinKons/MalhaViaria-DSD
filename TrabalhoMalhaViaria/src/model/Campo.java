package model;

import controller.Observado;
import controller.Observador;
import java.util.ArrayList;
import java.util.List;

public class Campo implements Observado {

    protected Coordenada coordenada;
    protected boolean ocupado;
    protected Campo proximo;
    protected List<Observador> observadores = new ArrayList<>();
    protected Via via;

    public Campo(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        if(ocupado) {
            for(Observador o : observadores) {
                o.notificaCampoOcupado(this.coordenada.getX(), this.coordenada.getY());
            }
        } else {
            for(Observador o : observadores) {
                o.notificaCampoLivre(this.coordenada.getX(), this.coordenada.getY());
            }
        }
        this.ocupado = ocupado;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public void setProximo(Campo campo) {
        this.proximo = campo;
    }

    public Campo getProximo() {
        return proximo;
    }
    
    public boolean isCampoDeSaida() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        Campo outro = (Campo) obj;
        if (this.proximo != null && outro.getProximo() != null) {
            if (this.coordenada.equals(outro.getCoordenada())
                    && this.proximo.getCoordenada().equals(outro.getProximo().getCoordenada())) {
                return true;
            }
        } else {
            if (this.coordenada.equals(outro.getCoordenada())) {
                return true;
            }
        }

        return false;
    }

    public void avancaVeiculo(Veiculo veiculo) {
        if(!this.proximo.isOcupado()) {
            veiculo.setCampo(this.proximo);
            this.proximo.setOcupado(true);
            this.setOcupado(false);
        }
    }

    @Override
    public void addObservador(Observador o) {
        observadores.add(o);
    }

    public Via getVia() {
        return via;
    }

    public void setVia(Via via) {
        this.via = via;
    }
    
}   
