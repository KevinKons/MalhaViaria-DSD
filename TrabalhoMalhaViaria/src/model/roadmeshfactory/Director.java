package model.roadmeshfactory;

import model.Road;

public class Director {

    private RoadBuilderInterface roadBuilder;

    public Director(RoadBuilderInterface builder) {
        this.roadBuilder = builder;
    }

    public void build(int[] coordinates) {
        roadBuilder.setRoad(new Road());
        roadBuilder.buildGeographicalOrientation(coordinates);
        roadBuilder.buildInitialCellFinalCellCrossRoads(coordinates);
        roadBuilder.buildIntermediateCells();
        roadBuilder.setNexts();
    }
}
