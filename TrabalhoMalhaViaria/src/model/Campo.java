package model;

public class Campo {

    protected Coordenada coordenada;
    protected boolean ocupado;
    protected Campo proximo;

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

}
