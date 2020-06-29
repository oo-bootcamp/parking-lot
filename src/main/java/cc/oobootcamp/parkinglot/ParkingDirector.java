package cc.oobootcamp.parkinglot;

public class ParkingDirector {
    private ParkingManager manager;

    public ParkingDirector(ParkingManager manager) {
        this.manager = manager;
    }

    public String report() {
        return manager.report(0);
    }
}
