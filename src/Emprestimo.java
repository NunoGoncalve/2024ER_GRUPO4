import java.util.ArrayList;
import java.util.Date;

public class Emprestimo {
    private int num;
    private int nif;
    private ArrayList <String> emprestados;
    private Date dataInicio;
    private Date dataFimPrev;
    private Date dataFim;


    //Construtor do emprestimo
    public Emprestimo(int num, int nif, ArrayList <String> emprestados, Date dataInicio, Date dataFimPrev) {
        this.num = num;
        this.nif = nif;
        this.emprestados = new ArrayList<>();
        this.emprestados = emprestados;
        this.dataInicio = dataInicio;
        this.dataFim = null;
        this.dataFimPrev = dataFimPrev;
    }

    //Construtor do emprestimo 2
    public Emprestimo() {
        this.num = 0;
        this.nif = 0;
        this.emprestados = new ArrayList<>();
        this.dataInicio = null;
        this.dataFim = null;
        this.dataFimPrev = null;
    }

    //metodos getters e setters
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public ArrayList <String> getEmprestados() {
        return this.emprestados;
    }

    public void setnEmprestados(ArrayList <String> emprestados) {
        this.emprestados = emprestados;
    }

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
    //Fim dos setters e getters
}