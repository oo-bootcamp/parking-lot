package cc.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingDirectorTest {
    @Test
    void should_return_correct_report_when_report_given_a_manager_managed_two_robot_and_a_locker() {

        StringBuilder expectedReport = new StringBuilder();
        expectedReport.append("M 20 40\n");
        expectedReport.append("\tP 4 7\n");
        expectedReport.append("\tB 9 20\n");
        expectedReport.append("\t\tP 3 7\n");
        expectedReport.append("\t\tP 1 3\n");
        expectedReport.append("\t\tP 5 10\n");
        expectedReport.append("\tB 7 13\n");
        expectedReport.append("\t\tP 5 8\n");
        expectedReport.append("\t\tP 2 5\n");

        ParkingLot parkingLot = createParkingLot(4, 7);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(createParkingLot(3, 7),
                createParkingLot(1, 3), createParkingLot(5, 10));
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(createParkingLot(5, 8), createParkingLot(2, 5));

        ParkingManager manager = new ParkingManager(parkingLot, smartParkingBoy, superParkingBoy);
        ParkingDirector director = new ParkingDirector(manager);

        String report = director.report();

        assertThat(report).isEqualTo(expectedReport.toString());
    }

    private ParkingLot createParkingLot(int available, int capacity) {
        ParkingLot parkingLot = new ParkingLot(capacity);
        for (int i = 0; i < capacity - available; i++) {
            parkingLot.park(new Car());
        }
        return parkingLot;
    }
}
