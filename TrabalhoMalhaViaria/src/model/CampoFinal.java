package model;

/**
 *
 * @author Avell
 */
public class CampoFinal extends Campo {

    public CampoFinal(Coordenada coordenada) {
        super(coordenada);
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
