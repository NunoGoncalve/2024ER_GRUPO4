import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Emprestimos {
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    //função para listar os empréstimos guardados no array

    public void listarEmprestimos(){
        for (Emprestimo emp : emprestimos) {
            emp.formataEmprestimoE();
        }
    }

    public boolean isEmpty(){
        return emprestimos.isEmpty();
    }

    public boolean empAtivos(){
        for (Emprestimo emp : emprestimos) {
            if(emp.getDataFim()==null) return true;
        }
        return false;
    }

    public void listarEmprestimosAtivos(){
        for (Emprestimo emp : emprestimos) {
            if(emp.getDataFim()==null) emp.formataEmprestimoE();
        }
    }


    public void atualizarEmprestimo(Emprestimo emp) {
        Scanner ler = new Scanner(System.in);
        int resp;
        String codigo;
        Livro liv = new Livro();
        Livros livs = new Livros();
        Jornal_revista jr = new Jornal_revista();
        JornaisRevistas jrs = new JornaisRevistas();

        livs.lerLivros();
        jrs.ler_jornaisRevistas();

        if(!emp.getEmprestadosLivros().isEmpty()) emp.getEmprestadosLivros().listarLivros();
        if(!emp.getEmprestadosJornaisRevistas().isEmpty()) emp.getEmprestadosJornaisRevistas().listarJornaisRevistas();
        System.out.println("1) - Adicionar Livro/Jornal/Revista");
        System.out.println("2) - Remover Livro/Jornal/Revista");
        System.out.println("3) - Cancelar");
        System.out.println("Escolha uma opção: ");
        resp = ler.nextInt();


        boolean flag = false;
        if(resp==1) {
            do{
                System.out.print("Insira o ISBN ou ISSN desejado: ");
                codigo = ler.next();
                if(liv.verificaIsbn(codigo)){
                    liv=livs.procuraLivro(codigo);
                    if(!liv.isEmpty() && liv.getLivre()){
                        emp.getEmprestadosLivros().adicionarLivro(liv);
                        liv.setLivre(false);
                        livs.guardarLivros();
                        flag=true;
                    }else System.out.println("Livro não disponível");
                }else if (jr.validarISSN(codigo)){
                    jr=jrs.procuraJornalRevista(codigo);
                    if(!jr.isEmpty() && jr.getLivre()){
                        emp.getEmprestadosJornaisRevistas().adicionarJornalRevista(jr);
                        jr.setLivre(false);
                        jrs.guardarJornaisRevistas();
                        flag=true;
                    }else System.out.println("Jornal/Revista não disponível");
                }else{
                    System.out.println("ISBN/ISSN inválido");
                }
            }while(!flag);
        }else if(resp==2) {
            do{
                System.out.print("Insira o ISBN ou ISSN desejado: ");
                codigo = ler.next();
                if(liv.verificaIsbn(codigo)){
                    if(emp.getEmprestadosLivros().eliminarLivro(codigo)){
                        liv.setLivre(true);
                        livs.guardarLivros();
                        flag=true;
                    }
                }else if (jr.validarISSN(codigo)){
                    if(emp.getEmprestadosJornaisRevistas().eliminarJornalRevista(codigo)){
                        jr.setLivre(true);
                        jrs.guardarJornaisRevistas();
                        flag=true;
                    }
                }else{
                    System.out.println("ISBN/ISSN inválido");
                }
            }while(!flag);
        }else {
            System.out.println("A cancelar... ");
        }
    }
    //função para verificar se o utente existe

    public void registarEmprestimo(){
        Emprestimo emp = new Emprestimo();
        emprestimos.add(emp.criarEmprestimo(emprestimos.size()+1));
    }


    public Emprestimo procurarEmprestimo(int cod) {
        Emprestimo empFlag = new Emprestimo();
        for (Emprestimo emp : emprestimos) {
            if (emp.getNum() == cod) {
                return emp;
            }
        }
        return empFlag;
    }

    public void menuPesquisaEmprestimo() {
        Scanner ler = new Scanner(System.in);
        String op;
        System.out.println("1) - Pesquisar empréstimos num intervalo de datas");
        System.out.println("2) - Pesquisar os empréstimos de um utente num intervalo de datas");
        System.out.println("3) - Pesquisar os empréstimos de um utente");
        System.out.println("4) - Cancelar");
        System.out.print("Insira a sua opção: ");
        op=ler.next();
        switch (op) {
            case "1":
                pesquisarEmprestimo(pedeDatas());
                break;
            case "2":
                String nifString;
                int nif;
                Utente ut = new Utente();
                do{
                    System.out.print("Insira o NIF de um utente: ");
                    nifString = ler.next();
                }while(!ut.verificaNif(nifString));
                nif=Integer.parseInt(nifString);
                pesquisarEmprestimo(nif, pedeDatas());
                break;

            case "3":
                pesquisarEmprestimo();
                break;
            case "4":
                System.out.println("A cancelar... ");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente!");
                break;
        }
    }

    public void pesquisarEmprestimo(Date[] datas) {
        for (Emprestimo emp : emprestimos) {
            if(emp.getDataInicio().compareTo(datas[0])>=0 && emp.getDataFimPrev().compareTo(datas[1])<=0){
                emp.formataEmprestimoE();
            }
        }
    }
    public void pesquisarEmprestimo(int nif, Date[] datas) {
        for (Emprestimo emp : emprestimos) {
            if(emp.getUtente().getNif() == nif && emp.getDataInicio().compareTo(datas[0])>=0 && emp.getDataFimPrev().compareTo(datas[1])<=0){
                emp.formataEmprestimoE();
            }
        }
    }

    public void pesquisarEmprestimo() {
        Scanner ler = new Scanner(System.in);
        Utente ut = new Utente();
        int nif;
        String nifString;
        do{
            System.out.print("Insira o NIF de um utente: ");
            nifString = ler.next();
        }while(!ut.verificaNif(nifString));
        nif=Integer.parseInt(nifString);
        for (Emprestimo emp : emprestimos) {
            if(emp.getUtente().getNif() == nif){
                emp.formataEmprestimoE();
            }
        }
    }

    public void tempoMedio(Date[] datas) {
        double tempoMedio=0;
        int i=0;
        for (Emprestimo emp : emprestimos) {
            if(emp.getDataInicio().compareTo(datas[0])>=0 && emp.getDataFimPrev().compareTo(datas[1])<=0){
                Calendar calendar = Calendar.getInstance();
                int dataInicio, dataFim;
                calendar.setTime(emp.getDataInicio());
                dataInicio = calendar.get(Calendar.DAY_OF_MONTH);

                calendar.setTime(emp.getDataFim());
                dataFim = calendar.get(Calendar.DAY_OF_MONTH);
                tempoMedio+=dataFim-dataInicio;
                i++;
            }
        }
        tempoMedio/=i;
        System.out.println("O tempo médio é "+tempoMedio);
    }

    /**
     Função criada para devolver um empréstimo à biblioteca
     */
    public void devolverEmprestimo() {
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String dataDev;
        listarEmprestimosAtivos();
        System.out.print("Insira o número do empréstimo: ");
        int numEmprestimo = ler.nextInt();
        Emprestimo emp = procurarEmprestimo(numEmprestimo);
        if(emp.isEmpty()) {
            System.out.println("Empréstimo não encontrado");
        }else {
            boolean flag = false;
            do{
                System.out.print("Empréstimo encontrado, insira a data de devolução (dd/mm/yyyy): ");
                dataDev = ler.next();
                if(emp.verificarDatas(dataDev)){
                    flag = true;
                }else System.out.println("Formato incorreto!");
            }while(!flag);

            try {
                emp.setDataFim(formatDate.parse(dataDev));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Livros livsEmp = emp.getEmprestadosLivros();
            Livros livs = new Livros();
            livs.lerLivros();

            for(int i=0; i< livsEmp.size();i++){
                livs.procuraLivro(livsEmp.getLivros().get(i).getISBN()).setLivre(true);
            }
            livs.guardarLivros();

        }

    }

    public Date[] pedeDatas(){
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String strDataInicio, strDataFim="";
        Date dataInicio, dataFim;
        Emprestimo emp = new Emprestimo();
        boolean flag = false;
        do{
            System.out.print("Data de inicio: ");
            strDataInicio = ler.next();
            if(emp.verificarDatas(strDataInicio)){
                System.out.print("Data de fim: ");
                strDataFim = ler.next();
                 if(emp.verificarDatas(strDataFim)) {
                     flag = true;
                 }
            }
            if (!flag) System.out.println("Formato incorreto!");
        }while(!flag);
        try {
            dataInicio = formatDate.parse(strDataInicio);
            dataFim = formatDate.parse(strDataFim);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new Date[]{dataInicio, dataFim};
    }

    public void totalEmprestimos () {
        Scanner ler = new Scanner(System.in);
        int total = 0;
        String op;
        do{
            System.out.println("1) - Total de empréstimos");
            System.out.println("2) - Total de empréstimos num intervalo de datas");
            System.out.print("Escolha uma opção: ");
            op=ler.next();
            switch (op) {
                case "1":
                    System.out.println("O número total de empréstimos é: "+emprestimos.size());
                    break;
                case "2":
                    Date[] datas=pedeDatas();
                    for (Emprestimo emp: emprestimos) {
                        if (emp.getDataInicio().compareTo(datas[0]) >= 0 && emp.getDataInicio().compareTo(datas[1]) <= 0) {
                            total ++;
                        }
                    }
                    System.out.println("O número total de empréstimos no intervalo inserido é: "+total);
                    break;
                default:
                    System.out.println("Opção invalida! Tente novamente!");
                    break;
            }
        }while(!op.matches("[1|2]"));
    }

   /** Metodo diasAtraso
    *  Apresenta uma lista dos utentes cuja devolução dos itens emprestados tenha um
    atraso superior a um número de dias a inserir pelo utilizador;*/
    public void diasAtraso(){
        Calendar calendar = Calendar.getInstance();
        Scanner ler = new Scanner(System.in);
        int dataFimPrev, dataFim, atraso;
        System.out.print("Insira o número de dias em atraso desejado: ");
        int numDias = ler.nextInt();
        for(Emprestimo emp: emprestimos) {

            calendar.setTime(emp.getDataFim());
            dataFim = calendar.get(Calendar.DAY_OF_MONTH);

            calendar.setTime(emp.getDataFimPrev());
            dataFimPrev = calendar.get(Calendar.DAY_OF_MONTH);

            atraso=dataFim-dataFimPrev;
            if(atraso==numDias) {
                System.out.println("----- Utente -----");
                System.out.println(emp.getUtente().formataUtenteE());
                System.out.println();
            }
        }
    }

    public void lerEmprestimos() {
        int n_linhas = contLinhas();
        if(n_linhas!=0) setEmprestimos(lerFicheiro(n_linhas));
    }

    private String[] lerFicheiro(int n_linhas){
        int i=0;
        String[] livros = new String[n_linhas];

        File myfile = new File("emprestimos.txt");
        try {
            Scanner myReader = new Scanner(myfile);
            while (myReader.hasNextLine()) {
                livros[i] = myReader.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return livros;
    }

    private void setEmprestimos(String[] emprestimos) {
        for (String s : emprestimos) {
            Emprestimo emp = new Emprestimo(s);
            this.emprestimos.add(emp);
        }
    }

    public void guardarEmprestimos(){
        try {
            FileWriter writer = new FileWriter("emprestimos.txt");
            for (Emprestimo emp : this.emprestimos) {
                if(this.emprestimos.getFirst()==emp) writer.write(emp.formataEmprestimoF());
                else writer.write("\n"+emp.formataEmprestimoF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int contLinhas(){
        int i=0;
        File myfile = new File("emprestimos.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader("emprestimos.txt"))) {
                    while (reader.readLine() != null) i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return i;
    }
}

