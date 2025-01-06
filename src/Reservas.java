import java.util.ArrayList;
import java.util.Date;

public class Reservas {
    private ArrayList<Reserva> reservas = new ArrayList<>();

    // Adicionar nova reserva
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
        System.out.println("Reserva feita com sucesso!");
    }

    // Remover reserva pelo número
    public void removerReserva(int numeroReserva) {
        reservas.removeIf(reserva -> reserva.getNumeroReserva() == numeroReserva);
        System.out.println("Reserva removida com sucesso!");
    }

    // Exibir todas as reservas de um utente pelo NIF
    public void exibirReservasPorUtente(String nifUtente) {
        boolean encontrou = false;
        for (Reserva reserva : reservas) {
            if (reserva.getNifUtente().equals(nifUtente)) {
                reserva.exibirDetalhes(false);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva encontrada para esse NIF.");
        }
    }

    // Exibir todas as reservas sem mostrar os nomes dos utentes
    public void exibirReservasAnonimas() {
        for (Reserva reserva : reservas) {
            reserva.exibirDetalhes(true);
        }
    }

    // Pesquisar reservas por intervalo de datas e NIF
    public void pesquisarReservasPorData(String nifUtente, Date dataInicio, Date dataFim) {
        boolean encontrou = false;
        for (Reserva reserva : reservas) {
            if (reserva.getNifUtente().equals(nifUtente) &&
                    !reserva.getDataInicio().before(dataInicio) &&
                    !reserva.getDataFim().after(dataFim)) {
                reserva.exibirDetalhes(false);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva encontrada no intervalo de datas fornecido.");
        }
    }
}
