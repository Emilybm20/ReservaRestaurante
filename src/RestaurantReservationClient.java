import java.rmi.Naming;
import java.util.List;

public class RestaurantReservationClient {
    public static void main(String[] args) {
        try {
            // Obtenha a referência remota do objeto de serviço do servidor RMI
            RestaurantReservation reservationService = (RestaurantReservation) Naming.lookup("rmi://localhost/RestaurantReservation");

            // Faça uma reserva
            boolean success = reservationService.makeReservation("John Doe", 4, "2023-09-20");

            if (success) {
                System.out.println("Reserva feita com sucesso!");
            } else {
                System.out.println("A reserva falhou.");
            }

            // Liste todas as reservas
            List<String> reservations = reservationService.listReservations();
            System.out.println("Reservas existentes:");
            for (String reservation : reservations) {
                System.out.println(reservation);
            }

            // Verifique a disponibilidade para uma data específica
            int availableSeats = reservationService.checkAvailability("2023-09-20");
            System.out.println("Mesas disponíveis em 2023-09-20: " + availableSeats);

            // Cancelar uma reserva
            boolean cancelSuccess = reservationService.cancelReservation("John Doe", "2023-09-20");
            if (cancelSuccess) {
                System.out.println("Cancelamento de reserva bem-sucedido.");
            } else {
                System.out.println("Não foi possível cancelar a reserva.");
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente RMI: " + e.getMessage());
        }
    }
}
