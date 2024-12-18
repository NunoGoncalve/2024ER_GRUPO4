import java.util.Scanner;

public class Emprestimo {
    private int numero_livro;
    private String nome_utente;
    private String nome_livro;
    private int data_inicio;
    private int data_prevista_fim;
    private int data_defenitiva_fim;
    public static void menuEmprestimo(){
        Scanner ler = new Scanner(System.in);
        int op;
        do{
            System.out.println("1 - Listar");
            System.out.println("2 - Adicionar");
            System.out.println("3 - Pesquisar");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Eliminar");
            System.out.println("6 - Sair");
            System.out.print("Selecione uma opção: ");
            op=ler.nextInt();
            switch(op){
                case 1:
                    System.out.println(1);
                    break;

                case 2:
                    System.out.println(2);
                    break;

                case 3:
                    System.out.println(3);
                    break;

                case 4:
                    System.out.println(4);
                    break;
                    
                case 5:
                    System.out.println(5);
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
}
