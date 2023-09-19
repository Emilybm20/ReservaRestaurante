import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RestaurantReservationServer {
    public static void main(String[] args) {
        try {
            // Número máximo de mesas disponíveis no restaurante
            int maxCapacity = 50;

            // Crie o registro RMI na porta 1099
            LocateRegistry.createRegistry(1099);

            // Crie uma instância do objeto de implementação e vincule-o ao registro RMI
            RestaurantReservation reservationService = new RestaurantReservationImpl(maxCapacity);
            Naming.rebind("RestaurantReservation", reservationService);

            System.out.println("Servidor RMI pronto para aceitar reservas.");
        } catch (Exception e) {
            System.err.println("Erro no servidor RMI: " + e.getMessage());
        }
    }
}
