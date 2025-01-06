import java.util.ArrayList;
import java.util.Date; // Importando a classe Date

public class Reservas {
    private ArrayList<Reserva> reservas = new ArrayList<>(); // Lista de todas as reservas

    // Adicionar uma nova reserva
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
        System.out.println("Reserva registrada com sucesso."); // Exibindo a mensagem de sucesso
    }

    // Remover uma reserva
    public void removerReserva(int numeroReserva) {
        reservas.removeIf(reserva -> reserva.getNumeroReserva() == numeroReserva);
        System.out.println("Reserva removida com sucesso.");
    }

    // Pesquisar reservas por intervalo de datas
    public void pesquisarReservasPorData(Date dataInicio, Date dataFim) {
        for (Reserva reserva : reservas) {
            if (!reserva.getDataInicio().before(dataInicio) && !reserva.getDataFim().after(dataFim)) {
                reserva.exibirDetalhes();
            }
        }
    }

    // Exibir reservas para um utente específico, com base no NIF
    public void exibirReservasPorNIF(String nifUtente) {
        boolean encontrouReserva = false;

        for (Reserva reserva : reservas) {
            if (reserva.getNomeUtente().equals(nifUtente)) { // Verifica se o NIF do utente corresponde
                reserva.exibirDetalhes();
                encontrouReserva = true;
            }
        }

        if (!encontrouReserva) {
            System.out.println("Não há reservas para o NIF informado.");
        }
    }
}
