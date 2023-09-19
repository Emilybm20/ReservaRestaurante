import java.rmi.Remote;
        import java.rmi.RemoteException;
        import java.util.List;

public interface RestaurantReservation extends Remote {
    boolean makeReservation(String guestName, int numberOfGuests, String date) throws RemoteException;
    List<String> listReservations() throws RemoteException;
    boolean cancelReservation(String guestName, String date) throws RemoteException;
    int checkAvailability(String date) throws RemoteException;
}
