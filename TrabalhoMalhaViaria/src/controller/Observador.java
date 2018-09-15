package controller;

import java.util.List;
import utils.Coordenada;

/**
 *
 * @author Avell
 */
public interface Observador {
    
    void notificaCriacaoDeMalha(int tamanhoX, int tamanhoY, List<Coordenada[]> vias);
}
