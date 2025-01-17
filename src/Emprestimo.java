import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Emprestimo {
    private int num;
    private Utente utente;
    private Livros emprestadosLivros;
    private JornaisRevistas emprestadosJornaisRevistas;
    private Date dataInicio;
    private Date dataFimPrev;
    private Date dataFim;

    public Livros getEmprestadosLivros() {
        return emprestadosLivros;
    }

    public JornaisRevistas getEmprestadosJornaisRevistas() {
        return emprestadosJornaisRevistas;
    }

    //Construtor do empréstimo 2
    public Emprestimo() {
        this.num = 0;
        this.dataInicio = null;
        this.dataFim = null;
        this.dataFimPrev = null;
        Livros liv = new Livros();
        Utente ut = new Utente();
        JornaisRevistas rev = new JornaisRevistas();
        this.utente = ut;
        this.emprestadosJornaisRevistas = rev;
        this.emprestadosLivros = liv;
    }

    /** Cria um emprestimo de acordo com ad informações obtidas do ficheiro */
    public Emprestimo(String emprestimo) {
        String regra="[|;]", regraLivros="[*]";
        String[] campos = emprestimo.split(regra);
        String[] livros = campos[4].split(regraLivros);
        Date dataInicio, dataFimPrev, dataFim = null;
        Livros livs = new Livros();
        Utentes uts = new Utentes();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        livs.lerLivros();
        uts.lerUtentes();

        this.num = Integer.parseInt(campos[0]);

        try {
            dataInicio = formatter.parse(campos[1]);
            dataFimPrev = formatter.parse(campos[2]);
            if(!campos[3].equals("00/00/0000")) dataFim = formatter.parse(campos[3]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataFimPrev= dataFimPrev;
        this.emprestadosLivros = new Livros();
        this.emprestadosJornaisRevistas = new JornaisRevistas();
        for (String livro : livros) {
            if (!livs.procuraLivro(livro).isEmpty()) {
                this.emprestadosLivros.adicionarLivro(livs.procuraLivro(livro));
            }
        }

        this.utente = uts.procurarUtente(Integer.parseInt(campos[5]));
    }

    public String formataEmprestimoF () {
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String dataFimPrev = df.format(this.dataFimPrev);
        String dataFim;
        if(this.dataFim!=null) dataFim = df.format(this.dataFim);
        else dataFim = "00/00/0000";
        String dataInicio = df.format(this.dataInicio);

        String format= this.num+"|"+dataInicio+"|"+dataFimPrev+"|"+dataFim+"|";
        for(Livro liv: this.emprestadosLivros.getLivros()){
            if(liv == this.emprestadosLivros.getLivros().getLast()) format+=liv.getISBN()+"|";
            else format+=liv.getISBN()+"*";
        }
        format+=this.utente.getNif()+";";
        return format;
    }

    public boolean verificarDatas(String data){
        return data.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    /** Metodo criarEmprestimo
     Pede ao utilizador as informações do empréstimo e faz as verificações necessárias
     @param num utilizado para definir o número do empréstimo */
    public Emprestimo criarEmprestimo(int num) {
        Scanner ler = new Scanner(System.in);
        Utentes uts = new Utentes();
        Livro liv = new Livro();
        Livros livs = new Livros();
        Jornal_revista jor = new Jornal_revista();
        JornaisRevistas jors = new JornaisRevistas();
        livs.lerLivros();
        uts.lerUtentes();
        jors.ler_jornaisRevistas();
        //define o formato para a data
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Registo de Empréstimos \n");
        this.num = num;
        System.out.println("Numero:" + this.num);
        uts.listarUtentes();
        int Nif;
        System.out.print("Insira o NIF do Utente:  ");
        Nif = ler.nextInt();
        this.utente =uts.procurarUtente(Nif);
        String codigo;
        System.out.print("Quantos livros/jornais/revistas deseja emprestar: ");
        int numljr = ler.nextInt();
        ler.skip("\n");
        boolean flag;
        for (int i = 0; i < numljr; i++) {
            flag=false;
            do{
                System.out.print("Insira o ISBN ou ISSN do livro/jornal/revista desejado: ");
                codigo = ler.nextLine();
                if(liv.verificaIsbn(codigo)){
                    liv= livs.procuraLivro(codigo);
                    if(!liv.isEmpty() && liv.getLivre()){
                        this.emprestadosLivros.adicionarLivro(liv);
                        liv.setLivre(false);
                        livs.guardarLivros();
                        flag=true;
                    }else System.out.println("Livro não disponível");
                }else if (jor.validarISSN(codigo)){
                    jor=jors.procuraJornalRevista(codigo);
                    if(!jor.isEmpty() && jor.getLivre()){
                        this.getEmprestadosJornaisRevistas().adicionarJornalRevista(jor);
                        jor.setLivre(false);
                        jors.guardarJornaisRevistas();
                        flag=true;
                    }else System.out.println("Jornal/Revista não disponível");
                }else {
                    System.out.println("ISBN/ISSN inválido");
                }
            }while(!flag);
        }

        this.dataInicio= new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String data;
        Date date;
        System.out.println("Data Inicio: " + formatDate.format(this.dataInicio));
        flag=false;
        do{
            System.out.print("Data prevista de entrega: ");
            data=ler.nextLine();
            if(verificarDatas(data)) flag=true;
            else System.out.println("Data invválida! Tente novamente");
        }while(!flag);


        try {
            date = formatter.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.dataFimPrev = date;
        this.dataFim=null;
        return this;
    }

    public boolean isEmpty() {
        return this.num==0;
    }

    /**
     Formata os dados relativos a um empréstimo
     */
    public void formataEmprestimoE(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println();
        System.out.println("----- Empréstimo ----");
        System.out.println("Número do empréstimo: "+this.num);
        System.out.println("Nif associado: "+this.utente.getNif());

        if (this.emprestadosLivros!=null) {
            System.out.println("Livros emprestados: ");
            this.emprestadosLivros.listarLivros();
        }

        if (!this.emprestadosJornaisRevistas.isEmpty()) this.emprestadosJornaisRevistas.listarJornaisRevistas();

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