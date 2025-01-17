import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Reserva {
    private int num;
    private Utente utente;
    private Livros livrosReservados;
    private JornaisRevistas jornaisRevistasReservados;
    private Date dataRegisto;
    private Date dataInicio;
    private Date dataFim;


    public Reserva() {
        this.num = 0;
        this.dataRegisto = null;
        this.dataInicio = null;
        this.dataFim = null;
        this.utente = new Utente();
        this.livrosReservados = new Livros();
        this.jornaisRevistasReservados = new JornaisRevistas();
    }

    /** Cria uma reserva de acordo com ad informações obtidas do ficheiro */
    public Reserva(String reserva, String biblioteca) {
        String regra="[|;]", regraLivros="[*]";
        String[] campos = reserva.split(regra);
        String[] livrosJornaisRevistas = campos[4].split(regraLivros);
        Livros livs = new Livros();
        JornaisRevistas jr = new JornaisRevistas();
        Utentes uts = new Utentes();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        livs.lerLivros(biblioteca);
        jr.lerJornaisRevistas(biblioteca);
        uts.lerUtentes(biblioteca);

        this.num = Integer.parseInt(campos[0]);

        try {
            this.dataRegisto = formatter.parse(campos[1]);
            this.dataInicio  = formatter.parse(campos[2]);
            this.dataFim = formatter.parse(campos[3]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.livrosReservados = new Livros();
        this.jornaisRevistasReservados = new JornaisRevistas();
        for (String livJorRev : livrosJornaisRevistas) {
            if (!livs.procuraLivro(livJorRev).isEmpty()) {
                this.livrosReservados.adicionarLivro(livs.procuraLivro(livJorRev));
            } else if (!jr.procuraJornalRevista(livJorRev).isEmpty()) {
                this.jornaisRevistasReservados.adicionarJornalRevista(jr.procuraJornalRevista(livJorRev));
            }
        }

        this.utente = uts.procurarUtente(Integer.parseInt(campos[5]));
    }

    public String formataReservaF () {
        String pattern = "dd/MM/yyyy";
        DateFormat formato = new SimpleDateFormat(pattern);
        String dataRegisto = formato.format(this.dataRegisto);
        String dataInicio = formato.format(this.dataInicio);
        String dataFim = formato.format(this.dataFim);

        boolean flag=false;


        String format= this.num+"|"+dataRegisto+"|"+dataInicio+"|"+dataFim+"|";

        if(!this.livrosReservados.isEmpty()) {
            flag=true;
            for(Livro liv: this.livrosReservados.getLivros()){
                if(liv == this.livrosReservados.getLivros().getLast()) format+=liv.getISBN();
                else format+=liv.getISBN()+"*";
            }
        }
        if(!this.jornaisRevistasReservados.isEmpty()){
            if(flag){
                for(Jornal_revista jr: this.jornaisRevistasReservados.getJornalRevistas()){
                    if (jr == this.jornaisRevistasReservados.getJornalRevistas().getFirst()) format+="*"+jr.getISSN();
                    else if (jr == this.jornaisRevistasReservados.getJornalRevistas().getLast()) format+=jr.getISSN();
                    else format+=jr.getISSN()+"*";
                }
            }else{
                for(Jornal_revista jr: this.jornaisRevistasReservados.getJornalRevistas()){
                    if (jr == this.jornaisRevistasReservados.getJornalRevistas().getLast()) format+=jr.getISSN();
                    else format+=jr.getISSN()+"*";
                }
            }
        }
        format+="|"+this.utente.getNif()+";";
        return format;
    }

    public void formataReservaE(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println();
        System.out.println("--------------- Reserva ---------------");

        System.out.println("Número da reserva: "+String.format("%03d", this.num));
        System.out.println("Nif associado: "+this.utente.getNif());

        System.out.println();
        System.out.println("--------------- Livros/Jornais/Revistas ---------------");
        if (this.livrosReservados!=null)  this.livrosReservados.listarLivros();
        if (!this.jornaisRevistasReservados.isEmpty()) this.jornaisRevistasReservados.listarJornaisRevistas();

        System.out.println("Data de registo: "+format.format(this.dataRegisto));
        System.out.println("Data de inicio: "+format.format(this.dataInicio));
        System.out.println("Data de fim: "+format.format(this.dataFim));
        System.out.println();
    }

    /** Metodo criarReserva
     Pede ao utilizador as informações do empréstimo e faz as verificações necessárias
     @param numReserva utilizado para definir o número da reserva */
    public Reserva criarReserva(int numReserva, String biblioteca) {
        Scanner ler = new Scanner(System.in);
        Utentes uts = new Utentes();
        Livro liv = new Livro();
        Livros livs = new Livros();
        Jornal_revista jor = new Jornal_revista();
        JornaisRevistas jors = new JornaisRevistas();

        livs.lerLivros(biblioteca);
        uts.lerUtentes(biblioteca);
        jors.lerJornaisRevistas(biblioteca);

        //define o formato para a data
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.num = numReserva;
        System.out.println("Número da reserva:" + this.num);
        uts.listarUtentes();
        String nif;
        do{
            System.out.print("Insira o NIF do Utente:  ");
            nif = ler.next();
        }while(!utente.verificaNif(nif));
        int NIF = Integer.parseInt(nif);
        if(uts.procurarUtente(NIF)!=null) this.utente = uts.procurarUtente(NIF);
        String codigo;
        System.out.print("Quantos livros/jornais/revistas deseja reservar?: ");
        int numljr = ler.nextInt();
        ler.skip("\n");
        boolean flag;
        for (int i = 0; i < numljr; i++) {
            flag=false;
            do{
                if(livs.contLivrosLivres()==0) System.out.println("Todos os livros estão ocupados");
                else livs.listarLivrosLivres();
                if(jors.contJornaisRevistasLivres()==0) System.out.println("Todos os jornais/revistas estão ocupados");
                else jors.listarJornaisRevistasLivres();
                System.out.println();
                System.out.print("Insira o ISBN ou ISSN do livro/jornal/revista desejado: ");
                codigo = ler.nextLine();
                if(liv.verificaIsbn(codigo)){
                    liv= livs.procuraLivro(codigo);
                    if(!liv.isEmpty() && liv.getLivre()){
                        this.livrosReservados.adicionarLivro(liv);
                        liv.setLivre(false);
                        livs.guardarLivros(biblioteca);
                        flag=true;
                    }else System.out.println("Livro não disponível");
                }else if (jor.validarISSN(codigo)){
                    jor=jors.procuraJornalRevista(codigo);
                    if(!jor.isEmpty() && jor.getLivre()){
                        this.jornaisRevistasReservados.adicionarJornalRevista(jor);
                        jor.setLivre(false);
                        jors.guardarJornaisRevistas(biblioteca);
                        flag=true;
                    }else System.out.println("Jornal/Revista não disponível");
                }else {
                    System.out.println("ISBN/ISSN inválido");
                }
            }while(!flag);
        }

        this.dataRegisto= new Date();
        System.out.println("Data de Registo: " + formato.format(this.dataRegisto));

        Date[] datas= pedeDatas();
        this.dataInicio = datas[0];
        this.dataFim = datas[1];

        return this;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public Utente getUtente() {
        return utente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataRegisto() {
        return dataRegisto;
    }

    public int getNum() {
        return num;
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

    public boolean verificaData(String data){
        return data.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    public boolean isEmpty(){
        return num==0;
    }

    // Métodos getters e setters
    public Livros getLivrosReservados() {
        return livrosReservados;
    }

    public JornaisRevistas getJornaisRevistasReservados() {
        return jornaisRevistasReservados;
    }


}
