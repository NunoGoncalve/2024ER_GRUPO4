import java.util.Scanner;

public class Main {

    public static void menuEmprestimos() {
        Scanner ler = new Scanner(System.in);
        int opc;
        do {
            System.out.println("1 - Listar");
            System.out.println("2 - Adicionar");
            System.out.println("3 - Pesquisar");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Devolver");
            System.out.println("6 - Sair");
            System.out.print("Selecione uma opção: ");
            opc = ler.nextInt();
            switch (opc) {
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
        } while (opc != 6);
    }

    public static void menuLivros(){
        int op;
        Scanner ler = new Scanner(System.in);
        Livros livros=new Livros();
        livros.lerLivros();
        if(livros.isEmpty()){
            do {
                System.out.println("- Livros -");
                System.out.println("1) - Adicionar");
                System.out.println("2) - Sair");
                System.out.print("Selecione uma opção: ");
                op = ler.nextInt();
                switch (op) {
                    case 1:
                        livros.adicionarLivro();
                        livros.guardarLivros();
                        op = 2;
                        break;
                    case 2:
                        livros.limparLivros();
                        System.out.println("A sair do menu livro...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente");
                        break;
                }
            } while (op != 2);
        }
        if(!livros.isEmpty()){
            do {
                System.out.println("- Livros -");
                System.out.println("1) - Listar");
                System.out.println("2) - Adicionar");
                System.out.println("3) - Pesquisar");
                System.out.println("4) - Atualizar");
                System.out.println("5) - Eliminar");
                System.out.println("6) - Sair");
                System.out.print("Selecione uma opção: ");
                op = ler.nextInt();
                switch (op) {
                    case 1:
                        livros.listarLivros();
                        break;
                    case 2:
                        livros.adicionarLivro();
                        break;

                    case 3:
                        livros.listaLivro(livros.procuraLivro(livros.pedeIsbn()));
                        break;
                    case 4:
                        livros.atualizarLivro();
                        break;

                    case 5:
                        livros.eliminarLivro(livros.pedeIsbn());
                        if(livros.isEmpty()){
                            livros.guardarLivros();
                            livros.limparLivros();
                            System.out.println("A sair do menu livro...");
                            op =6;
                        }
                        break;

                    case 6:
                        livros.guardarLivros();
                        livros.limparLivros();
                        System.out.println("A sair do menu livro...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente");
                        break;
                }
            }while (op != 6);
        }
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
                    menuLivros();
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
                    menuEmprestimos();
                    break;
                case 6:
                    System.out.println("A sair ...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente");
                    break;
            }
        }while(op!=6);
    }

    public static void main(String[] args) {

        System.out.println("Hello, World!");
    }
}