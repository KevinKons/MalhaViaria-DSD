package model.roadmeshbuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.*;
import utils.UtilMethods;

public class RoadMeshFactory {

    private static RoadMeshFactory ourInstance = new RoadMeshFactory();
    private RoadBuilder roadBuilder = new RoadBuilder();
    private Director director = new Director(roadBuilder);

    public static RoadMeshFactory getInstance() {
        return ourInstance;
    }

    private RoadMeshFactory() {
    }

    public RoadMesh buildRoadMesh(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt"));

        RoadMesh roadMesh = RoadMesh.getInstance();
        int i = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            if (i == 0) {
                String newLine = line.replace("\t\t\t", "");
                roadMesh.setXSize(Integer.parseInt(newLine));
                i++;
            } else if (i == 1) {
                String newLine = line.replace("\t\t\t", "");
                roadMesh.setYSize(Integer.parseInt(newLine));
                i++;
            } else {
                int[] coordinates = UtilMethods.stringArrayToIntArray(line.split("\t"));
                director.build(coordinates);
                Road road = roadBuilder.getRoad();
                /*setGeographicalOrientation(road, line.split("\t"));
                defineCamposInicioEFimECruzamento(road, line.split("\t"));
                defineCamposIntermediarios(road);
                defineProximos(road);*/
                roadMesh.addRoad(road);
            }
        }
        return roadMesh;
    }
