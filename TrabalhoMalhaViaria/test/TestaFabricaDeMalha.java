
import java.io.IOException;
import model.Campo;
import model.Coordenada;
import model.fabricaDeMalha.FabricaDeMalha;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Avell
 */
public class TestaFabricaDeMalha {

    private FabricaDeMalha fabricaDeMalha;
    
    public TestaFabricaDeMalha() {
    }
    
    @Before
    public void setUp() {
        fabricaDeMalha = new FabricaDeMalha();
    }
    
    @Test
    public void testaCriarMalha() throws IOException {
        Campo[] esperado = new Campo[7];
        Campo c1 = new Campo(new Coordenada(18, 6));
        Campo c2 = new Campo(new Coordenada(18, 5));
        Campo c3 = new Campo(new Coordenada(18, 4));
        Campo c4 = new Campo(new Coordenada(18, 3));
        Campo c5 = new Campo(new Coordenada(18, 2));
        Campo c6 = new Campo(new Coordenada(18, 1));
        Campo c7 = new Campo(new Coordenada(18, 0));
        
        c1.setProximo(c2);
        c2.setProximo(c3);
        c3.setProximo(c4);
        c4.setProximo(c5);
        c5.setProximo(c6);
        c6.setProximo(c7);
        
        esperado[0] = c1;
        esperado[1] = c2;
        esperado[2] = c3;
        esperado[3] = c4;
        esperado[4] = c5;
        esperado[5] = c6;
        esperado[6] = c7;
        
        Campo[] reais = fabricaDeMalha.criarMalha("malha1.txt").getVias().get(4).getCampos();
        
        for(Campo c : reais) {
            System.out.println(c.getCoordenada().toString());
        }
        
        assertArrayEquals(esperado, reais);
    }
    
    
    
}
