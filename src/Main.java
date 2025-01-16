import java.util.Scanner;

public class Main {

    public static void menuJornaisRevistas(String bib){
        int op;
        Scanner ler = new Scanner(System.in);
        JornaisRevistas jornaisRevistas =new JornaisRevistas();
        jornaisRevistas.ler_jornaisRevistas(bib);
        do {

            System.out.println("- Jornais/Revistas -");
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
                    jornaisRevistas.listarJornaisRevistas();
                    break;
                case 2:
                    jornaisRevistas.adicionarJornalRevista();
                    break;

                case 3:
                    jornaisRevistas.listaJornalRevista();
                    break;
                case 4:
                    jornaisRevistas.atualizarJornalRevista();
                    break;

                case 5:
                    jornaisRevistas.eliminarJornalRevista();
                    break;

                case 6:
                    jornaisRevistas.guardarJornaisRevistas(bib);
                    jornaisRevistas.limparJornaisRevistas();
                    System.out.println("A sair do menu livro...");
                    break;
                default:
                    System.out.println("Opção incorreta! Tente novamente");
                    break;
            }
        }while (op != 6);
    }

    public static boolean menuLivros(String bib){
        int op;
        Scanner ler = new Scanner(System.in);
        Livros livros=new Livros();
        livros.lerLivros(bib);
        if(livros.isEmpty()){
            do {
                System.out.println("- Livros -");
                System.out.println("1) - Adicionar");
                System.out.println("2) - Sair");
                System.out.print("Biblioteca vazia! Selecione uma opção: ");
                op = ler.nextInt();
                switch (op) {
                    case 1:
                        livros.adicionarLivro();
                        livros.guardarLivros(bib);
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
                            livros.guardarLivros(bib);
                            livros.limparLivros();
                            return true;
                        }
                        break;

                    case 6:
                        livros.guardarLivros(bib);
                        livros.limparLivros();
                        System.out.println("A sair do menu livro...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente");
                        break;
                }
            }while (op != 6);
        }
        return false;
    }

    public static void menuUtente(String bib){
        int escolha;
        Utentes uts = new Utentes();
        uts.lerUtentes(bib);
        if(uts.isEmpty()){}
        do {

            System.out.println("--------------- Bem vindo à secção Utente ---------------");
            System.out.println("1) Listar");
            System.out.println("2) Adicionar");
            System.out.println("3) Pesquisar");
            System.out.println("4) Atualizar");
            System.out.println("5) Eliminar");
            System.out.println("6) Sair");
            System.out.print("Como deseja prosseguir?  ");
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
                    uts.pesquisarUtente();
                    break;

                case 4:
                    uts.atualizarUtente();
                    break;

                case 5:
                    uts.eliminarUtente();
                    break;

                case 6:
                    System.out.println("A sair do menu Utente....\n");
                    uts.guardarUtentes(bib);
                    break;
                default:
                    System.out.println("ERRO!!  Escolha uma das opções");
                    break;
                }
        }while (escolha != 6);
    }


    public static void menu(String bib){
        Scanner ler = new Scanner(System.in);
        String op;
        do{
            System.out.println("--------------- Bem vindo à biblioteca "+bib.substring(bib.indexOf("/") + 1)+" ---------------");
            System.out.println("1) - Livros");
            System.out.println("2) - Jornais / Revistas");
            System.out.println("3) - Utentes");
            System.out.println("4) - Reservas");
            System.out.println("5) - Empréstimos");
            System.out.println("6) - Sair");
            System.out.print("Selecione uma opção: ");
            op=ler.next();

            switch(op){
                case "1":
                    do{}while(menuLivros(bib));
                    break;

                case "2":
                    menuJornaisRevistas(bib);
                    break;

                case "3":
                    menuUtente(bib);
                    break;

                case "4":
                    /**/
                    break;

                case "5":
                    /**/
                    break;
                case "6":
                    System.out.println("A sair ...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente");
                    break;
            }
        }while(!op.equals("6"));
    }

    public static boolean menuBibliotecas(){
        Scanner ler = new Scanner(System.in);
        Bibliotecas bib = new Bibliotecas();
        String op;

        if(bib.isEmpty()){
            do{
                System.out.println("--------------- Bem vindo à secção Bibliotecas ---------------");
                System.out.println("1) - Adicionar");
                System.out.println("2) - Sair");
                System.out.print("Sem bibliotecas! Selecione uma opção: ");
                op=ler.next();
                switch(op){
                    case "1":
                        bib.adicionarBiblioteca();
                        op="2";
                        break;
                    case "2":
                        System.out.println("A sair ...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente");
                        break;
                }
            }while(!op.equals("2"));
        }
        if(!bib.isEmpty()){
            do{
                System.out.println("--------------- Bem vindo à secção Bibliotecas ---------------");
                System.out.println("1) - Selecionar");
                System.out.println("2) - Adicionar");
                System.out.println("3) - Eliminar");
                System.out.println("4) - Sair");
                System.out.print("Selecione uma opção: ");
                op=ler.next();

                switch(op){
                    case "1":
                        String biblioteca = bib.escolherBibliotecas();
                        if(!biblioteca.equals("")) menu(biblioteca);
                        break;

                    case "2":
                        bib.adicionarBiblioteca();
                        break;

                    case "3":
                        String biblioteca = bib.escolherBibliotecas();
                        if(!biblioteca.equals("")) bib.eliminarBiblioteca(biblioteca);
                        if(bib.isEmpty()){
                            return true;
                        }
                        break;

                    case "4":
                        System.out.println("A sair ...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente");
                        break;
                }
            }while(!op.equals("4"));
        }
        return false;
    }

    public static void main(String[] args) {
         do{}while(menuBibliotecas());
    }
}