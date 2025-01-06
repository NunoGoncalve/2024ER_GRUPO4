import java.util.ArrayList;
import java.util.Date; // Importando a classe Date

public class Reservas {
    private ArrayList<Reserva> reservas = new ArrayList<>(); // Lista de todas as reservas

    // Métodos para adicionar, remover, pesquisar e listar reservas
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void removerReserva(int numeroReserva) {
        reservas.removeIf(reserva -> reserva.getNumeroReserva() == numeroReserva);
    }

    public void pesquisarReservasPorData(Date dataInicio, Date dataFim) {
        for (Reserva reserva : reservas) {
            if (!reserva.getDataInicio().before(dataInicio) && !reserva.getDataFim().after(dataFim)) {
                reserva.exibirDetalhes();
            }
        }
    }

    public void exibirReservas() {
        for (Reserva reserva : reservas) {
            reserva.exibirDetalhes();
        }
    }
}
