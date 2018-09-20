
package model;

import java.util.ArrayList;
import java.util.List;

public class MalhaViaria {
    
    private int tamanhoX;
    private int tamanhoY;
    private List<Via> vias = new ArrayList<>(); 
    private int qntVeiculos = 0;
    
    private static MalhaViaria instance;
    public static MalhaViaria getInstance() {
        if(instance == null) 
            instance = new MalhaViaria();
        
        return instance;
    }
    
    private MalhaViaria() {}
    
    public void setTamanhoX(int tamanhoX) {
        this.tamanhoX = tamanhoX;
    }

    public void setTamanhoY(int tamanhoY) {
        this.tamanhoY = tamanhoY;
    }

    public void addVia(Via via) {
        vias.add(via);
    }

    public List<Via> getVias() {
        return vias;
    }

    public int getTamanhoX() {
        return tamanhoX;
    }

    public int getTamanhoY() {
        return tamanhoY;
    }

    public void veiculoEntrouNaMalha() {
        qntVeiculos++;
    }
    
    public void veiculoSaiuDaMalha() {
        qntVeiculos--;
    }

    public int getQntVeiculos() {
        return qntVeiculos;
    }
    
    
    
}
