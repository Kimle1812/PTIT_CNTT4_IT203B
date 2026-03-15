package Exercise01;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets;

    public TicketPool(String roomName, int numberOfTickets) {
        this.roomName = roomName;
        tickets = new ArrayList<>();
        for (int i = 1; i <= numberOfTickets; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i), roomName));
        }
    }

    public synchronized Ticket sellTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }
        return null;
    }

    public boolean isSoldOut() {
        return tickets.stream().allMatch(Ticket::isSold);
    }

    public int remainingTickets() {
        return (int) tickets.stream().filter(t -> !t.isSold()).count();
    }

    public String getRoomName() {
        return roomName;
    }
}