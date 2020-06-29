package cc.oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ParkingBoy implements ParkingAble, Reportable {
    protected List<ParkingLot> parkingLots = new ArrayList<>();


    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    @Override
    public Car pick(Ticket ticket) {
        return ParkingService.pick(parkingLots, ticket);
    }

    @Override
    public boolean isFull() {
        return parkingLots.stream().allMatch(ParkingAble::isFull);
    }

    @Override
    public boolean contains(Ticket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.contains(ticket));
    }

    @Override
    public int getCapacity() {
        return parkingLots.stream().mapToInt(ParkingLot::getCapacity).sum();
    }

    @Override
    public int getAvailableCapacity() {
        return parkingLots.stream().mapToInt(ParkingLot::getAvailableCapacity).sum();
    }

    @Override
    public String report(int level) {
        String content = String.format("%s %d %d\n", "B", getAvailableCapacity(), getCapacity(), "\n");
        return Reportable.generateReport(this, level, content);
    }

    @Override
    public List<Reportable> getSubReportables() {
        return new ArrayList<>(parkingLots);
    }
}