//
//    private void setGeographicalOrientation(Road road, String[] stringCoordinates) {
//        int[] coordinates = UtilMethods.stringArrayToIntArray(stringCoordinates);
//        Coordinate initialCell = new Coordinate(coordinates[0], coordinates[1]);
//        Coordinate finalCell = new Coordinate(coordinates[2], coordinates[3]);
//
//        if (initialCell.getX() == finalCell.getX()) {
//            if (initialCell.getY() > finalCell.getY()) {
//                road.setGeographicalOrientation(GeographicalOrientation.NORTH);
//            } else if (initialCell.getY() < finalCell.getY()) {
//                road.setGeographicalOrientation(GeographicalOrientation.SOUTH);
//            }
//        } else if (initialCell.getY() == finalCell.getY()) {
//            if (initialCell.getX() > finalCell.getX()) {
//                road.setGeographicalOrientation(GeographicalOrientation.WEST);
//            } else if (initialCell.getX() < finalCell.getX()) {
//                road.setGeographicalOrientation(GeographicalOrientation.EAST);
//            }
//        }
//    }
//
//    private void defineCamposInicioEFimECruzamento(Road road, String[] stringCoordinates) {
//        RoadMesh roadMesh = RoadMesh.getInstance();
//        int[] coordinates = UtilMethods.stringArrayToIntArray(stringCoordinates);
//
//        CellInterface initialCell;
//        if (coordinates[0] == 0 || coordinates[0] == roadMesh.getXSize() - 1
//                || coordinates[1] == 0 || coordinates[1] == roadMesh.getYSize() - 1) {
//
//            initialCell = new Cell(new Coordinate(coordinates[0], coordinates[1]));
//        } else {
//            if (road.getGeographicalOrientation() == GeographicalOrientation.NORTH) {
//                initialCell = new Cell(new Coordinate(coordinates[0], coordinates[1] - 1));
//            } else if (road.getGeographicalOrientation() == GeographicalOrientation.SOUTH) {
//                initialCell = new Cell(new Coordinate(coordinates[0], coordinates[1] + 1));
//            } else if (road.getGeographicalOrientation() == GeographicalOrientation.EAST) {
//                initialCell = new Cell(new Coordinate(coordinates[0] + 1, coordinates[1]));
//            } else {
//                initialCell = new Cell(new Coordinate(coordinates[0] - 1, coordinates[1]));
//            }
//
//            /*
//            Verifica se já existe cruzamento em(coordinates[0], coordinates[1]),
//            se não existe, cria o cruzamento e adiciona crossRoad na malha viaria
//            Logo após set initialCell como proximo desse cruzamento
//             */
//            Coordinate crossRoadCoordinate = new Coordinate(coordinates[0], coordinates[1]);
//            CellInterface crossRoad = roadMesh.searchCrossRoad(crossRoadCoordinate);
//            if (crossRoad == null) {
//                crossRoad = new CrossRoad(crossRoadCoordinate);
//                roadMesh.addCrossRoad(crossRoad);
//            }
//            crossRoad.addNext(initialCell);
//        }
//
//        CellInterface finalCell;
//        if (coordinates[2] == 0 || coordinates[2] == roadMesh.getXSize() - 1
//                || coordinates[3] == 0 || coordinates[3] == roadMesh.getYSize() - 1) {
//
//            finalCell = new FinalCell(new Coordinate(coordinates[2], coordinates[3]));
//        } else {
//            if (road.getGeographicalOrientation() == GeographicalOrientation.NORTH) {
//                finalCell = new FinalCell(new Coordinate(coordinates[2], coordinates[3] + 1));
//            } else if (road.getGeographicalOrientation() == GeographicalOrientation.SOUTH) {
//                finalCell = new FinalCell(new Coordinate(coordinates[2], coordinates[3] - 1));
//            } else if (road.getGeographicalOrientation() == GeographicalOrientation.EAST) {
//                finalCell = new FinalCell(new Coordinate(coordinates[2] - 1, coordinates[3]));
//            } else {
//                finalCell = new FinalCell(new Coordinate(coordinates[2] + 1, coordinates[3]));
//            }
//
//            /*
//            Verifica se já existe cruzamento em(coordinates[2], coordinates[3]),
//            se não existe, cria o cruzamento e adiciona crossRoad na malha viaria
//            Logo após crossRoad como proimo de finalCell, indicando que a ultima
//            célula da via leva ao cruzamento
//             */
//            Coordinate crossRoadCoordinate = new Coordinate(coordinates[2], coordinates[3]);
//            CellInterface crossRoad = roadMesh.searchCrossRoad(crossRoadCoordinate);
//            if (crossRoad == null) {
//                crossRoad = new CrossRoad(crossRoadCoordinate);
//                roadMesh.addCrossRoad(crossRoad);
//            }
//            finalCell.addNext(crossRoad);
//
//        }
//        defineTamanhoDaVia(road, initialCell, finalCell);
//
//        road.getCells()[0] = initialCell;
//        road.getCells()[road.getSize() - 1] = finalCell;
//
//    }
//
//    private void defineTamanhoDaVia(Road road, CellInterface intialCell, CellInterface finalCell) {
//        int xInitialCell = intialCell.getCoordinate().getX();
//        int yInitialCell = intialCell.getCoordinate().getY();
//        int xFinalCell = finalCell.getCoordinate().getX();
//        int yFinalCell = finalCell.getCoordinate().getY();
//
//        if (xInitialCell == xFinalCell) {
//            road.setSize(Math.abs(yInitialCell - yFinalCell) + 1);
//        } else if (yInitialCell == yFinalCell) {
//            road.setSize(Math.abs(xInitialCell - xFinalCell) + 1);
//        }
//    }
//
//    private void defineCamposIntermediarios(Road road) {
//        if (road.getGeographicalOrientation() == GeographicalOrientation.NORTH) {
//            int x = road.getCells()[0].getCoordinate().getX();
//            int initialY = road.getCells()[0].getCoordinate().getY();
//            for (int i = 1; i < road.getSize() - 1; i++) {
//                CellInterface cell = new Cell(new Coordinate(x, initialY - i));
//                road.getCells()[i] = cell;
//            }
//        } else if (road.getGeographicalOrientation() == GeographicalOrientation.SOUTH) {
//            int x = road.getCells()[0].getCoordinate().getX();
//            int initialY = road.getCells()[0].getCoordinate().getY();
//            for (int i = 1; i < road.getSize() - 1; i++) {
//                CellInterface cell = new Cell(new Coordinate(x, initialY + i));
//                road.getCells()[i] = cell;
//            }
//        } else if (road.getGeographicalOrientation() == GeographicalOrientation.WEST) {
//            int initialX = road.getCells()[0].getCoordinate().getX();
//            int y = road.getCells()[0].getCoordinate().getY();
//            for (int i = 1; i < road.getSize() - 1; i++) {
//                CellInterface cell = new Cell(new Coordinate(initialX - i, y));
//                road.getCells()[i] = cell;
//            }
//        } else if (road.getGeographicalOrientation() == GeographicalOrientation.EAST) {
//            int initialX = road.getCells()[0].getCoordinate().getX();
//            int y = road.getCells()[0].getCoordinate().getY();
//            for (int i = 1; i < road.getSize() - 1; i++) {
//                CellInterface cell = new Cell(new Coordinate(initialX + i, y));
//                road.getCells()[i] = cell;
//            }
//        }
//    }
//
//    private void defineProximos(Road road) {
//        for (int i = 0; i < road.getSize() - 1; i++) {
//            road.getCells()[i].addNext(road.getCells()[i + 1]);
//        }
//    }

}


