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
                System.out.println(emp.getNum() + " " + emp.getEmprestados() + " " + emp.getNif());
            }
        }


    public void registarEmprestimos(Livros livros) {
            Scanner sc = new Scanner(System.in);
            //define o formato para a data
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            Emprestimo novo = new Emprestimo(); // cria um novo emprestimo
            System.out.println("Registo de Emprestimos \n");
            novo.setNum(emprestimos.size() + 1);
            System.out.println("Numero:" + novo.getNum());
            System.out.println("nif: " ); // necessario verificar nif
            novo.setNif(sc.nextInt());

            novo.getEmprestados().clear();
            System.out.println("Emprestar livros:");
            String codigo;
            while(true) {
                System.out.print("  Proximo livro: "); // necessario verificar nLivro
                codigo = sc.nextLine(); //
                // VERIFICAR SE LIVRO EXISTE NA LISTA DE LIVROS - FUNCAO FOI DESENVOLVIDA?
                Livro liv = livros.procuraLivro(codigo);
                if (pos != -1) {
                    if (liv.getLivre() == 0)  // se livro 'Disponivel' {
                        novo.getEmprestados().add(codigo);
                    }
                    else {
                        if (livros.getLivro()[pos].getEstado() == 1)  // se livro 'Emprestado'
                            System.out.println("  Livro Emprestado!");
                        else
                            System.out.println("  Livro Reservado!");
                    }
                else{
                    System.out.println("  Livro nao existe!");
                }
            }
            novo.setDataInicio(new Date());
            System.out.println("Data Inicio: " + formatDate.format(novo.getDataInicio()));
            System.out.print("Data prevista de entrega: ");
            String dataFimPrev = sc.next();

            emprestimos.add(novo); // insere novo emprestimo na lista de emprestimos
        }

        public int pesquisarEmprestimo(int cod) {
            for (int i = 0; i < emprestimos.size(); i++) {
                if (emprestimos.get(i).getNum() == cod) {
                    return i;
                }
            }
            return -1;
        }

        public void devolverEmprestimo() throws java. text. ParseException {
            Scanner sc = new Scanner(System.in);
            System.out.println("Registo do emprestimo: ");
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Numero do emprestimo: ");
            int numEmprestimo = sc.nextInt();
            int i = pesquisarEmprestimo(numEmprestimo);
            if (i == -1) {
                System.out.println("Emprestimo inexistente");
                return;
            }
            System.out.println("Emprestimo encontrado, insira a data de devolução (dd/MM/yyyy): ");
            this.emprestimos.get(i).setDataFim(formatDate.parse(sc.next()));
            //FALTA atualizar o campo disponibilidade do livro
        }

        public int totalEmprestimos (Date dataInicio, Date dataFim) {
            int total = 0;
            for (int i = 0; i < emprestimos.size(); i++) {
                if (emprestimos.get(i).getDataInicio().compareTo(dataInicio) >= 0 && emprestimos.get(i).getDataInicio().compareTo(dataFim) <= 0) {
                    return total += 1;
                }
            }
            return -1;
        }
}

