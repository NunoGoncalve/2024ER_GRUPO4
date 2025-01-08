import java.util.ArrayList;

public class Emprestimos {
        private ArrayList<Emprestimo> emprestimos;

        public Emprestimos() {
            this.emprestimos = new ArrayList<>();
        }

        public ArrayList<Emprestimo> getEmprestimos() {
            return emprestimos;
        }
        public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
            this.emprestimos = emprestimos;
        }
        public void insertEmprestimo(Emprestimo emprestimo) {
            this.emprestimos.add(emprestimo);
        }
        public void listarEmprestimos() {
            for (Emprestimo emp : emprestimos) {
                System.out.println(emp.getNum() + " " + emp.getnLivro() + " " + emp.getnUtente());
            }
        }
}

