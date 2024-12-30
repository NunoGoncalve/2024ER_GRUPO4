import java.util.Scanner;

public class Main {

    public static void menuUtente(){
        int escolha;
        Utentes uts = new Utentes();
        do {

            System.out.println("Bem vindo a Utente");
            System.out.println("1) Listar");
            System.out.println("2) Adicionar");
            System.out.println("3) Pesquisar");
            System.out.println("4) Atualizar");
            System.out.println("5) Eliminar");
            System.out.println("6) Sair");
            System.out.println("Como deseja prosseguir?");
            Scanner ler = new Scanner(System.in);
            escolha = ler.nextInt();

            switch (escolha) {
                case 1:
                    uts.listarUtentes();
                    break;

                case 2:
                    uts.adicionarUtente();
                    break;

                case 3:
                    //  uts.pesquisarUtente();
                    break;

                case 4:
                    uts.atualizarUtente();
                    break;

                case 5:
                    uts.eliminarUtente();
                    break;

                case 6:
                    System.out.println("A sair do menu Utente....");
                    break;
                default:
                    System.out.println("ERRO!!  Escolha uma das opções");
                    break;
                }
        }while (escolha != 6);
    }


    public static void menu(){
        Scanner ler = new Scanner(System.in);
        int op;
        do{
            System.out.println("1 - Livros");
            System.out.println("2 - Jornais / Revistas");
            System.out.println("3 - Utentes");
            System.out.println("4 - Reservas");
            System.out.println("5 - Empréstimos");
            System.out.println("6 - Sair");
            System.out.print("Selecione uma opção: ");
            op=ler.nextInt();
            switch(op){
                case 1:
                    /**/
                    break;

                case 2:
                    /**/
                    break;

                case 3:
                    menuUtente();
                    break;

                case 4:
                    /**/
                    break;

                case 5:
                    /**/
                    break;
                case 6:
                    System.out.println("A sair ...");
                    break;
                default:
                    System.out.println("Opção incorreta! Tente novamente");
                    break;
            }
        }while(op!=6);
    }

    public static void main(String[] args) {
        menu();

    }
}