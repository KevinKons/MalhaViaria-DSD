
package model;

import java.util.ArrayList;
import java.util.List;

public class MalhaViaria {
    
    private int tamanhoX;
    private int tamanhoY;
    private List<Via> vias = new ArrayList<>(); 
    
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
    
    
}
