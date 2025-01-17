import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservas {
    private List<Reserva> reservas = new ArrayList<>();

    /** Metodo adicionarReserva
     * Cria uma reserva e adiciona-a ao arraylist reservas
     * @param biblioteca -> biblioteca onde se encontram as reservas*/
    public void adicionarReserva(String biblioteca) {
        Reserva res = new Reserva();
        this.reservas.add(res.criarReserva(this.reservas.size()+1, biblioteca));
    }

    /** Metodo lerReservas
     * @param biblioteca -> biblioteca onde se encontram as reservas */
    public void lerReservas(String biblioteca) {
        int nLinhas = contLinhas(biblioteca);
        if(nLinhas!=0) setReservas(lerFicheiro(nLinhas, biblioteca), biblioteca);
    }

    /** Metodo setReservas
     * @param biblioteca -> biblioteca onde se encontram as reservas
     * @param reservas recebe como parametro um array de strings
     * Para cada string no array reservas cria um reservas e adiciona-o ao array conforme a informação encontrada no array */
    private void setReservas(String[] reservas, String biblioteca) {
        for (String s : reservas) {
            Reserva res = new Reserva(s, biblioteca);
            this.reservas.add(res);
        }
    }

    /** Metodo exibirReservas*
     * Mostra todas as reservas */
    public void exibirReservas() {
        for (Reserva reserva : reservas) {
            reserva.formataReservaE();
        }
    }

    /** Metodo pedeDatas
     * Pede ao utilizador duas datas, faz as verificações necessárias
     * @return das duas datas inseridas */
    public Date[] pedeDatas(){
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String strDataInicio, strDataFim="";
        Date dataInicio, dataFim;
        Reserva res = new Reserva();
        boolean flag = false;
        do{
            System.out.print("Data de inicio: ");
            strDataInicio = ler.next();
            if(res.verificaData(strDataInicio)){
                System.out.print("Data de fim: ");
                strDataFim = ler.next();
                if(res.verificaData(strDataFim)) {
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

    /** Metodo listarUtentes
     * lista os utentes com reservas*/
    public void listarUtentes(){
        for (Reserva reserva : reservas) {
            reserva.getUtente().formataUtenteE();
        }
    }

    /** Metodo procurarReserva
     * Procura uma reserva pelo número
     * @return da reserva encontrada ou reserva vazia caso não encontre*/
    public Reserva procurarReserva(int cod) {
        Reserva empFlag = new Reserva();
        for (Reserva res : reservas) {
            if (res.getNum() == cod) {
                return res;
            }
        }
        return empFlag;
    }

    /** Metodo menuPesquisaReserva
     * Apresenta o menu de pesquisae redireciona o utilizador para a função escolihda*/
    public void menuPesquisaReserva() {
        Scanner ler = new Scanner(System.in);
        String op;
        System.out.println("1)  Pesquisar reservas por número");
        System.out.println("2)  Pesquisar reservas num intervalo de datas");
        System.out.println("3)  Pesquisar as reservas de um utente num intervalo de datas");
        System.out.println("4)  Pesquisar as reservas de um utente");
        System.out.println("5)  Cancelar");
        System.out.print("Insira a sua opção: ");
        op=ler.next();
        switch (op) {
            case "1":
                System.out.println("Insira o número da reserva: ");
                int num = ler.nextInt();
                procurarReserva(num).formataReservaE();
                break;

            case "2":
                pesquisarReservasData(pedeDatas());
                break;
            case "3":
                String nifString;
                int nif;
                Utente ut = new Utente();
                do{
                    System.out.print("Insira o NIF de um utente: ");
                    nifString = ler.next();
                }while(!ut.verificaNif(nifString));
                nif=Integer.parseInt(nifString);
                pesquisarReservaUtenteData(nif, pedeDatas());
                break;

            case "4":
                pesquisarReservaUtente();
                break;
            case "5":
                System.out.println("A cancelar... ");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente!");
                break;
        }
    }

    /** Metodo pesquisarReservasData
     * Pesquisa as reservas num intervalo de datas*/
    public void pesquisarReservasData(Date[] datas) {
        for (Reserva res : reservas) {
            if(res.getDataInicio().compareTo(datas[0])>=0 && res.getDataFim().compareTo(datas[1])<=0){
                res.formataReservaE();
            }
        }
    }

    /** Metodo pesquisarReserva
     * Pesquisa as reservas de um utente num intervalo de datas*/
    public void pesquisarReservaUtenteData(int nif, Date[] datas) {
        for (Reserva res : reservas) {
            if(res.getUtente().getNif() == nif && res.getDataInicio().compareTo(datas[0])>=0 && res.getDataFim().compareTo(datas[1])<=0){
                res.formataReservaE();
            }
        }
    }

    /** Metodo pesquisarReserva
     * Pesquisa as reservas de um utente*/
    public void pesquisarReservaUtente() {
        Scanner ler = new Scanner(System.in);
        Utente ut = new Utente();
        int nif;
        String nifString;
        do{
            System.out.print("Insira o NIF de um utente: ");
            nifString = ler.next();
        }while(!ut.verificaNif(nifString));
        nif=Integer.parseInt(nifString);
        for (Reserva res : reservas) {
            if(res.getUtente().getNif() == nif){
                res.formataReservaE();
            }
        }
    }

    /** Metodo isEmpty
     * @return se o arraylist está vazio ou não */
    public boolean isEmpty(){
        return reservas.isEmpty();
    }


    /** Metodo removerReserva
     * @param numero -> numero da reserva
     * @param biblioteca -> biblioteca onde se encontra a reserva*/
    public void removerReserva(int numero, String biblioteca) {
        Reserva res = procurarReserva(numero);
        if (!res.isEmpty()) {
            Livros livsEmp = res.getLivrosReservados();
            Livros livs = new Livros();
            livs.lerLivros(biblioteca);

            JornaisRevistas jrEmp = res.getJornaisRevistasReservados();
            JornaisRevistas jrs = new JornaisRevistas();
            jrs.lerJornaisRevistas(biblioteca);

            for(int i=0; i< livsEmp.size();i++){
                livs.procuraLivro(livsEmp.getLivros().get(i).getISBN()).setLivre(true);
            }
            for(int i=0; i< jrEmp.size();i++){
                jrs.procuraJornalRevista(jrEmp.getJornalRevistas().get(i).getISSN()).setLivre(true);
            }
            livs.guardarLivros(biblioteca);
            jrs.guardarJornaisRevistas(biblioteca);
            this.reservas.remove(res);
            System.out.println("Reserva #" + String.format("%03d", numero) + " removida com sucesso.");
        } else {
            System.out.println("Número de reserva inválido.");
        }
    }


    /** Metodo guardarReservas
     * @param biblioteca -> biblioteca onde guardar o ficheiro
     * Guarda o conteúdo do array no ficheiro verificando se é a primeira linha ou as seguintes */
    public void guardarReservas(String biblioteca) {
        try {
            FileWriter writer = new FileWriter(biblioteca+"/reservas.txt");
            for (Reserva res : this.reservas) {
                if(this.reservas.getFirst()==res) writer.write(res.formataReservaF());
                else writer.write("\n"+res.formataReservaF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Metodo lerFicheiro
     * @param biblioteca -> biblioteca onde o ficheiro se encontra
     * @param nLinhas  recebe como parametro o número de linhas que o ficheiro têm e cria um array de strings desse tamanho,
     * de seguida lê as linhas do ficheiro e guarda-as no array reservas
     * @return do array reservas */
    private String[] lerFicheiro(int nLinhas, String biblioteca){
        int i=0;
        String[] reservas = new String[nLinhas];

        File myfile = new File(biblioteca+"/reservas.txt");
        try {
            Scanner myReader = new Scanner(myfile);
            while (myReader.hasNextLine()) {
                reservas[i] = myReader.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    /** Metodo contLinhas
     * @param biblioteca -> biblioteca onde se encontra o ficheiro
     * Cria o ficheiro caso este não exista e retorna o número de linhas a 0.
     * Caso o ficheiro exista conta as linhas presentes no ficheiro
     * @return i número de linhas*/
    private int contLinhas(String biblioteca){
        int i=0;
        File myfile = new File(biblioteca+"/reservas.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader(biblioteca+"/reservas.txt"))) {
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
