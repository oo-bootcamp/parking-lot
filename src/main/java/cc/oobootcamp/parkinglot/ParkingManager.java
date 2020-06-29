package cc.oobootcamp.parkinglot;

import cc.oobootcamp.parkinglot.exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingManager implements Reportable {
    private List<ParkingAble> parkingAbles = new ArrayList<>();

    public ParkingManager(ParkingAble... parkingBoys) {
        this.parkingAbles.addAll(Arrays.asList(parkingBoys));
    }

    public Ticket park(Car car) {
        for (ParkingAble parkingAble : parkingAbles) {
            if (!parkingAble.isFull()) {
                return parkingAble.park(car);
            }
        }
        throw new ParkingLotFullException();
    }

    public Car pick(Ticket ticket) {
        return ParkingService.pick(parkingAbles, ticket);
    }

    public int getCapacity() {
        int totalCapacity = 0;
        for (ParkingAble parkingAble : parkingAbles) {
            totalCapacity += parkingAble.getCapacity();
        }
        return totalCapacity;
    }

    public int getAvailableCapacity() {
        int totalAvailableCapacity = 0;
        for (ParkingAble parkingAble : parkingAbles) {
            totalAvailableCapacity += parkingAble.getAvailableCapacity();
        }
        return totalAvailableCapacity;
    }

    @Override
    public String report(int level) {
        String content = String.format("%s %d %d\n", "M", getAvailableCapacity(), getCapacity(), "\n");
        return Reportable.generateReport(this, level, content);
    }

    @Override
    public List<Reportable> getSubReportables() {
        List<Reportable> reportables = new ArrayList<>();
        for (ParkingAble parkingAble : parkingAbles) {
            reportables.add((Reportable) parkingAble);
        }
        return reportables;
    }
}
