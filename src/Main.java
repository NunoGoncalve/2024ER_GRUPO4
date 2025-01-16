import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    jornaisRevistas.eliminarJornalRevista();
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
        int opc;
        do {
            System.out.println("1 - Listar");
            System.out.println("2 - Adicionar");
            System.out.println("3 - Devolver");
            System.out.println("4 - Quantidade de livros numa data");
            System.out.println("5 - Mostrar empréstimos com atraso");
            System.out.println("6 - Sair");
            System.out.print("Selecione uma opção: ");
            opc = ler.nextInt();
            switch (opc) {
                case 1:
                    emprestimos.listarEmprestimos();
                    break;

                case 2:
                    emprestimos.registarEmprestimo();
                    break;

                case 3:
                    emprestimos.devolverEmprestimo();
                    break;

               case 4:
                   SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                   System.out.println("Data de inicio: ");
                   String strDataInicio = ler.next();
                   System.out.println("Data de fim: ");
                   String strdataFim = ler.next();
                   Date dataInicio = null;
                   try {
                       dataInicio = formatDate.parse(strDataInicio);

                   } catch (ParseException e) {
                       throw new RuntimeException(e);
                   }
                   Date dataFim = null;
                   try {
                       dataFim = formatDate.parse(strdataFim);
                   } catch (ParseException e) {
                       throw new RuntimeException(e);
                   }
                   System.out.println(emprestimos.intervaloData(dataInicio, dataFim));
                   break;

                case 5:
                    emprestimos.diasAtraso();
                    break;
                case 6:
                    emprestimos.guardarEmprestimos();
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
                    uts.pesquisarUtente(Nif);
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