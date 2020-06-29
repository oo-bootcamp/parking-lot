package cc.oobootcamp.parkinglot;

import cc.oobootcamp.parkinglot.exception.ParkingLotFullException;
import cc.oobootcamp.parkinglot.exception.TicketInvalidException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot implements ParkingAble, Reportable {
    private int space;
    private Map<Ticket, Car> parkedCars = new HashMap<>();

    public ParkingLot(int space) {
        this.space = space;
    }

    public Ticket park(Car car) {
        if (isFull()) {
            throw new ParkingLotFullException();
        }
        Ticket ticket = new Ticket();
        parkedCars.put(ticket, car);
        return ticket;
    }

    public boolean isFull() {
        return parkedCars.size() >= space;
    }

    public Car pick(Ticket ticket) {
        if (parkedCars.containsKey(ticket)) {
            return parkedCars.remove(ticket);
        }
        throw new TicketInvalidException();
    }

    public boolean contains(Ticket ticket) {
        return parkedCars.containsKey(ticket);
    }

    @Override
    public int getCapacity() {
        return space;
    }

    @Override
    public int getAvailableCapacity() {
        return space - parkedCars.size();
    }

    @Override
    public String report(int level) {
        String content = String.format("%s %d %d\n", "P", getAvailableCapacity(), getCapacity(), "\n");
        return Reportable.generateReport(this, level, content);
    }

    @Override
    public List<Reportable> getSubReportables() {
        return Collections.emptyList();
    }

    public int availableSpace() {
        return space - parkedCars.size();
    }

    public double vacancyRate() {
        return availableSpace() / (double) space;
    }
}
