package controller;

/**
 *
 * @author Avell
 */
public interface ControllerMalhaViaria extends Observado {

    public int getQntLinhas();
    public int getQntColunas();
    public void criarMalhaViaria();
    public String[] getOpcoesDeMalhas();

    public void selecionaMalha(int selectedIndex);
    
}
