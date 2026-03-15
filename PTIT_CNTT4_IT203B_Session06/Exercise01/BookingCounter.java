package Exercise01;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;
    private Random random = new Random();

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {
        while (!roomA.isSoldOut() || !roomB.isSoldOut()) {
            TicketPool chosenRoom = random.nextBoolean() ? roomA : roomB;
            Ticket ticket = chosenRoom.sellTicket();

            if (ticket != null) {
                soldCount++;
                System.out.println(counterName + " bán vé phòng " + chosenRoom.getRoomName());
                System.out.println(counterName + " đã bán vé " + ticket.getTicketId());
            } else {
                TicketPool otherRoom = (chosenRoom == roomA) ? roomB : roomA;
                Ticket otherTicket = otherRoom.sellTicket();
                if (otherTicket != null) {
                    soldCount++;
                    System.out.println(counterName + " bán vé phòng " + otherRoom.getRoomName());
                    System.out.println(counterName + " đã bán vé " + otherTicket.getTicketId());
                }
            }
        }
    }

    public int getSoldCount() {
        return soldCount;
    }
}