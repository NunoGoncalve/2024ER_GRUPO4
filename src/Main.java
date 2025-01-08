import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    static Reservas reservas = new Reservas();  // Lista global de reservas

    public static void menuReserva() {
        Scanner ler = new Scanner(System.in);


        System.out.println("1 - Adicionar Reserva");
        System.out.println("2 - Exibir Reservas");
        System.out.println("3 - Exibir Reservas com NIF");
        System.out.println("4 - Remover Reserva");
        System.out.println("5 - Pesquisar Reservas por Data");
        System.out.println("6 - Sair");
        System.out.print("Escolha uma opção: ");
        int opcaoReserva = ler.nextInt();

        switch (opcaoReserva) {
            case 1:
                reservas.adicionarReserva();  // Passando o SimpleDateFormat para o metodo, tive que diminuir aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                break;
            case 2:
                reservas.exibirReservas();  // Exibe todas as reservas anonimamente
                break;
            case 3:
                reservas.exibirReservasComNif();  // Exibe todas as reservas com NIF
                break;
            case 4:
                System.out.print("Digite o número da reserva para remover: ");
                int numeroRemover = ler.nextInt();
                reservas.removerReserva(numeroRemover);  // Remove a reserva
                break;
            case 5:
                /*System.out.print("Digite o NIF do utente: ");
                String nifPesquisa = ler.next();
                System.out.print("Digite a data de início (dd/MM/yyyy): ");
                String inicioPesquisa = ler.next();
                System.out.print("Digite a data de fim (dd/MM/yyyy): ");
                String fimPesquisa = ler.next();
                try {
                    Date dataInicioPesquisa = sdf.parse(inicioPesquisa);
                    Date dataFimPesquisa = sdf.parse(fimPesquisa);
                    // Pesquisa reservas por intervalo de datas
                    reservas.pesquisarReservasPorData(nifPesquisa, dataInicioPesquisa, dataFimPesquisa);
                } catch (Exception e) {
                    System.out.println("Formato de data inválido.");
                }*/
                break;
            case 6:
                System.out.println("A sair ...");
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
                case 1:
                    
                    break;
                case 2:
                    // Funções para jornais/revistas
                    break;
                case 3:
                    // Funções para utentes
                    break;
                case 4:
                    menuReserva();  // Chama o menu de reservas
                    break;
                case 5:
                    // Funções para empréstimos
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
        menu();  // Chama o menu principal
    }
}
