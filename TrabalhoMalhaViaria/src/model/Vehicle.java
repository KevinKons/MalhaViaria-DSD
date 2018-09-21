package model;

public class Vehicle implements Runnable {

    private int velocity;
    private CellInterface cell;

    public Vehicle(int velocity) {
        this.velocity = velocity;
    }
    
    @Override
    public void run() {
        while(!cell.isExitCell()) {
            cell.advanceVehicle(this);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        cell.setBusy(false);
    }

    public CellInterface getCell() {
        return cell;
    }

    public void setCell(CellInterface cell) {
        this.cell = cell;
    }
        
}
