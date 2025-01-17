import java.util.Scanner;

public class Main {

    public static boolean menuReserva(String biblioteca) {
        Scanner ler = new Scanner(System.in);
        Reservas reservas = new Reservas();
        reservas.lerReservas(biblioteca);
        Livros livros = new Livros();
        livros.lerLivros(biblioteca);
        JornaisRevistas jornais = new JornaisRevistas();
        String op;
        if(livros.contLivrosLivres()==0 && jornais.contJornaisRevistasLivres()==0 && reservas.isEmpty()){
            System.out.println("Não existem reservas! Todos os livros/jornais/revistas estão ocupados!");
            return false;
        }
        if(reservas.isEmpty()){
            do{
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Reservas ---------------");
                System.out.println("1)  Adicionar Reservas");
                System.out.println("2)  Sair");
                System.out.print("Sem reservas! Escolha uma opção: ");
                op=ler.next();
                switch (op) {
                    case "1":
                        reservas.adicionarReserva(biblioteca);
                        reservas.guardarReservas(biblioteca);
                        op="2";
                        break;
                    case "2":
                        reservas.guardarReservas(biblioteca);
                        System.out.println("A sair ...");
                        break;
                    default:
                        System.out.println("Opção incorreta!");
                        break;
                }
            }while(!op.equals("2"));
        }
        if(livros.contLivrosLivres()==0 && jornais.contJornaisRevistasLivres()==0){
            do{
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Reservas ---------------");
                System.out.println("1)  Exibir Reservas");
                System.out.println("2)  Mostrar utentes");
                System.out.println("3)  Pesquisar ");
                System.out.println("4)  Remover Reserva");
                System.out.println("5)  Alterar Reserva");
                System.out.println("6)  Transformar Reserva em Empréstimo");
                System.out.println("7)  Sair");
                System.out.print("Todos os livros estão ocupados! Escolha uma opção: ");
                op=ler.next();
                switch (op) {
                    case "1":
                        reservas.exibirReservas();  // Exibe todas as reservas anonimamente
                        break;
                    case "2":
                        reservas.listarUtentes(); // Exibe todas as reservas com NIF
                        break;
                    case "3":
                        reservas.menuPesquisaReserva(); // Exibe todas as reservas com NIF
                        break;
                    case "4":
                        reservas.exibirReservas();
                        System.out.print("Digite o número da reserva para remover: ");
                        int numeroRemover = ler.nextInt();
                        reservas.removerReserva(numeroRemover, biblioteca);  // Remove a reserva
                        if(reservas.isEmpty()){
                            reservas.guardarReservas(biblioteca);
                            op="6";
                        }
                        break;
                    case "5":
                        reservas.menuAtualizarReserva(1,biblioteca);
                        break;
                    case "6":
                        reservas.transformaReservaEmprestimo(biblioteca);
                        break;
                    case "7":
                        reservas.guardarReservas(biblioteca);
                        System.out.println("A sair ...");
                        break;
                    default:
                        System.out.println("Opção incorreta!");
                        break;
                }
            }while(!op.equals("6"));
        }
        if(!reservas.isEmpty()){
            do{
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Reservas ---------------");
                System.out.println("1)  Adicionar Reserva");
                System.out.println("2)  Exibir Reservas");
                System.out.println("3)  Alterar Reserva");
                System.out.println("4)  Mostrar utentes");
                System.out.println("5)  Pesquisar ");
                System.out.println("6)  Remover Reserva");
                System.out.println("7)  Transformar Reserva em Empréstimo");
                System.out.println("8)  Sair");
                System.out.print("Todos os livros estão ocupados! Escolha uma opção: ");
                op=ler.next();
                switch (op) {
                    case "1":
                        reservas.adicionarReserva(biblioteca);
                        break;

                    case "2":
                        reservas.exibirReservas();
                        break;

                    case "3":
                        reservas.menuAtualizarReserva(2, biblioteca);
                        break;
                    case "4":
                        reservas.listarUtentes();
                        break;
                    case "5":
                        reservas.menuPesquisaReserva();
                        break;
                    case "6":
                        reservas.exibirReservas();
                        System.out.print("Digite o número da reserva para remover: ");
                        int numeroRemover = ler.nextInt();
                        reservas.removerReserva(numeroRemover, biblioteca);
                        if(reservas.isEmpty()){
                            reservas.guardarReservas(biblioteca);
                            return true;
                        }
                        break;
                    case "7":
                        reservas.transformaReservaEmprestimo(biblioteca);
                        break;
                    case "8":
                        reservas.guardarReservas(biblioteca);
                        System.out.println("A sair ...");
                        break;
                    default:
                        System.out.println("Opção incorreta!");
                        break;
                }
            }while(!op.equals("8"));
        }
        return false;
    }

