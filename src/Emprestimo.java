import java.util.Scanner;
import java.util.Date;

public class Emprestimo {
    private int num;
    private long nUtente;
    private long nLivro;
    private Date dataInicio;
    private Date dataFimPrev;
    private Date dataFim;

    public static void menuEmprestimo() {
        Scanner ler = new Scanner(System.in);
        int op;
        do {
            System.out.println("1 - Listar");
            System.out.println("2 - Adicionar");
            System.out.println("3 - Pesquisar");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Eliminar");
            System.out.println("6 - Sair");
            System.out.print("Selecione uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    System.out.println(1);
                    break;

                case 2:
                    System.out.println(2);
                    break;

                case 3:
                    System.out.println(3);
                    break;

                case 4:
                    System.out.println(4);
                    break;

                case 5:
                    System.out.println(5);
                    break;

                case 6:
                    System.out.println("A sair ...");
                    break;
                default:
                    System.out.println("Opção incorreta! Tente novamente");
                    break;
            }
        } while (op != 6);
    }


    public Emprestimo(int num, int nUtente, long nLivros, Date dataInicio, Date dataFim, Date dataFimPrev) {
        this.num = num;
        this.nUtente = nUtente;
        this.nLivro = nLivros;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataFimPrev = dataFimPrev;
    }

    public Emprestimo() {
        this.num = 0;
        this.nUtente = 0;
        this.nLivro = 0;
        this.dataInicio = null;
        this.dataFim = null;
        this.dataFimPrev = null;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getnUtente() {
        return nUtente;
    }

    public void setnUtente(long nUtente) {
        this.nUtente = nUtente;
    }

    public long getnLivro() {
        return nLivro;
    }

    public void setnLivro(long nLivro) {
        this.nLivro = nLivro;
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

}