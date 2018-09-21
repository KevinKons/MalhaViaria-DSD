package model;

public enum GeographicalOrientation {
    
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");
    
    private String geographicalOrientation;

    GeographicalOrientation(String geographicalOrientation) {
        this.geographicalOrientation = geographicalOrientation;
    }
    
}
