import java.util.Scanner;

public class main {
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
                    /**/
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