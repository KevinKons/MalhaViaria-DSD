package model;

import java.util.ArrayList;
import java.util.List;

public class Via {
 
    private Campo[] campos;
    private int tamanho;
    private OrientacaoGeografica orientacaoGeografica;
    private List<Via> viasSeguintes = new ArrayList<>();

    public OrientacaoGeografica getOrientacaoGeografica() {
        return orientacaoGeografica;
    }

    public void setOrientacaoGeografica(OrientacaoGeografica orientacaoGeofrafica) {
        this.orientacaoGeografica = orientacaoGeofrafica;
    }
    
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
        this.campos = new Campo[tamanho];
    }

    public Campo[] getCampos() {
        return campos;
    }

    public List<Via> getViasSeguintes() {
        return viasSeguintes;
    }

    public void addViaSeguintes(Via viasSeguinte) {
        this.viasSeguintes.add(viasSeguinte);
    }
}
