import java.util.ArrayList;
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
            Emprestimo novo = new Emprestimo();
            System.out.println("Registo de Emprestimos \n");
            novo.setNum(emprestimos.size() + 1);
            System.out.println("Numero:" + novo.getNum());
            System.out.print("nif: " );
            sc.nextInt();
        }

        //função para eliminar um emprestimo guardado no array
        public void eliminarEmprestimos() {

        }
}

