package model.roadmeshfactory;

import model.*;

public class SemaphoreRoadBuilder extends RoadBuilder {

    @Override
    protected AbstractCell createCrossRoad(int x, int y) {
        RoadMesh roadMesh = RoadMesh.getInstance();

        Coordinate crossRoadCoordinate = new Coordinate(x, y);
        AbstractCell crossRoad = roadMesh.searchCrossRoad(crossRoadCoordinate);
        if (crossRoad == null) {
            crossRoad = new SemaphoreCrossRoad(crossRoadCoordinate);
            roadMesh.addCrossRoad(crossRoad);
        }
        return crossRoad;
    }
}
