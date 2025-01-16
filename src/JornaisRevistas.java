import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class JornaisRevistas guarda um array de jornais e revistas
 */
public class JornaisRevistas {
    private ArrayList<Jornal_revista> jornaisRevistas = new ArrayList<>();

    /**
     * Chama a função ler_ficheiro e guarda as linhas lidas num array e envia-as para a função setJornaisRevistas
     */
    public void ler_jornaisRevistas() {
        int n_linhas = contLinhas();
        if (n_linhas != 0) setJornaisRevistas(lerFicheiro(n_linhas));
    }

    /**
     * Cria um jornal/revista, adiciona-o ao array e chama a função write_jornalRevista
     */
    public void adicionarJornalRevista() {
        Jornal_revista jr = new Jornal_revista();
        this.jornaisRevistas.add(jr.criarJornalRevista());
    }

    public void adicionarJornalRevista(Jornal_revista jr) {
        this.jornaisRevistas.add(jr);
    }

    /**
     * Verifica se o ficheiro está vazio, se não estiver imprime os jornais/revistas que estão no array
     */
    public void listarJornaisRevistas() {
        if (this.jornaisRevistas.isEmpty()) {
            System.out.println("Ficheiro vazio! Adicione um jornal ou revista");
        } else {
            for (Jornal_revista jr : this.jornaisRevistas) {
                System.out.println("--------------- Jornal/Revista ---------------");
                System.out.println(jr.formataJornalRevistaE());
            }
            System.out.println("--------------- Fim ---------------");
        }
    }

    /**
     * Lista apenas os jornais/revistas não emprestados
     */
    public void listarJornaisRevistasLivres() {
        for (Jornal_revista jr : this.jornaisRevistas) {
            if (jr.getlivre()) {
                System.out.println("--------------- Jornal/Revista Livre ---------------");
                System.out.println(jr.formataJornalRevistaE());
            }
        }
        System.out.println("--------------- Fim ---------------");
    }

    /**
     * Lista apenas os jornais/revistas emprestados
     */
    public void listarJornaisRevistasOcupados() {
        for (Jornal_revista jr : this.jornaisRevistas) {
            if (!jr.getlivre()) {
                System.out.println("--------------- Jornal/Revista Ocupado ---------------");
                System.out.println(jr.formataJornalRevistaE());
            }
        }
        System.out.println("--------------- Fim ---------------");
    }

