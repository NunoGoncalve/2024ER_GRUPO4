import java.io.*;
import java.text.DecimalFormat;
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


    public void adicionarAEmprestimo(Emprestimo emp, String biblioteca){
        Scanner ler = new Scanner(System.in);
        String codigo;
        Livro liv = new Livro();
        Livros livs = new Livros();
        Jornal_revista jr = new Jornal_revista();
        JornaisRevistas jrs = new JornaisRevistas();

        livs.lerLivros(biblioteca);
        jrs.ler_jornaisRevistas(biblioteca);

        boolean flag = false;
        do{
            System.out.print("Insira o ISBN ou ISSN desejado: ");
            codigo = ler.next();
            if(liv.verificaIsbn(codigo)){
                liv=livs.procuraLivro(codigo);
                if(!liv.isEmpty() && liv.getLivre()){
                    emp.getEmprestadosLivros().adicionarLivro(liv);
                    liv.setLivre(false);
                    livs.guardarLivros(biblioteca);
                    flag=true;
                }else System.out.println("Livro não disponível");
            }else if (jr.validarISSN(codigo)){
                jr=jrs.procuraJornalRevista(codigo);
                if(!jr.isEmpty() && jr.getLivre()){
                    emp.getEmprestadosJornaisRevistas().adicionarJornalRevista(jr);
                    jr.setLivre(false);
                    jrs.guardarJornaisRevistas(biblioteca);
                    flag=true;
                }else System.out.println("Jornal/Revista não disponível");
            }else{
                System.out.println("ISBN/ISSN inválido");
            }
        }while(!flag);

    }

    public void removerDeEmprestimo(Emprestimo emp, String biblioteca){
        Scanner ler = new Scanner(System.in);
        String codigo;
        Livro liv = new Livro();
        Livros livs = new Livros();
        Jornal_revista jr = new Jornal_revista();
        JornaisRevistas jrs = new JornaisRevistas();

        livs.lerLivros(biblioteca);
        jrs.ler_jornaisRevistas(biblioteca);

        boolean flag = false;
        do{
            System.out.print("Insira o ISBN ou ISSN desejado: ");
            codigo = ler.next();
            if(liv.verificaIsbn(codigo)){
                liv=livs.procuraLivro(codigo);
                if(!liv.isEmpty()){
                    if(emp.getEmprestadosLivros().getLivros().indexOf(liv)!=0){
                        emp.getEmprestadosLivros().eliminarLivro(codigo);
                        liv.setLivre(true);
                        livs.guardarLivros(biblioteca);
                        flag=true;
                    }else{
                        System.out.println("Não é possível eliminar o último livro do empréstimo");
                    }

                }
            }else if (jr.validarISSN(codigo)){
                jr=jrs.procuraJornalRevista(codigo);
                if(!jr.isEmpty()){
                    if(emp.getEmprestadosJornaisRevistas().getJornalRevistas().indexOf(jr)!=0) {
                        emp.getEmprestadosJornaisRevistas().eliminarJornalRevista(codigo);
                        jr.setLivre(true);
                        jrs.guardarJornaisRevistas(biblioteca);
                        flag = true;
                    }else{
                        System.out.println("Não é possível eliminar o último jornal/revista do empréstimo");
                    }
                }
            }else{
                System.out.println("ISBN/ISSN inválido");
            }
        }while(!flag);

    }

    public void menuAtualizarEmprestimo(String biblioteca) {
        Scanner ler = new Scanner(System.in);
        String cod;
        int intCod=0;
        Emprestimo emp = new Emprestimo();

        System.out.println();
        this.listarEmprestimosAtivos();
        do{
            System.out.print("Insira o código do empréstimo: ");
            cod=ler.next();
            if(!cod.matches("\\b")){
                System.out.println("Insira um número!");
            }
            else intCod=Integer.parseInt(cod);
        }while(intCod!=0);
        if(procurarEmprestimo(intCod).getDataFim()==null){
            intCod=Integer.parseInt(cod);
            emp = procurarEmprestimo(intCod);
        }else{
            System.out.println("Empréstimo devolvido! Não é possível alterar");
        }

        Livros livs = new Livros();
        JornaisRevistas jrs = new JornaisRevistas();

        livs.lerLivros(biblioteca);
        jrs.ler_jornaisRevistas(biblioteca);

        if(!emp.getEmprestadosLivros().isEmpty()) emp.getEmprestadosLivros().listarLivros();
        if(!emp.getEmprestadosJornaisRevistas().isEmpty()) emp.getEmprestadosJornaisRevistas().listarJornaisRevistas();

        do{
            System.out.println("1) - Adicionar Livro/Jornal/Revista");
            System.out.println("2) - Remover Livro/Jornal/Revista");
            System.out.println("3) - Cancelar");
            System.out.print("Escolha uma opção: ");
            cod = ler.next();
            switch (cod){
                case "1":
                    adicionarAEmprestimo(emp, biblioteca);
                    break;
                case "2":
                    removerDeEmprestimo(emp, biblioteca);
                    break;
                case "3":
                    System.out.println("A cancelar...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente");
                    break;
            }
        }while(!cod.equals("3"));

    }
    //função para verificar se o utente existe

    public void registarEmprestimo(String biblioteca){
        Emprestimo emp = new Emprestimo();
        this.emprestimos.add(emp.criarEmprestimo(this.emprestimos.size()+1, biblioteca));
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

    /** Metodo pedeDatas
     * Pede ao utilizador duas datas, faz as verificações necessárias
     * @return das duas datas inseridas */
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

    public void menuPesquisaEmprestimo() {
        Scanner ler = new Scanner(System.in);
        String op;
        System.out.println("1)  Pesquisar empréstimos num intervalo de datas");
        System.out.println("2)  Pesquisar os empréstimos de um utente num intervalo de datas");
        System.out.println("3)  Pesquisar os empréstimos de um utente");
        System.out.println("4)  Cancelar");
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
        double tempoMedio=0, i=0;
        DecimalFormat format = new DecimalFormat("0.00");
        for (Emprestimo emp : emprestimos) {
            if(emp.getDataInicio().compareTo(datas[0])>=0 && emp.getDataFimPrev().compareTo(datas[1])<=0){
                Calendar calendar = Calendar.getInstance();
                int dataInicio, dataFim;
                calendar.setTime(emp.getDataInicio());
                dataInicio = calendar.get(Calendar.DAY_OF_MONTH);

                calendar.setTime(emp.getDataFimPrev());
                dataFim = calendar.get(Calendar.DAY_OF_MONTH);
                tempoMedio+=dataFim-dataInicio;
                i++;
            }
        }
        tempoMedio/=i;
        System.out.println("O tempo médio é "+format.format(tempoMedio));
    }

    /** Metodo devolverEmprestimo
     * Pede ao utilizador o número do empréstimo, procura-o, quando encontrado pede a data de devolução,
     * define os livros/jornais/revistas emprestados como livres */
    public void devolverEmprestimo(String biblioteca) {
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String dataDev;
        listarEmprestimosAtivos();
        System.out.print("Insira o número do empréstimo: ");
        int numEmprestimo = ler.nextInt();
        Emprestimo emp = procurarEmprestimo(numEmprestimo);
        if(emp.isEmpty()) {
            System.out.println("Empréstimo não encontrado");
        }else if(emp.getDataFim()!=null){
            System.out.println("Empréstimo já devolvido!");
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
            livs.lerLivros(biblioteca);

            JornaisRevistas jrEmp = emp.getEmprestadosJornaisRevistas();
            JornaisRevistas jrs = new JornaisRevistas();
            jrs.ler_jornaisRevistas(biblioteca);

            for(int i=0; i< livsEmp.size();i++){
                livs.procuraLivro(livsEmp.getLivros().get(i).getISBN()).setLivre(true);
            }
            for(int i=0; i< jrEmp.size();i++){
                jrs.procuraJornalRevista(jrEmp.getJornalRevistas().get(i).getISSN()).setLivre(true);
            }
            livs.guardarLivros(biblioteca);
            jrs.guardarJornaisRevistas(biblioteca);

        }

    }



    /** Metodo totalEmprestimos
     * Apresenta duas opções ao utilizador
     * 1 -> mostra o total de empréstimos
     * 2 -> mostra o total de empréstimos num intervalo de datas
     */
    public void totalEmprestimos () {
        Scanner ler = new Scanner(System.in);
        int total = 0;
        String op;
        do{
            System.out.println("1)  Total de empréstimos");
            System.out.println("2)  Total de empréstimos num intervalo de datas");
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

    /** Metodo lerEmprestimos
     * Define a variável nLinhas com o resultado da função contLinhas;
     * Se o nLinhas for diferente de 0 chama a função setEmpréstimos com o resultado da função lerFicheiro como parametro*/
    public void lerEmprestimos(String biblioteca) {
        int nLinhas = contLinhas(biblioteca);
        if(nLinhas!=0) setEmprestimos(lerFicheiro(nLinhas, biblioteca),biblioteca);
    }

    /** Metodo lerFicheiro
     * @param nLinhas  recebe como parametro o número de linhas que o ficheiro têm e cria um array de strings desse tamanho,
     * de seguida lê as linhas do ficheiro e guarda-as num array retornando o mesmo */
    private String[] lerFicheiro(int nLinhas, String biblioteca) {
        int i=0;
        String[] emprestimos = new String[nLinhas];

        File myfile = new File(biblioteca+"/emprestimos.txt");
        try {
            Scanner myReader = new Scanner(myfile);
            while (myReader.hasNextLine()) {
                emprestimos[i] = myReader.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }

    /*public void maisRequisitado(Date[] datas){
        Livros livros = new Livros();
        livros.lerLivros();
        int[] item = new int[livros.size()];
        Livros livrosValidos = new Livros();
        ArrayList<Livros> emps = new ArrayList<>();

        int max = 0;
        Livro aux= null;
        for(Emprestimo emp: this.emprestimos){
            if(emp.getDataInicio().compareTo(datas[0]) >= 0 && emp.getDataInicio().compareTo(datas[1]) <= 0) {
                emps.set(this.emprestimos.indexOf(emp),emp.getEmprestadosLivros());
                for(Livros livs : emps){
                    for(Livro liv : livs.getLivros()){
                        item[emp.getEmprestadosLivros().getLivros().indexOf(liv)] = item[emp.getEmprestadosLivros().getLivros().indexOf(liv)]+1;
                        if(item[emp.getEmprestadosLivros().getLivros().indexOf(liv)]>max){
                            max = item[emp.getEmprestadosLivros().getLivros().indexOf(liv)];
                            aux = liv;
                        }
                    }
                }
            }
        }

        System.out.println("O item mais requisitado foi "+aux);
    }*/
    
    /** Metodo setEmprestimos
     * @param emprestimos recebe como parametro um array de strings
     * Para cada string no array emprestimos cria um emprestimo e adiciona-o ao array conforme a informação encontrada no array */
    private void setEmprestimos(String[] emprestimos, String biblioteca) {
        for (String s : emprestimos) {
            Emprestimo emp = new Emprestimo(s, biblioteca);
            this.emprestimos.add(emp);
        }
    }

    /** Metodo guardarEmprestimos
     * Guarda o conteúdo do array no ficheiro verificando se é a primeira linha ou as seguintes */
    public void guardarEmprestimos(String biblioteca){
        try {
            FileWriter writer = new FileWriter(biblioteca+"/emprestimos.txt");
            for (Emprestimo emp : this.emprestimos) {
                if(this.emprestimos.getFirst()==emp) writer.write(emp.formataEmprestimoF());
                else writer.write("\n"+emp.formataEmprestimoF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Metodo contLinhas
     * Cria o ficheiro caso este não exista e retorna o número de linhas a 0.
     * Caso o ficheiro exista conta as linhas presentes no ficheiro
     * @return i número de linhas*/
    private int contLinhas(String biblioteca){
        int i=0;
        File myfile = new File(biblioteca+"/emprestimos.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader(biblioteca+"/emprestimos.txt"))) {
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

