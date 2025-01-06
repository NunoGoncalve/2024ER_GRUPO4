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
        boolean encontrouReserva = false;

        // Percorrer todas as reservas e verificar se a data de início ou data de fim está dentro do intervalo fornecido
        for (Reserva reserva : reservas) {
            // Verificar se a reserva tem dataInicio ou dataFim dentro do intervalo fornecido
            if ((reserva.getDataInicio().after(dataInicio) || reserva.getDataInicio().equals(dataInicio)) &&
                    (reserva.getDataInicio().before(dataFim) || reserva.getDataInicio().equals(dataFim)) ||
                    (reserva.getDataFim().after(dataInicio) && reserva.getDataFim().before(dataFim))) {
                reserva.exibirDetalhes();
                encontrouReserva = true;
            }
        }

        if (!encontrouReserva) {
            System.out.println("Não há reservas no intervalo de datas fornecido.");
        }
    }

    // Exibir todas as reservas de um determinado utente, com base no NIF
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