import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservas {
    private List<Reserva> listaReservas = new ArrayList<>();

    // Método para adicionar uma nova reserva
    public void criarReserva(SimpleDateFormat sdf) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Você vai reservar mais de um livro? (sim/não): ");
        String resposta = scanner.nextLine();

        int numeroLivros;
        if ("sim".equalsIgnoreCase(resposta)) {
            System.out.print("Quantos livros você deseja reservar? ");
            numeroLivros = scanner.nextInt();
            scanner.nextLine();  // Consumir quebra de linha
        } else {
            numeroLivros = 1;
        }

        for (int i = 1; i <= numeroLivros; i++) {
            System.out.print("Digite o NIF do utente: ");
            String nif = scanner.nextLine();
            System.out.print("Digite o ISBN do livro #" + i + ": ");
            String isbn = scanner.nextLine();

            System.out.print("Digite a data de reserva (dd/MM/yyyy): ");
            String dataReserva = scanner.nextLine();
            Date dataInicio = null;
            try {
                dataInicio = sdf.parse(dataReserva);
            } catch (Exception e) {
                System.out.println("Data inválida. Tente novamente.");
                return;
            }

            // Defina o termo da reserva (15 dias após a data de início)
            long termoReserva = 15L * 24 * 60 * 60 * 1000;
            Date dataFim = new Date(dataInicio.getTime() + termoReserva);

            Reserva reserva = new Reserva(nif, dataInicio, dataFim, isbn);
            listaReservas.add(reserva);
            System.out.println("Reserva adicionada com sucesso:");
            System.out.println("Reserva #" + String.format("%03d", listaReservas.size()));
            System.out.println("ISBN: " + isbn);
            System.out.println("NIF: " + nif);
            System.out.println("Início: " + sdf.format(dataInicio));
            System.out.println("Término: " + sdf.format(dataFim));
        }
    }

    // Método para exibir todas as reservas
    public void exibirReservas() {
        if (listaReservas.isEmpty()) {
            System.out.println("Não há reservas.");
            return;
        }

        for (Reserva reserva : listaReservas) {
            System.out.println("Reserva #" + String.format("%03d", listaReservas.indexOf(reserva) + 1));
            System.out.println("ISBN: " + reserva.getIsbn());
            System.out.println("NIF: " + reserva.getNif());
            System.out.println("Início: " + reserva.getDataInicio());
            System.out.println("Término: " + reserva.getDataFim());
        }
    }

    // Método para exibir reservas com NIF
    public void exibirReservasComNif() {
        if (listaReservas.isEmpty()) {
            System.out.println("Não há reservas.");
            return;
        }

        for (Reserva reserva : listaReservas) {
            System.out.println("Reserva #" + String.format("%03d", listaReservas.indexOf(reserva) + 1));
            System.out.println("ISBN: " + reserva.getIsbn());
            System.out.println("NIF: " + reserva.getNif());
            System.out.println("Início: " + reserva.getDataInicio());
            System.out.println("Término: " + reserva.getDataFim());
        }
    }

    // Método para pesquisar reservas por data
    public void pesquisarReservasPorData(String nif, Date inicio, Date fim) {
        boolean encontrou = false;
        for (Reserva reserva : listaReservas) {
            if ((reserva.getDataInicio().after(inicio) || reserva.getDataInicio().equals(inicio)) &&
                    (reserva.getDataFim().before(fim) || reserva.getDataFim().equals(fim)) &&
                    reserva.getNif().equals(nif)) {
                System.out.println("Reserva #" + String.format("%03d", listaReservas.indexOf(reserva) + 1));
                System.out.println("ISBN: " + reserva.getIsbn());
                System.out.println("NIF: " + reserva.getNif());
                System.out.println("Início: " + reserva.getDataInicio());
                System.out.println("Término: " + reserva.getDataFim());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Não há reservas para este NIF dentro do período informado.");
        }
    }

    // Método para remover uma reserva
    public void removerReserva(int numero) {
        if (numero > 0 && numero <= listaReservas.size()) {
            listaReservas.remove(numero - 1);
            System.out.println("Reserva #" + String.format("%03d", numero) + " removida com sucesso.");
        } else {
            System.out.println("Número de reserva inválido.");
        }
    }
}
