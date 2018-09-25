package model.roadmeshbuilder;

import model.AbstractCell;
import model.Coordinate;
import model.GeographicalOrientation;
import model.Road;
import model.roadmeshbuilder.products.Cell;
import model.threadstrategy.Strategy;

public abstract class RoadBuilderInterface {

    Road road;

    void setRoad(Road road) {
        this.road = road;
    }

    Road getRoad() {
        return road;
    }

    void buildGeographicalOrientation(int[] coordinates) {
        Coordinate initialCell = new Coordinate(coordinates[0], coordinates[1]);
        Coordinate finalCell = new Coordinate(coordinates[2], coordinates[3]);

        if (initialCell.getX() == finalCell.getX()) {
            if (initialCell.getY() > finalCell.getY()) {
                road.setGeographicalOrientation(GeographicalOrientation.NORTH);
            } else if (initialCell.getY() < finalCell.getY()) {
                road.setGeographicalOrientation(GeographicalOrientation.SOUTH);
            }
        } else if (initialCell.getY() == finalCell.getY()) {
            if (initialCell.getX() > finalCell.getX()) {
                road.setGeographicalOrientation(GeographicalOrientation.WEST);
            } else if (initialCell.getX() < finalCell.getX()) {
                road.setGeographicalOrientation(GeographicalOrientation.EAST);
            }
        }
    }

    public abstract void buildInitialCellFinalCellCrossRoads(int[] coordinates, Strategy strategy);

    public abstract void buildRoadSize(AbstractCell initialCell, AbstractCell finalCell);

    void buildIntermediateCells() {
        if (road.getGeographicalOrientation() == GeographicalOrientation.NORTH) {
            int x = road.getCells()[0].getCoordinate().getX();
            int initialY = road.getCells()[0].getCoordinate().getY();
            for (int i = 1; i < road.getSize() - 1; i++) {
                AbstractCell cell = new Cell(new Coordinate(x, initialY - i));
                road.getCells()[i] = cell;
            }
        } else if (road.getGeographicalOrientation() == GeographicalOrientation.SOUTH) {
            int x = road.getCells()[0].getCoordinate().getX();
            int initialY = road.getCells()[0].getCoordinate().getY();
            for (int i = 1; i < road.getSize() - 1; i++) {
                AbstractCell cell = new Cell(new Coordinate(x, initialY + i));
                road.getCells()[i] = cell;
            }
        } else if (road.getGeographicalOrientation() == GeographicalOrientation.WEST) {
            int initialX = road.getCells()[0].getCoordinate().getX();
            int y = road.getCells()[0].getCoordinate().getY();
            for (int i = 1; i < road.getSize() - 1; i++) {
                AbstractCell cell = new Cell(new Coordinate(initialX - i, y));
                road.getCells()[i] = cell;
            }
        } else if (road.getGeographicalOrientation() == GeographicalOrientation.EAST) {
            int initialX = road.getCells()[0].getCoordinate().getX();
            int y = road.getCells()[0].getCoordinate().getY();
            for (int i = 1; i < road.getSize() - 1; i++) {
                AbstractCell cell = new Cell(new Coordinate(initialX + i, y));
                road.getCells()[i] = cell;
            }
        }
    }

    void setNexts() {
        for (int i = 0; i < road.getSize() - 1; i++) {
            road.getCells()[i].addNext(road.getCells()[i + 1]);
        }
    }

}
