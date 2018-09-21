package model.fabricaDeMalha;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.RoadMesh;

public abstract class AbstractRoadMeshFactory {
    
    public abstract RoadMesh buildRoadMesh(String fileName) throws FileNotFoundException, IOException;
}
