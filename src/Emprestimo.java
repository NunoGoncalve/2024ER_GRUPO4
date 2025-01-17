import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Emprestimo {
    private int num;
    private Utente utente;
    private Livros livrosReservados;
    private JornaisRevistas jornaisRevistasReservados;
    private Date dataInicio;
    private Date dataFimPrev;
    private Date dataFim;

    public Livros getEmprestadosLivros() {
        return livrosReservados;
    }

    public JornaisRevistas getEmprestadosJornaisRevistas() {
        return jornaisRevistasReservados;
    }

    //Construtor do empréstimo 2
    public Emprestimo() {
        this.num = 0;
        this.dataInicio = null;
        this.dataFim = null;
        this.dataFimPrev = null;
        this.utente = new Utente();
        this.livrosReservados = new Livros();
        this.jornaisRevistasReservados = new JornaisRevistas();
    }

    /** Cria um emprestimo de acordo com ad informações obtidas do ficheiro */
    public Emprestimo(String emprestimo, String biblioteca) {
        String regra="[|;]", regraLivros="[*]";
        String[] campos = emprestimo.split(regra);
        String[] livrosJornaisRevistas = campos[4].split(regraLivros);
        Date dataFim = null;
        Livros livs = new Livros();
        JornaisRevistas jr = new JornaisRevistas();
        Utentes uts = new Utentes();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        livs.lerLivros(biblioteca);
        jr.lerJornaisRevistas(biblioteca);
        uts.lerUtentes(biblioteca);

        this.num = Integer.parseInt(campos[0]);

        try {
            this.dataInicio = formatter.parse(campos[1]);
            this.dataFimPrev = formatter.parse(campos[2]);
            if(!campos[3].equals("00/00/0000")) dataFim = formatter.parse(campos[3]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.dataFim = dataFim;

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

    public String formataEmprestimoF () {
        String pattern = "dd/MM/yyyy";
        DateFormat formato = new SimpleDateFormat(pattern);
        String dataFimPrev = formato.format(this.dataFimPrev);
        String dataFim;
        boolean flag=false;

        if(this.dataFim!=null) dataFim = formato.format(this.dataFim);
        else dataFim = "00/00/0000";
        String dataInicio = formato.format(this.dataInicio);
        String format= this.num+"|"+dataInicio+"|"+dataFimPrev+"|"+dataFim+"|";

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

    /** verificaData
     * @param data -> data a verificar
     * @return -> se a data é válida ou não*/
    public boolean verificaData(String data){
        return data.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    /** Metodo criarEmprestimo
     Pede ao utilizador as informações do empréstimo e faz as verificações necessárias
     @param numEmprestimo utilizado para definir o número do empréstimo */
    public Emprestimo criarEmprestimo(int numEmprestimo, String biblioteca) {
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
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Registo de Empréstimos \n");
        this.num = numEmprestimo;
        System.out.println("Número do empréstimo:" + this.num);
        uts.listarUtentes();
        String nif;
        do{
            System.out.print("Insira o NIF do Utente:  ");
            nif = ler.next();
        }while(!utente.verificaNif(nif));
        int NIF = Integer.parseInt(nif);
        if(uts.procurarUtente(NIF)!=null) this.utente = uts.procurarUtente(NIF);
        String codigo;
        System.out.print("Quantos livros/jornais/revistas deseja emprestar: ");
        int numljr = ler.nextInt();
        ler.skip("\n");
        boolean flag=false;
        for (int i = 0; i < numljr; i++) {
            flag=false;
            do{
                if(livs.contLivrosLivres()==0) System.out.println("Todos os livros estão ocupados");
                else livs.listarLivros();
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
                        this.getEmprestadosJornaisRevistas().adicionarJornalRevista(jor);
                        jor.setLivre(false);
                        jors.guardarJornaisRevistas(biblioteca);
                        flag=true;
                    }else System.out.println("Jornal/Revista não disponível");
                }else {
                    System.out.println("ISBN/ISSN inválido");
                }
            }while(!flag);
        }

        flag=false;
        this.dataInicio= new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String data;
        System.out.println("Data de inicio: " + formatDate.format(this.dataInicio));
        do{
            System.out.print("Data prevista de entrega: ");
            data=ler.next();
            if(verificaData(data)) flag=true;
            else System.out.println("Data inválida! Tente novamente");
        }while(!flag);

        try {
            this.dataFimPrev = formatter.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.dataFim=null;
        return this;
    }

    public boolean isEmpty() {
        return this.num==0;
    }

    /** Metodo formataEmprestimoE
     * Formata os dados relativos a um empréstimo */
    public void formataEmprestimoE(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println();
        System.out.println("--------------- Empréstimo ---------------");
        System.out.println("Número do empréstimo: "+this.num);
        System.out.println("Nif associado: "+this.utente.getNif());

        System.out.println("--------------- Livros/Jornais/Revistas ---------------");
        if (!this.livrosReservados.isEmpty()) this.livrosReservados.listarLivros();
        if (!this.jornaisRevistasReservados.isEmpty()) this.jornaisRevistasReservados.listarJornaisRevistas();

        System.out.println("Data de inicio: "+format.format(this.dataInicio));
        System.out.println("Data de fim previsto: "+format.format(this.dataFimPrev));
        if (this.dataFim!=null) System.out.println("Data de fim efetivo: "+format.format(this.dataFim));
        else System.out.println("Ainda não entregue!");
        System.out.println();
    }

    public int getNum() {
        return num;
    }

    /**metodos getters e setters */

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataFimPrev() {
        return dataFimPrev;
    }

}