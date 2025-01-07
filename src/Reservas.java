import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Reservas {
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private ArrayList<String> livrosDisponiveis = new ArrayList<>();  // Lista de livros disponíveis

    // Adicionar título de livro ao sistema
    public void adicionarLivro(String livroTitulo) {
        livrosDisponiveis.add(livroTitulo);
    }

    // Criar uma nova reserva
    public void criarReserva(SimpleDateFormat sdf) {
        Scanner ler = new Scanner(System.in);

        // Exibir os livros disponíveis
        System.out.println("Escolha um livro para a reserva:");
        for (int i = 0; i < livrosDisponiveis.size(); i++) {
            System.out.println(i + 1 + " - " + livrosDisponiveis.get(i));
        }

        int opcaoLivro = ler.nextInt();
        String livroSelecionado = livrosDisponiveis.get(opcaoLivro - 1);  // Selecionando o livro escolhido

        System.out.print("Digite o número da reserva: ");
        int numeroReserva = ler.nextInt();
        System.out.print("Digite o NIF do utente: ");
        String nifUtente = ler.next();

        Date dataRegisto = new Date();
        System.out.print("Digite a data de início (dd/MM/yyyy): ");
        String inicioStr = ler.next();
        try {
            Date dataInicio = sdf.parse(inicioStr);

            // Criar reserva com o título do livro
            Reserva reserva = new Reserva(numeroReserva, dataRegisto, dataInicio, nifUtente, livroSelecionado);
            reservas.add(reserva);

            // Exibir a data de devolução
            System.out.println("A data de devolução será: " + sdf.format(reserva.getDataFim()));

        } catch (Exception e) {
            System.out.println("Formato de data inválido.");
        }
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
