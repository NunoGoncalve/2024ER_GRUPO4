import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Emprestimos {
        private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

        //função para adicionar emprestimos ao array
        public void insertEmprestimo(Emprestimo emprestimo) {
            this.emprestimos.add(emprestimo);
        }

        //função para listar os emprestimos guardados no array

        public void listarEmprestimos(){
            for (Emprestimo emp : emprestimos) {
                emp.formataEmprestimoE();
            }
        }
        //função para verificar se o utilizador quer adicionar varios livros no mesmo emprestimo
        public String atualizarEmprestimo(Emprestimo emprestimo) {
            Scanner sc = new Scanner(System.in);
            String resp = "";
            while (!resp.equals("S") && !resp.equals("N")) {
                System.out.println("mais livrios? [S/N]");
                resp = sc.nextLine();
            }
            return resp;
        }
        //função para verificar se o utente existe

        public void registarEmprestimo(){
            Emprestimo emp = new Emprestimo();
            emprestimos.add(emp.criarEmprestimo(emprestimos.size()+1));
        }

        /**função para verificar se um emprestimo existe a partir do seu numero */
        public Emprestimo pesquisarEmprestimo(int cod) {
            Emprestimo empFlag = new Emprestimo();
            for (Emprestimo emp : emprestimos) {
                if (emp.getNum() == cod) {
                    return emp;
                }
            }
            return empFlag;
        }

        /**
         Função criada para devolver um empréstimo à biblioteca
         */
        public void devolverEmprestimo() {
            Scanner sc = new Scanner(System.in);
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            listarEmprestimos();
            System.out.print("Insira o número do emprestimo: ");
            int numEmprestimo = sc.nextInt();
            if(pesquisarEmprestimo(numEmprestimo)==null) {
                System.out.println("Empréstimo não encontrado");
            }else {
                System.out.print("Emprestimo encontrado, insira a data de devolução (dd/MM/yyyy): ");
                try {
                    pesquisarEmprestimo(numEmprestimo).setDataFim(formatDate.parse(sc.next()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Livros livsEmp = pesquisarEmprestimo(numEmprestimo).getEmprestadosLivros();
                Livros livs = new Livros();
                livs.lerLivros();

                for(int i=0; i< livsEmp.size();i++){
                    livs.procuraLivro(livsEmp.getLivros().get(i).getISBN()).setLivre(true);
                }
                livs.guardarLivros();
            }
        }

        public int intervaloData (Date dataInicio, Date dataFim) {
            int total = 0;
            for (Emprestimo emp: emprestimos) {
                if (emp.getDataInicio().compareTo(dataInicio) >= 0 && emp.getDataInicio().compareTo(dataFim) <= 0) {
                    return total += 1;
                }
            }
            return -1;
        }

   /* Apresentar uma lista dos utentes cuja devolução dos itens emprestados tenha um
    atraso superior a um número de dias a perguntar ao utilizador;*/
    public void diasAtraso(){
        Scanner ler = new Scanner(System.in);

        String pattern = "dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String fimPrev, fim;
        int fimPrevI, fimI, atraso;
        System.out.print("Insira o número de dias em atraso desejado: ");
        int numDias = ler.nextInt();
        for(Emprestimo emp: emprestimos) {
            fimPrev = df.format(emp.getDataFimPrev());
            fim = df.format(emp.getDataFim());
            fimPrevI = Integer.parseInt(fimPrev);
            fimI = Integer.parseInt(fim);
            atraso=fimI-fimPrevI;
            if(atraso==numDias) {
                emp.formataEmprestimoE();
            }

        }
    }

    public void lerEmprestimos() {
        int n_linhas = contLinhas();
        if(n_linhas!=0) setEmprestimos(lerFicheiro(n_linhas));
    }

    private String[] lerFicheiro(int n_linhas){
        int i=0;
        String[] livros = new String[n_linhas];

        File myfile = new File("livros.txt");
        try {
            Scanner myReader = new Scanner(myfile);
            while (myReader.hasNextLine()) {
                livros[i] = myReader.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return livros;
    }

    private void setEmprestimos(String[] emprestimos) {
        for (String s : emprestimos) {
            Emprestimo emp = new Emprestimo(s);
            this.emprestimos.add(emp);
        }
    }

    public void guardarEmprestimos(){
        try {
            FileWriter writer = new FileWriter("emprestimos.txt");
            for (Emprestimo emp : this.emprestimos) {
                if(this.emprestimos.getFirst()==emp) writer.write(emp.formataEmprestimoF());
                else writer.write("\n"+emp.formataEmprestimoF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int contLinhas(){
        int i=0;
        File myfile = new File("emprestimos.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader("emprestimos.txt"))) {
                    while (reader.readLine() != null) i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return i;
    }
}

