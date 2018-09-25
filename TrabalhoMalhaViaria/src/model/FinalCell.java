package model;

/**
 *
 * @author Avell
 */
public class FinalCell extends AbstractCell {

    private AbstractCell next;

    public FinalCell(Coordinate coordinate) {
        super.setCoordinate(coordinate);
    }

    @Override
    public void advanceVehicle(Vehicle vehicle) {
        if (next.isNotBusy()) { //Se cruzamento está vazio
            if (next.next().isNotBusy()) { //se campo após o cruzamento está vazio
                vehicle.setCell(this.next);
                this.next.setBusy(true, vehicle.getColor());
                super.setBusy(false, vehicle.getColor());
                
                this.next.advanceVehicle(vehicle);
            }
        }
    }

    @Override
    public void addNext(AbstractCell cell) {
        this.next = cell;
    }

    @Override
    public AbstractCell next() {
        return next;
    }

    @Override
    public boolean equals(Object obj) {
        Cell outro = (Cell) obj;
        if (super.getCoordinate().equals(outro.getCoordinate())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isFinalCell() {
        return true;
    }
    
    

}