    public static void menuJornaisRevistas(String biblioteca){
        int op;
        Scanner ler = new Scanner(System.in);
        JornaisRevistas jornaisRevistas =new JornaisRevistas();
        jornaisRevistas.lerJornaisRevistas(biblioteca);
        do {
            System.out.println();
            System.out.println("--------------- Bem vindo à secção Jornais/Revistas ---------------");
            System.out.println("1)  Listar");
            System.out.println("2)  Adicionar");
            System.out.println("3)  Pesquisar");
            System.out.println("4)  Atualizar");
            System.out.println("5)  Eliminar");
            System.out.println("6)  Sair");
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
                    jornaisRevistas.guardarJornaisRevistas(biblioteca);
                    jornaisRevistas.limparJornaisRevistas();
                    System.out.println("A sair do menu livro...");
                    break;
                default:
                    System.out.println("Opção incorreta! Tente novamente");
                    break;
            }
        }while (op != 6);
    }

    public static boolean menuEmprestimos(String biblioteca) {
        Scanner ler = new Scanner(System.in);
        Emprestimos emprestimos = new Emprestimos();
        emprestimos.lerEmprestimos(biblioteca);
        String opc;
        if(emprestimos.isEmpty()){
            do {
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Empréstimos ---------------");
                System.out.println("1)  Adicionar");
                System.out.println("2)  Sair");
                System.out.print("Sem empréstimos! Selecione uma opção: ");
                opc = ler.next();
                switch (opc) {
                    case "1":
                        emprestimos.registarEmprestimo(biblioteca);
                        emprestimos.guardarEmprestimos(biblioteca);
                        opc="2";
                        break;

                    case "2":
                        emprestimos.guardarEmprestimos(biblioteca);
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
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Empréstimos ---------------");
                System.out.println("1)  Listar");
                System.out.println("2)  Registar ");
                System.out.println("3)  Pesquisar ");
                System.out.println("4)  Total de empréstimos");
                System.out.println("5)  Tempo médio");
                System.out.println("6)  Mostrar empréstimos com atraso");
                System.out.println("7)  Sair");
                System.out.print("Selecione uma opção: ");
                opc = ler.next();
                switch (opc) {
                    case "1":
                        emprestimos.listarEmprestimos();
                        break;

                    case "2":
                        emprestimos.registarEmprestimo(biblioteca);
                        emprestimos.guardarEmprestimos(biblioteca);
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
                        emprestimos.guardarEmprestimos(biblioteca);
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
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Empréstimos ---------------");
                System.out.println("1)  Listar");
                System.out.println("2)  Registar");
                System.out.println("3)  Listar utentes");
                System.out.println("4)  Pesquisar ");
                System.out.println("5)  Atualizar");
                System.out.println("6)  Devolver");
                System.out.println("7)  Total de empréstimos");
                System.out.println("8)  Tempo médio");
                System.out.println("9)  Mostrar empréstimos com atraso");
                System.out.println("0)  Sair");
                System.out.print("Selecione uma opção: ");
                opc = ler.next();
                switch (opc) {
                    case "1":
                        emprestimos.listarEmprestimos();
                        break;

                    case "2":
                        emprestimos.registarEmprestimo(biblioteca);
                        break;

                    case "3":
                        emprestimos.listarEmprestimos();
                        break;

                    case "4":
                        emprestimos.menuPesquisaEmprestimo();
                        break;

                    case "5":
                        emprestimos.menuAtualizarEmprestimo(biblioteca);
                        break;

                    case "6":
                        emprestimos.devolverEmprestimo(biblioteca);
                        if(!emprestimos.empAtivos()){
                            emprestimos.guardarEmprestimos(biblioteca);
                            return true;

                        }

                        break;

                    case "7":
                        emprestimos.totalEmprestimos();
                        break;

                    case "8":
                        emprestimos.tempoMedio(emprestimos.pedeDatas());
                        break;

                    case "9":
                        emprestimos.diasAtraso();
                        break;
                    case "0":
                        emprestimos.guardarEmprestimos(biblioteca);
                        System.out.println("A sair ...");
                        break;

                    default:
                        System.out.println("Opção incorreta! Tente novamente");
                        break;
                }
            } while (!opc.equals("0"));
        }
        return false;
    }

    public static boolean menuLivros(String biblioteca){
        int op;
        Scanner ler = new Scanner(System.in);
        Livros livros=new Livros();
        livros.lerLivros(biblioteca);
        if(livros.isEmpty()){
            do {
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Livros ---------------");
                System.out.println("1)  Adicionar");
                System.out.println("2)  Sair");
                System.out.print("Biblioteca vazia! Selecione uma opção: ");
                op = ler.nextInt();
                switch (op) {
                    case 1:
                        livros.adicionarLivro();
                        livros.guardarLivros(biblioteca);
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
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Livros ---------------");
                System.out.println("1)  Listar");
                System.out.println("2)  Adicionar");
                System.out.println("3)  Pesquisar");
                System.out.println("4)  Atualizar");
                System.out.println("5)  Eliminar");
                System.out.println("6)  Sair");
                System.out.print("Selecione uma opção: ");
                op = ler.nextInt();
                switch (op) {
                    case 1:
                        System.out.println();
                        livros.listarLivros();
                        System.out.println();
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
                        livros.eliminarLivro();
                        if(livros.isEmpty()){
                            livros.guardarLivros(biblioteca);
                            livros.limparLivros();
                            return true;
                        }
                        break;

                    case 6:
                        livros.guardarLivros(biblioteca);
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

    public static void menuUtente(String biblioteca) {
        String escolha;
        Utentes uts = new Utentes();
        uts.lerUtentes(biblioteca);
        if (uts.isEmpty()) {
            do {
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Utente ---------------");
                System.out.println("1) Adicionar");
                System.out.println("2) Sair");
                System.out.print("Não existem utentes! Como deseja prosseguir?  ");
                Scanner ler = new Scanner(System.in);
                escolha = ler.next();

                switch (escolha) {
                    case "1":
                        uts.adicionarUtente();
                        break;
                    case "2":
                        System.out.println("A sair do menu Utente....\n");
                        uts.guardarUtentes(biblioteca);
                        break;
                    default:
                        System.out.println("ERRO!!  Escolha uma das opções");
                        break;
                }
            } while (!escolha.equals("2"));
        }
        if (!uts.isEmpty()) {
            do {
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Utente ---------------");
                System.out.println("1) Listar");
                System.out.println("2) Adicionar");
                System.out.println("3) Pesquisar");
                System.out.println("4) Atualizar");
                System.out.println("5) Eliminar");
                System.out.println("6) Sair");
                System.out.print("Como deseja prosseguir?  ");
                Scanner ler = new Scanner(System.in);
                escolha = ler.next();
                switch (escolha) {
                    case "1":
                        uts.listarUtentes();
                        break;

                    case "2":
                        uts.adicionarUtente();
                        break;
                    case "3":
                        int Nif;
                        System.out.print("Insira o NIF do Utente:  ");
                        Nif = ler.nextInt();
                        uts.pesquisarUtente(uts.procurarUtente(Nif));
                        break;

                    case "4":
                        uts.atualizarUtente();
                        break;

                    case "5":
                        uts.eliminarUtente(biblioteca);
                        break;

                    case "6":
                        System.out.println("A sair do menu Utente....\n");
                        uts.guardarUtentes(biblioteca);
                        break;
                    default:
                        System.out.println("ERRO!!  Escolha uma das opções");
                        break;
                }
            }while (!escolha.equals("6"));
        }
    }

    public static void menu(String biblioteca){
        Scanner ler = new Scanner(System.in);
        String op;
        do{
            System.out.println();
            System.out.println("--------------- Bem vindo à biblioteca "+biblioteca.substring(biblioteca.indexOf("/") + 1)+" ---------------");
            System.out.println("1)  Livros");
            System.out.println("2)  Jornais / Revistas");
            System.out.println("3)  Utentes");
            System.out.println("4)  Reservas");
            System.out.println("5)  Empréstimos");
            System.out.println("6)  Sair");
            System.out.print("Selecione uma opção: ");
            op=ler.next();

            switch(op){
                case "1":
                    do{}while(menuLivros(biblioteca));
                    break;

                case "2":
                    menuJornaisRevistas(biblioteca);
                    break;

                case "3":
                    menuUtente(biblioteca);
                    break;

                case "4":
                    do{}while(menuReserva(biblioteca));
                    break;

                case "5":
                    do{}while(menuEmprestimos(biblioteca));
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
        Bibliotecas bibs = new Bibliotecas();
        String op, biblioteca;
        if(bibs.isEmpty()){
            do{
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Bibliotecas ---------------");
                System.out.println("1) - Adicionar");
                System.out.println("2) - Sair");
                System.out.print("Sem bibliotecas! Selecione uma opção: ");
                op=ler.next();
                switch(op){
                    case "1":
                        bibs.adicionarBiblioteca();
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
        if(!bibs.isEmpty()){
            do{
                System.out.println();
                System.out.println("--------------- Bem vindo à secção Bibliotecas ---------------");
                System.out.println("1) - Selecionar");
                System.out.println("2) - Adicionar");
                System.out.println("3) - Eliminar");
                System.out.println("4) - Sair");
                System.out.print("Selecione uma opção: ");
                op=ler.next();

                switch(op){
                    case "1":
                        biblioteca = bibs.escolherBibliotecas();
                        if(!biblioteca.isEmpty()) menu(biblioteca);
                        break;

                    case "2":
                        bibs.adicionarBiblioteca();
                        break;

                    case "3":
                        biblioteca = bibs.escolherBibliotecas();
                        if(!biblioteca.isEmpty()) bibs.eliminarBiblioteca(biblioteca);
                        if(bibs.isEmpty()){
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