    public boolean isEmpty(){
        return this.jornaisRevistas.isEmpty();
    }
    private void menuAtualizar(Jornal_revista jr) {
        Scanner ler = new Scanner(System.in);
        int op;
        System.out.println("--------------- Atualizar Jornal/Revista ---------------");
        System.out.println("1) Título");
        System.out.println("2) Editora");
        System.out.println("3) Categoria");
        System.out.println("4) ISSN");
        System.out.println("5) Ano de publicação");
        System.out.println("6) Todos");
        System.out.println("7) Cancelar");
        System.out.print("Selecione uma opção: ");
        op = ler.nextInt();
        ler.nextLine(); // Consumir o newline
        switch (op) {
            case 1:
                System.out.print("Insira o título do jornal/revista: ");
                jr.setTitulo(ler.nextLine());
                break;
            case 2:
                System.out.print("Insira a editora do jornal/revista: ");
                jr.setEditora(ler.nextLine());
                break;
            case 3:
                System.out.print("Insira a categoria do jornal/revista: ");
                jr.setCategoria(ler.nextLine());
                break;
            case 4:
                System.out.print("Insira o ISSN do jornal/revista: ");
                jr.setISSN(ler.nextLine());
                break;
            case 5:
                System.out.print("Insira a nova data de publicação (dd-MM-yyyy): ");
                while (true) {
                    String data = ler.nextLine();
                    if (jr.validarData(data)) {
                        String[] partes = data.split("-");
                        jr.setAno_publicacao(Integer.parseInt(partes[2])); // Extrai o ano
                        break;
                    } else {
                        System.out.print("Data inválida. Insira no formato dd-MM-yyyy: ");
                    }
                }
                break;
            case 6:
                this.jornaisRevistas.set(this.jornaisRevistas.indexOf(jr), jr.criarJornalRevista());
                break;
            case 7:
                System.out.println("A cancelar...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private String pedeISSN() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o ISSN do jornal/revista: ");
        return ler.nextLine();
    }

    /**
     * Verifica se o jornal/revista retornado tem valores, caso tenha chama o menu atualizar
     */
    public void atualizarJornalRevista() {
        Jornal_revista jr = procuraJornalRevista(pedeISSN());
        if (jr.getAno_publicacao() != -1) menuAtualizar(jr);
    }

    /**
     * Verifica se o jornal/revista retornado tem valores, caso tenha imprime-o no ecrã
     */
    public void listaJornalRevista() {
        Jornal_revista jr = procuraJornalRevista(pedeISSN());
        if (jr.getAno_publicacao() != -1) {
            System.out.println("--------------- Jornal/Revista ---------------");
            System.out.println(jr.formataJornalRevistaE());
            System.out.println("--------------- Fim ---------------");
        }
    }

    /**
     * Procura o jornal/revista no array, caso não encontre retorna um jornal/revista vazio, caso encontre retorna o jornal/revista que encontrou
     */
    public Jornal_revista procuraJornalRevista(String issn) {
        Jornal_revista jr_flag = new Jornal_revista(-1);
        if (this.jornaisRevistas.isEmpty()) {
            System.out.println("Ficheiro vazio! Adicione um jornal ou revista");
        } else {
            for (Jornal_revista jr : this.jornaisRevistas) {
                if (jr.getISSN().equals(issn)) return jr;
            }
            System.out.println("ISSN não encontrado! Tente novamente!");
        }
        return jr_flag;
    }

    /**
     * Verifica se o ficheiro está vazio, se não estiver procura o ISSN no array, elimina-o, e chama a função reescrever_jornaisRevistas
     */
    public void eliminarJornalRevista() {
        if (this.jornaisRevistas.isEmpty()) {
            System.out.println("Ficheiro vazio! Adicione um jornal ou revista");
        } else {
            String issn = pedeISSN();
            if (this.jornaisRevistas.removeIf(jr -> jr.getISSN().equals(issn)))
                System.out.println("O jornal/revista foi removido com sucesso!");
            else
                System.out.println("Jornal/Revista não encontrado!");
        }
    }

    /**
     * Escreve no ficheiro
     */
    public void guardarJornaisRevistas() {
        try {
            FileWriter writer = new FileWriter("jornais_revistas.txt");
            for (Jornal_revista jr : this.jornaisRevistas) {
                if (this.jornaisRevistas.getFirst() == jr) writer.write(jr.formataJornalRevistaF());
                else writer.write("\n" + jr.formataJornalRevistaF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void limparJornaisRevistas() {
        this.jornaisRevistas.clear();
    }

    /**
     * Lê as linhas do ficheiro e guarda-as num array
     */
    private String[] lerFicheiro(int n_linhas) {
        int i = 0;
        String[] jornaisRevistas = new String[n_linhas];

        File myfile = new File("jornais_revistas.txt");
        try {
            Scanner myReader = new Scanner(myfile);
            while (myReader.hasNextLine()) {
                jornaisRevistas[i] = myReader.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return jornaisRevistas;
    }

    /**
     * Cria os jornais e revistas e adiciona-os ao array conforme a informação no ficheiro
     */
    private void setJornaisRevistas(String[] jornaisRevistas) {
        for (String s : jornaisRevistas) {
            Jornal_revista jr = new Jornal_revista(s);
            this.jornaisRevistas.add(jr);
        }
    }

    /**
     * Conta as linhas presentes no ficheiro
     */
    private int contLinhas() {
        int i = 0;
        File myfile = new File("jornais_revistas.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader("jornais_revistas.txt"))) {
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
