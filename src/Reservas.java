import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Reservas {
    private ArrayList<Reserva> reservas = new ArrayList<>();


    // Criar uma nova reserva
    public void adicionarReserva() {
        Reserva res = new Reserva();

        this.reservas.add(res.criarReserva(reservas.size()+1));
    }

    // Exibir todas as reservas
    public void exibirReservas() {
        for (Reserva reserva : reservas) {
            reserva.exibirDetalhes(true);  // Exibe todas as reservas anonimamente
        }
    }

    // Exibir todas as reservas com NIF
    public void exibirReservasComNif() {
        for (Reserva reserva : reservas) {
            reserva.exibirDetalhes(false);  // Exibe todas as reservas com NIF
        }
    }

    // Remover uma reserva
    public void removerReserva(int numeroReserva) {
        reservas.removeIf(reserva -> reserva.getNumeroReserva() == numeroReserva);
        System.out.println("Reserva removida com sucesso!");
    }

    // Pesquisar reservas por data
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
