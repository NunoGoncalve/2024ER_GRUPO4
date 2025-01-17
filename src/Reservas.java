import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservas {
    private List<Reserva> reservas = new ArrayList<>();

    // Método para adicionar uma nova reserva
    public void adicionarReserva() {
        Reserva res = new Reserva();
        this.reservas.add(res.criarReserva(this.reservas.size()+1));
    }

    public void lerReservas() {
        int nLinhas = contLinhas();
        if(nLinhas!=0) setReservas(lerFicheiro(nLinhas));
    }

    /** Metodo setReservas
     * @param reservas recebe como parametro um array de strings
     * Para cada string no array reservas cria um reservas e adiciona-o ao array conforme a informação encontrada no array */
    private void setReservas(String[] reservas) {
        for (String s : reservas) {
            Reserva res = new Reserva(s);
            this.reservas.add(res);
        }
    }



    // Método para exibir todas as reservas
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
            if(res.verificarDatas(strDataInicio)){
                System.out.print("Data de fim: ");
                strDataFim = ler.next();
                if(res.verificarDatas(strDataFim)) {
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



    public Reserva procurarReserva(int cod) {
        Reserva empFlag = new Reserva();
        for (Reserva res : reservas) {
            if (res.getNum() == cod) {
                return res;
            }
        }
        return empFlag;
    }

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
                pesquisarEmprestimo(pedeDatas());
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
                pesquisarEmprestimo(nif, pedeDatas());
                break;

            case "4":
                pesquisarEmprestimo();
                break;
            case "5":
                System.out.println("A cancelar... ");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente!");
                break;
        }
    }

    public void pesquisarEmprestimo(Date[] datas) {
        for (Reserva res : reservas) {
            if(res.getDataInicio().compareTo(datas[0])>=0 && res.getDataFim().compareTo(datas[1])<=0){
                res.formataReservaE();
            }
        }
    }
    public void pesquisarEmprestimo(int nif, Date[] datas) {
        for (Reserva res : reservas) {
            if(res.getUtente().getNif() == nif && res.getDataInicio().compareTo(datas[0])>=0 && res.getDataFim().compareTo(datas[1])<=0){
                res.formataReservaE();
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
        for (Reserva res : reservas) {
            if(res.getUtente().getNif() == nif){
                res.formataReservaE();
            }
        }
    }

    public boolean isEmpty(){
        return reservas.isEmpty();
    }


    public void removerReserva(int numero) {
        Reserva res = procurarReserva(numero);
        if (!res.isEmpty()) {
            this.reservas.remove(res);
            System.out.println("Reserva #" + String.format("%03d", numero) + " removida com sucesso.");
        } else {
            System.out.println("Número de reserva inválido.");
        }
    }


    /** Metodo guardarReservas
     * Guarda o conteúdo do array no ficheiro verificando se é a primeira linha ou as seguintes */
    public void guardarReservas(){
        try {
            FileWriter writer = new FileWriter("reservas.txt");
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
     * @param nLinhas  recebe como parametro o número de linhas que o ficheiro têm e cria um array de strings desse tamanho,
     * de seguida lê as linhas do ficheiro e guarda-as num array retornando o mesmo */
    private String[] lerFicheiro(int nLinhas){
        int i=0;
        String[] reservas = new String[nLinhas];

        File myfile = new File("reservas.txt");
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
     * Cria o ficheiro caso este não exista e retorna o número de linhas a 0.
     * Caso o ficheiro exista conta as linhas presentes no ficheiro
     * @return i número de linhas*/
    private int contLinhas(){
        int i=0;
        File myfile = new File("reservas.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader("reservas.txt"))) {
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
