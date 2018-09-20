package controller;

import java.util.List;
import model.Campo;

/**
 *
 * @author Avell
 */
public interface Observador {
    
    void notificaCriacaoDeMalha(int tamanhoX, int tamanhoY, List<Campo[]> vias);

    public void notificaCampoOcupado(int x, int y);

    public void notificaCampoLivre(int x, int y);
}
