import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    static Reservas reservas = new Reservas(); // Lista global de reservas

    public static void menuReserva() {
        Scanner ler = new Scanner(System.in);
        int op;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("1 - Adicionar Reserva");
        System.out.println("2 - Remover Reserva");
        System.out.println("3 - Exibir Reservas por Utente");
        System.out.println("4 - Pesquisar Reservas por Data");
        System.out.println("5 - Exibir Todas as Reservas (Anônimo)");
        System.out.print("Escolha uma opção: ");
        int opcaoReserva = ler.nextInt();

        switch (opcaoReserva) {
            case 1:
                // Adicionar reserva
                System.out.print("Digite o número da reserva: ");
                int numeroReserva = ler.nextInt();
                System.out.print("Digite o NIF do utente: ");
                String nifUtente = ler.next();

                // Data de registo será a data atual
                Date dataRegisto = new Date();

                // Pedir a data de início
                System.out.print("Digite a data de início (dd/MM/yyyy): ");
                String inicioStr = ler.next();
                try {
                    Date dataInicio = sdf.parse(inicioStr);

                    // Criar reserva
                    Reserva reserva = new Reserva(numeroReserva, dataRegisto, dataInicio, nifUtente);
                    reservas.adicionarReserva(reserva);

                    // Mostrar data de devolução
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dataInicio);
                    calendar.add(Calendar.DAY_OF_YEAR, 15);
                    Date dataFim = calendar.getTime();
                    System.out.println("A data de devolução será: " + sdf.format(dataFim));

                } catch (Exception e) {
                    System.out.println("Formato de data inválido.");
                }
                break;

            case 2:
                // Remover reserva
                System.out.print("Digite o número da reserva para remover: ");
                int numeroRemover = ler.nextInt();
                reservas.removerReserva(numeroRemover);
                break;

            case 3:
                // Exibir reservas por utente
                System.out.print("Digite o NIF do utente: ");
                String nifConsulta = ler.next();
                reservas.exibirReservasPorUtente(nifConsulta);
                break;

            case 4:
                // Pesquisar reservas por data
                System.out.print("Digite o NIF do utente: ");
                String nifPesquisa = ler.next();
                System.out.print("Digite a data de início (dd/MM/yyyy): ");
                String inicioPesquisa = ler.next();
                System.out.print("Digite a data de fim (dd/MM/yyyy): ");
                String fimPesquisa = ler.next();
                try {
                    Date dataInicioPesquisa = sdf.parse(inicioPesquisa);
                    Date dataFimPesquisa = sdf.parse(fimPesquisa);
                    reservas.pesquisarReservasPorData(nifPesquisa, dataInicioPesquisa, dataFimPesquisa);
                } catch (Exception e) {
                    System.out.println("Formato de data inválido.");
                }
                break;

            case 5:
                // Exibir todas as reservas anonimamente
                reservas.exibirReservasAnonimas();
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void menu() {
        Scanner ler = new Scanner(System.in);
        int op;
        do {
            System.out.println("1 - Livros");
            System.out.println("2 - Jornais / Revistas");
            System.out.println("3 - Utentes");
            System.out.println("4 - Reservas");
            System.out.println("5 - Empréstimos");
            System.out.println("6 - Sair");
            System.out.print("Selecione uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 4:
                    menuReserva();
                    break;
                case 6:
                    System.out.println("A sair ...");
                    break;
                default:
                    System.out.println("Opção incorreta!");
                    break;
            }
        } while (op != 6);
    }

    public static void main(String[] args) {
        menu();
    }
}
