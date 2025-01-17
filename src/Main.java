import java.util.Scanner;

public class Main {

    public static void menuJornaisRevistas(){
        int op;
        Scanner ler = new Scanner(System.in);
        JornaisRevistas jornaisRevistas =new JornaisRevistas();
        jornaisRevistas.ler_jornaisRevistas();
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
                    jornaisRevistas.eliminarJornalRevista(jornaisRevistas.pedeIssn());
                    break;

                case 6:
                    jornaisRevistas.guardarJornaisRevistas();
                    jornaisRevistas.limparJornaisRevistas();
                    System.out.println("A sair do menu livro...");
                    break;
                default:
                    System.out.println("Opção incorreta! Tente novamente");
                    break;
            }
        }while (op != 6);
    }

    public static void menuEmprestimos() {
        Scanner ler = new Scanner(System.in);
        Emprestimos emprestimos = new Emprestimos();
        emprestimos.lerEmprestimos();
        String opc;
        if(emprestimos.isEmpty()){
            do {
                System.out.println("1 - Adicionar");
                System.out.println("2 - Sair");
                System.out.print("Selecione uma opção: ");
                opc = ler.next();
                switch (opc) {
                    case "1":
                        emprestimos.registarEmprestimo();
                        break;

                    case "2":
                        emprestimos.guardarEmprestimos();
                        System.out.println("A sair ...");
                        break;
                    default:
                        System.out.println("Opção incorreta! Tente novamente");
                        break;
                }
            } while (!opc.equals("2"));
        }
        if(!emprestimos.empAtivos()){
            do {
                System.out.println("1 - Listar");
                System.out.println("2 - Registar ");
                System.out.println("3 - Pesquisar ");
                System.out.println("4 - Total de empréstimos");
                System.out.println("5 - Tempo médio");
                System.out.println("6 - Mostrar empréstimos com atraso");
                System.out.println("7 - Sair");
                System.out.print("Selecione uma opção: ");
                opc = ler.next();
                switch (opc) {
                    case "1":
                        emprestimos.listarEmprestimos();
                        break;

                    case "2":
                        emprestimos.registarEmprestimo();
                        emprestimos.guardarEmprestimos();
                        opc="7";
                        break;

                    case "3":
                        emprestimos.menuPesquisaEmprestimo();
                        break;

                    case "4":
                        emprestimos.totalEmprestimos();
                        break;

                    case "5":
                        emprestimos.tempoMedio(emprestimos.pedeDatas());
                        break;

                    case "6":
                        emprestimos.diasAtraso();
                        break;
                    case "7":
                        emprestimos.guardarEmprestimos();
                        System.out.println("A sair ...");
                        break;

                    default:
                        System.out.println("Opção incorreta! Tente novamente");
                        break;
                }
            } while (!opc.equals("7"));
        }
        if(emprestimos.empAtivos()){
            do {
                System.out.println("1 - Listar");
                System.out.println("2 - Registar");
                System.out.println("3 - Pesquisar ");
                System.out.println("4 - Atualizar");
                System.out.println("5 - Devolver");
                System.out.println("6 - Total de empréstimos");
                System.out.println("7 - Tempo médio");
                System.out.println("8 - Mostrar empréstimos com atraso");
                System.out.println("9 - Sair");
                System.out.print("Selecione uma opção: ");
                opc = ler.next();
                switch (opc) {
                    case "1":
                        emprestimos.listarEmprestimos();
                        break;

                    case "2":
                        emprestimos.registarEmprestimo();
                        break;

                    case "3":
                        emprestimos.menuPesquisaEmprestimo();
                        break;

                    case "4":
                        System.out.print("Insira o código do empréstimo: ");
                        emprestimos.atualizarEmprestimo(emprestimos.procurarEmprestimo(ler.nextInt()));
                        break;

                    case "5":
                        emprestimos.devolverEmprestimo();
                        if(!emprestimos.empAtivos()){
                            emprestimos.guardarEmprestimos();
                            System.out.println("A sair ...");
                            opc="9";
                        }

                        break;

                    case "6":
                        emprestimos.totalEmprestimos();
                        break;

                    case "7":
                        emprestimos.tempoMedio(emprestimos.pedeDatas());
                        break;

                    case "8":
                        emprestimos.diasAtraso();
                        break;
                    case "9":
                        emprestimos.guardarEmprestimos();
                        System.out.println("A sair ...");
                        break;

                    default:
                        System.out.println("Opção incorreta! Tente novamente");
                        break;
                }
            } while (!opc.equals("9"));
        }
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
                System.out.print("Biblioteca vazia! Selecione uma opção: ");
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


    public static void menuUtente(){
        int escolha;
        Utentes uts = new Utentes();
        uts.lerUtentes();
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
                    int Nif;
                    System.out.print("Insira o NIF do Utente:  ");
                    Nif = ler.nextInt();
                    uts.pesquisarUtente(uts.procurarUtente(Nif));
                    break;

                case 4:
                    uts.atualizarUtente();
                    break;

                case 5:
                    uts.eliminarUtente();
                    break;

                case 6:
                    System.out.println("A sair do menu Utente....\n");
                    uts.guardarUtentes();
                    break;
                default:
                    System.out.println("ERRO!!  Escolha uma das opções");
                    break;
                }
        }while (escolha != 6);
    }


    public static void menu(){
        Scanner ler = new Scanner(System.in);
        String op;
        do{
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
                    menuLivros();
                    break;

                case "2":
                    menuJornaisRevistas();
                    break;

                case "3":
                    menuUtente();
                    break;

                case "4":
                    /**/
                    break;

                case "5":
                    menuEmprestimos();
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

    public static void main(String[] args) {
        menu();
    }
}