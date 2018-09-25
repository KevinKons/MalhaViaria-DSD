package model.roadmeshfactory;

import model.AbstractCell;
import model.Coordinate;
import model.MonitorCrossRoad;
import model.RoadMesh;

public class MonitorRoadBuilder extends RoadBuilder {

    @Override
    protected AbstractCell createCrossRoad(int x, int y) {
        RoadMesh roadMesh = RoadMesh.getInstance();

        Coordinate crossRoadCoordinate = new Coordinate(x, y);
        AbstractCell crossRoad = roadMesh.searchCrossRoad(crossRoadCoordinate);
        if (crossRoad == null) {
            crossRoad = new MonitorCrossRoad(crossRoadCoordinate);
            roadMesh.addCrossRoad(crossRoad);
        }
        return crossRoad;
    }
}
