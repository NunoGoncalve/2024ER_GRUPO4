import javax.swing.plaf.synth.SynthOptionPaneUI;
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

            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            for (Emprestimo emp : emprestimos) {
                String aux = String.format("%05d    %d    %s    %s    %s    %s \n", (emp.getNum()  + " " + emp.getNif() + " " + formatDate.format(emp.getDataInicio()) + " "
                        + formatDate.format(emp.getDataFimPrev()) + " " + formatDate.format(emp.getDataFim()) + " " + formatDate.format(emp.getEmprestados().get(0))));
                System.out.println(aux);
                for(int i = 1; i < emp.getEmprestados().size(); i++) {
                    System.out.println(String.format("%64s%s", " ", emp.getEmprestados().get(i)));
                }
            }
        }

        public String maisLivros() {
            Scanner sc = new Scanner(System.in);
            String resp = "";
            while (!resp.equals("S") && !resp.equals("N")) {
                System.out.println("mais livrios? [S/N]");
                resp = sc.nextLine();
            }
            return resp;
        }

        public int pesquisaUtente(Utentes utentes, int nif) {
            for (int i = 0; i < utentes.getUts().size(); i++) {
                if (utentes.getUts().get(i).getNif() == nif) {
                    return i;
                }
            }
            return -1;
        }

    public void registarEmprestimos(Livros livros, Utentes utentes) {
            Scanner sc = new Scanner(System.in);
            //define o formato para a data
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            Emprestimo novo = new Emprestimo(); // cria um novo emprestimo
            System.out.println("Registo de Emprestimos \n");
            novo.setNum(emprestimos.size() + 1);
            System.out.println("Numero:" + novo.getNum());

            while (true) {
                System.out.println("nif: " );
                novo.setNif(sc.nextInt());
                if (pesquisaUtente(utentes, novo.getNif()) != -1) {
                    break;
                } else {
                    System.out.println("nif errado");
                }
            }


            novo.getEmprestados().clear();
            System.out.println("Emprestar livros:");
            System.out.println("insira 1 para livros 2 para revistas/jornais:");
            String codigo;
            while (true) {
                System.out.println("Proximo livro: ");// necessario verificar nLIVRO
                codigo = sc.nextLine(); // verificar se livro existe na lista de livros
                Livro liv = livros.procuraLivro(codigo);
                if(liv.getISBN().equals("")) {
                    if(liv.getLivre()) { //se livro disponivel
                        novo.getEmprestados().add(codigo);
                    }
                    else {
                        System.out.println("Livro não disponivel");
                    }
                }
                if(maisLivros().equals("N")) {
                    break;
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
            for (String emp : this.emprestimos.get(i).getEmprestados()){



            }
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

