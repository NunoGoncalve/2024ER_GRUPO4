import java.util.ArrayList;

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

        //função para eliminar um emprestimo guardado no array
        public void eliminarEmprestimos() {

        }
}

