package model.roadmeshfactory;

import model.AbstractCell;
import model.Coordinate;
import model.GeographicalOrientation;
import model.RoadMesh;
import model.Cell;
import model.CrossRoad;
import model.FinalCell;

public abstract class RoadBuilder extends RoadBuilderInterface {

    @Override
    public void buildInitialCellFinalCellCrossRoads(int[] coordinates) {
        RoadMesh roadMesh = RoadMesh.getInstance();

        AbstractCell initialCell;
        if (coordinates[0] == 0 || coordinates[0] == roadMesh.getXSize() - 1
                || coordinates[1] == 0 || coordinates[1] == roadMesh.getYSize() - 1) {

            initialCell = new Cell(new Coordinate(coordinates[0], coordinates[1]));
        } else {
            if (road.getGeographicalOrientation() == GeographicalOrientation.NORTH) {
                initialCell = new Cell(new Coordinate(coordinates[0], coordinates[1] - 1));
            } else if (road.getGeographicalOrientation() == GeographicalOrientation.SOUTH) {
                initialCell = new Cell(new Coordinate(coordinates[0], coordinates[1] + 1));
            } else if (road.getGeographicalOrientation() == GeographicalOrientation.EAST) {
                initialCell = new Cell(new Coordinate(coordinates[0] + 1, coordinates[1]));
            } else {
                initialCell = new Cell(new Coordinate(coordinates[0] - 1, coordinates[1]));
            }

            /*
            Verifica se já existe cruzamento em(coordinates[0], coordinates[1]),
            se não existe, cria o cruzamento e adiciona crossRoad na malha viaria
            Logo após set initialCell como proximo desse cruzamento
             */

            AbstractCell crossRoad = createCrossRoad(coordinates[0], coordinates[1]);
            if (crossRoad != null)
                crossRoad.addNext(initialCell);

        }

        AbstractCell finalCell;
        if (coordinates[2] == 0 || coordinates[2] == roadMesh.getXSize() - 1
                || coordinates[3] == 0 || coordinates[3] == roadMesh.getYSize() - 1) {

            finalCell = new FinalCell(new Coordinate(coordinates[2], coordinates[3]));
        } else {
            if (road.getGeographicalOrientation() == GeographicalOrientation.NORTH) {
                finalCell = new FinalCell(new Coordinate(coordinates[2], coordinates[3] + 1));
            } else if (road.getGeographicalOrientation() == GeographicalOrientation.SOUTH) {
                finalCell = new FinalCell(new Coordinate(coordinates[2], coordinates[3] - 1));
            } else if (road.getGeographicalOrientation() == GeographicalOrientation.EAST) {
                finalCell = new FinalCell(new Coordinate(coordinates[2] - 1, coordinates[3]));
            } else {
                finalCell = new FinalCell(new Coordinate(coordinates[2] + 1, coordinates[3]));
            }

            /*
            Verifica se já existe cruzamento em(coordinates[2], coordinates[3]),
            se não existe, cria o cruzamento e adiciona crossRoad na malha viaria
            Logo após crossRoad como proimo de finalCell, indicando que a ultima
            célula da via leva ao cruzamento
             */

            AbstractCell crossRoad = createCrossRoad(coordinates[2], coordinates[3]);
            if (crossRoad != null)
                finalCell.addNext(crossRoad);

        }
        buildRoadSize(initialCell, finalCell);

        road.getCells()[0] = initialCell;
        road.getCells()[road.getSize() - 1] = finalCell;
    }

    protected abstract AbstractCell createCrossRoad(int x, int y);

    @Override
    public void buildRoadSize(AbstractCell initialCell, AbstractCell finalCell) {
        int xInitialCell = initialCell.getCoordinate().getX();
        int yInitialCell = initialCell.getCoordinate().getY();
        int xFinalCell = finalCell.getCoordinate().getX();
        int yFinalCell = finalCell.getCoordinate().getY();

        if (xInitialCell == xFinalCell) {
            road.setSize(Math.abs(yInitialCell - yFinalCell) + 1);
        } else if (yInitialCell == yFinalCell) {
            road.setSize(Math.abs(xInitialCell - xFinalCell) + 1);
        }
    }


}
