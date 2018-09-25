package model.roadmeshbuilder;

import model.Road;
import model.threadstrategy.Strategy;

public class Director {

    private RoadBuilderInterface roadBuilder;

    public Director(RoadBuilderInterface builder) {
        this.roadBuilder = builder;
    }

    public void build(int[] coordinates, Strategy strategy) {
        roadBuilder.setRoad(new Road());
        roadBuilder.buildGeographicalOrientation(coordinates);
        roadBuilder.buildInitialCellFinalCellCrossRoads(coordinates, strategy);
        roadBuilder.buildIntermediateCells();
        roadBuilder.setNexts();
    }
}
