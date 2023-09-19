import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RestaurantReservationImpl extends UnicastRemoteObject implements RestaurantReservation {
    private List<String> reservations;
    private int maxCapacity; // Número máximo de mesas disponíveis

    public RestaurantReservationImpl(int maxCapacity) throws RemoteException {
        reservations = new ArrayList<>();
        this.maxCapacity = maxCapacity;
    }

    @Override
    public synchronized boolean makeReservation(String guestName, int numberOfGuests, String date) throws RemoteException {
        // Implemente a lógica de reserva aqui
        if (checkAvailability(date) >= numberOfGuests) {
            String reservationInfo = guestName + " - " + numberOfGuests + " pessoas - " + date;
            reservations.add(reservationInfo);
            return true; // Retorna true se a reserva for bem-sucedida
        }
        return false; // Retorna false se não houver mesas disponíveis
    }

    @Override
    public synchronized List<String> listReservations() throws RemoteException {
        return reservations;
    }

    @Override
    public synchronized boolean cancelReservation(String guestName, String date) throws RemoteException {
        // Implemente a lógica de cancelamento de reserva aqui
        Iterator<String> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            String reservation = iterator.next();
            if (reservation.contains(guestName) && reservation.contains(date)) {
                iterator.remove();
                return true; // Retorna true se o cancelamento for bem-sucedido
            }
        }
        return false; // Retorna false se a reserva não for encontrada
    }

    @Override
    public synchronized int checkAvailability(String date) throws RemoteException {
        // Implemente a lógica de verificação de disponibilidade aqui
        int totalReservedSeats = 0;
        for (String reservation : reservations) {
            if (reservation.contains(date)) {
                String[] parts = reservation.split(" - ");
                int numberOfGuests = Integer.parseInt(parts[1].split(" ")[0]);
                totalReservedSeats += numberOfGuests;
            }
        }
        return maxCapacity - totalReservedSeats;
    }
}
