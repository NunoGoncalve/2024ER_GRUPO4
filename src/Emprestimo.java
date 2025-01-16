import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    //Construtor do emprestimo 2
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
        String regra="[|;]";
        String regraLivros="[*]";
        String[] campos = emprestimo.split(regra);
        String[] livros = campos[4].split(regraLivros);
        Livros livs = new Livros();

        Utentes uts = new Utentes();
        this.num = Integer.parseInt(campos[0]);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicio = null;
        try {
            dataInicio = formatter.parse(campos[1]);
            Date dataFim = formatter.parse(campos[2]);
            Date dataFimPrev = formatter.parse(campos[3]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataFimPrev= dataFimPrev;
        for (int i = 0; i < livros.length; i++) {
            if(!livs.procuraLivro(livros[i]).isEmpty()){
                this.emprestadosLivros.adicionarLivro(livs.procuraLivro(livros[i]));
            }
        }

        this.utente = uts.pesquisarUtente(Integer.parseInt(campos[6]));
    }

    public String formataEmprestimoF () {
        String format= this.num+"|"+this.dataInicio+"|"+this.dataFimPrev+"|"+this.dataFim+"|";
        for(Livro liv: this.emprestadosLivros.getLivros()){
            if(liv == this.emprestadosLivros.getLivros().getLast()) format+=liv+"|";
            else format+=liv+"*";
        }
        format+=this.utente.getNif()+";";
        return format;
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
        System.out.println("Registo de Emprestimos \n");
        this.num = num;
        System.out.println("Numero:" + this.num);
        uts.listarUtentes();
        int Nif;
        System.out.print("Insira o NIF do Utente:  ");
        Nif = ler.nextInt();
        this.utente =uts.pesquisarUtente(Nif);
        String codigo;
        System.out.print("Quantos livros/jornais/revistas deseja emprestar: ");
        int numljr = ler.nextInt();
        ler.skip("\n");
        boolean flag=false;
        for (int i = 0; i < numljr; i++) {
            do{
                flag=false;
                System.out.print("Insira o ISBN ou ISSN do livro/jornal/revista desejado: ");
                codigo = ler.nextLine();
                if(liv.verificaIsbn(codigo)){
                    liv= livs.procuraLivro(codigo);
                    if(!liv.isEmpty() && liv.getLivre()){
                        this.emprestadosLivros.adicionarLivro(liv);
                        liv.setLivre(false);
                        livs.guardarLivros();
                        flag=true;
                    }else System.out.println("Livro não disponivel");
                }else if (jor.validarISSN(codigo)){
                    if(!jors.procuraJornalRevista(codigo).isEmpty()){
                        this.emprestadosJornaisRevistas.adicionarJornalRevista(jors.procuraJornalRevista(codigo));
                        jors.procuraJornalRevista(codigo).setlivre(false);
                        flag=true;
                    }
                }
            }while( flag!= true);
        }

        /**
         Cria a data de início do emprestimo
          */
        this.dataInicio= new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String data;
        System.out.println("Data Inicio: " + formatDate.format(this.dataInicio));
        System.out.print("Data prevista de entrega: ");
        data=ler.nextLine();
        Date date = null;
        try {
            date = formatter.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.dataFimPrev = date;
        return this;
    }

    /**
     Formata os dados relativos a um empréstimo
     */
    public void formataEmprestimoE(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("----- Empréstimo ----");
        System.out.println("Número do empréstimo: "+this.num);
        System.out.println("Nif associado: "+this.utente.getNif());
        if(!this.emprestadosLivros.isEmpty())this.emprestadosLivros.listarLivros();
        if(!this.emprestadosJornaisRevistas.isEmpty())this.emprestadosJornaisRevistas.listarJornaisRevistas();
        System.out.println(format.format(this.dataInicio));
        System.out.println(format.format(this.dataFimPrev));
        if(this.dataFim!=null) System.out.println(format.format(this.dataFim));
        else System.out.println("Ainda não entregue!");
    }



    /**metodos getters e setters */
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    /*public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }*/

    /*public ArrayList <String> getEmprestados() {
        return this.emprestados;
    }

    public void setnEmprestados(ArrayList <String> emprestados) {
        this.emprestados = emprestados;
    }*/

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataFimPrev() {
        return dataFimPrev;
    }

    public void setDataFimPrev(Date dataFimPrev) {
        this.dataFimPrev = dataFimPrev;
    }
    /**Fim dos setters e getters */
}