import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    // Instância da classe Reservas para manipulação de reservas
    static Reservas reservas = new Reservas();

    public static void menuReserva() {
        Scanner ler = new Scanner(System.in);
        int op;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Inicializando o SimpleDateFormat
        // Menu de Reservas
        System.out.println("1 - Adicionar Reserva");
        System.out.println("2 - Remover Reserva");
        System.out.println("3 - Exibir Reservas");
        System.out.println("4 - Pesquisar Reservas por Data");
        System.out.print("Escolha uma opção de reserva: ");
        int opcaoReserva = ler.nextInt();

        switch (opcaoReserva) {
            case 1:
                // Adicionar reserva
                System.out.print("Digite o número da reserva: ");
                int numeroReserva = ler.nextInt();
                System.out.print("Digite o nome do utente: ");
                String nomeUtente = ler.next();

                // Data de registo será a data atual
                Date dataRegisto = new Date();

                // Pedir a data de início
                System.out.print("Digite a data de início (formato: dd/MM/yyyy): ");
                String inicioStr = ler.next();
                Date dataInicio = null;
                try {
                    dataInicio = sdf.parse(inicioStr); // Converter para Date usando SimpleDateFormat
                } catch (Exception e) {
                    System.out.println("Formato de data inválido.");
                }

                // Calcular a data de devolução (15 dias após a data de início)
                if (dataInicio != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dataInicio);
                    calendar.add(Calendar.DAY_OF_YEAR, 15); // Adicionando 15 dias
                    Date dataDevolucao = calendar.getTime();

                    // Exibir a data de devolução ao usuário
                    System.out.println("A data de devolução será: " + sdf.format(dataDevolucao));

                    // Criar a reserva com a data de devolução
                    Reserva reserva = new Reserva(numeroReserva, dataRegisto, dataInicio, nomeUtente);
                    reservas.adicionarReserva(reserva);
                }
                break;
            case 2:
                // Remover reserva
                System.out.print("Digite o número da reserva para remover: ");
                int numeroRemover = ler.nextInt();
                reservas.removerReserva(numeroRemover);
                break;
            case 3:
                // Exibir todas as reservas
                reservas.exibirReservas();
                break;
            case 4:
                // Pesquisar reservas por intervalo de datas
                System.out.print("Digite a data de início para pesquisa (formato: dd/MM/yyyy): ");
                String inicioPesquisa = ler.next();
                System.out.print("Digite a data de fim para pesquisa (formato: dd/MM/yyyy): ");
                String fimPesquisa = ler.next();
                try {
                    Date inicioData = sdf.parse(inicioPesquisa);
                    Date fimData = sdf.parse(fimPesquisa);
                    reservas.pesquisarReservasPorData(inicioData, fimData);
                } catch (Exception e) {
                    System.out.println("Formato de data inválido.");
                }
                break;
            default:
                System.out.println("Opção incorreta para reservas!");
                break;
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
                case 1:
                    // Ações relacionadas a Livros (não implementadas aqui)
                    break;
                case 2:
                    // Ações relacionadas a Jornais/Revistas (não implementadas aqui)
                    break;
                case 3:
                    // Ações relacionadas a Utentes (não implementadas aqui)
                    break;
                case 4:
                    menuReserva();
                    break;
                case 5:
                    // Empréstimos
                    break;
                case 6:
                    System.out.println("A sair ...");
                    break;
                default:
                    System.out.println("Opção incorreta! Tente novamente.");
                    break;
            }
        } while (op != 6);
    }

    public static void main(String[] args) {
        menu();
    }
}
