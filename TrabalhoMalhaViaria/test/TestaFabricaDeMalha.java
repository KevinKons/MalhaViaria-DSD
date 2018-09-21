
import java.io.IOException;
import model.semaphoreElements.Cell;
import model.Coordinate;
import model.fabricaDeMalha.SemaphoreRoadMeshFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Avell
 */
public class TestaFabricaDeMalha {

    private SemaphoreRoadMeshFactory fabricaDeMalha;
    
    public TestaFabricaDeMalha() {
    }
    
    @Before
    public void setUp() {
        fabricaDeMalha = SemaphoreRoadMeshFactory.getInstance();
    }
    
    @Test
    public void testaCriarMalha() throws IOException {
//        Cell[] esperado = new Cell[7];
//        Cell c1 = new Cell(new Coordinate(18, 6));
//        Cell c2 = new Cell(new Coordinate(18, 5));
//        Cell c3 = new Cell(new Coordinate(18, 4));
//        Cell c4 = new Cell(new Coordinate(18, 3));
//        Cell c5 = new Cell(new Coordinate(18, 2));
//        Cell c6 = new Cell(new Coordinate(18, 1));
//        Cell c7 = new Cell(new Coordinate(18, 0));
//        
//        c1.setProximo(c2);
//        c2.setProximo(c3);
//        c3.setProximo(c4);
//        c4.setProximo(c5);
//        c5.setProximo(c6);
//        c6.setProximo(c7);
//        
//        esperado[0] = c1;
//        esperado[1] = c2;
//        esperado[2] = c3;
//        esperado[3] = c4;
//        esperado[4] = c5;
//        esperado[5] = c6;
//        esperado[6] = c7;
//        
//        Cell[] reais = fabricaDeMalha.buildRoadMesh("malha1").getRoads().get(4).getCells();
//        
//        for(Cell c : reais) {
//            System.out.println(c.getCoordinate().toString());
//        }
//        
//        assertArrayEquals(esperado, reais);
    }
    
    
    
}
