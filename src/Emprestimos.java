import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Emprestimos {
        private ArrayList<Emprestimo> emprestimos;

        //Construtor dos arrays
        public Emprestimos() {
            this.emprestimos = new ArrayList<>();
        }

        //getter do array de emprestimos
        public ArrayList<Emprestimo> getEmprestimos() {
            return emprestimos;
        }

        //setter do array de emprestimos
        public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
            this.emprestimos = emprestimos;
        }

        //função para adicionar emprestimos ao array
        public void insertEmprestimo(Emprestimo emprestimo) {
            this.emprestimos.add(emprestimo);
        }

        //função para listar os emprestimos guardados no array
        public void listarEmprestimos() {
            for (Emprestimo emp : emprestimos) {
                System.out.println(emp.getNum() + " " + emp.getnLivro() + " " + emp.getNif());
            }
        }

        public void registarEmprestimos() {
            Scanner sc = new Scanner(System.in);
            //define o formato para a data
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            Emprestimo novo = new Emprestimo(); // cria um novo emprestimo
            System.out.println("Registo de Emprestimos \n");
            novo.setNum(emprestimos.size() + 1);
            System.out.println("Numero:" + novo.getNum());
            System.out.println("nif: " ); // necessario verificar nif
            novo.setNif(sc.nextInt());
            System.out.println("nLivro: " ); // necessario verificar nLivro
            novo.setnLivro(sc.nextLine());
            novo.setDataInicio(new Date());
            System.out.println("Data Inicio: " + formatDate.format(novo.getDataInicio()));
            System.out.print("Data prevista de entrega: ");
            String dataFimPrev = sc.next();

            emprestimos.add(novo); // insere novo emprestimo na lista de emprestimos
        }

    public void registarEmprestimosv2() throws ParseException {
        Scanner sca = new Scanner(System.in);
        //define o formato para a data
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Registo de Emprestimos \n");
        int numemp = emprestimos.size() + 1;
        System.out.println("Numero:" + numemp);
        System.out.println("nif: " ); // necessario verificar nif
        int nifUtente = sca.nextInt();
        System.out.println("nLivro: " ); // necessario verificar nLivro
        String nLivro = sca.next();
        Date dataInicio = new Date();
        System.out.println("Data Inicio: " + formatDate.format(dataInicio));
        System.out.print("Data prevista de entrega: ");
        String dataFimPrev = sca.next();

        Emprestimo novo = new Emprestimo(numemp, nifUtente, nLivro, dataInicio, formatDate.parse(dataFimPrev) );

        emprestimos.add(novo); // insere novo emprestimo na lista de emprestimos
    }
}

