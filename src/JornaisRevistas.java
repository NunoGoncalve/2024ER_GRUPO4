import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe JornaisRevistas que gerencia uma coleção de jornais e revistas.
 */
public class JornaisRevistas {
    private ArrayList<Jornal_revista> jornaisRevistas = new ArrayList<>();

    /** Metodo lerjormaisRevistas
     * Lê os dados dos jornais e revistas a partir de um ficheiro.
     * Chama a função "lerFicheiro" e guarda as linhas lidas num array,
     * enviando-as para a função "setJornaisRevistas".*/
    public void lerJornaisRevistas(String biblioteca) {
        int nLinhas = contLinhas(biblioteca);
        if (nLinhas != 0) setJornaisRevistas(lerFicheiro(nLinhas, biblioteca));
    }

    public int size(){
        return jornaisRevistas.size();
    }

    /**
     * Adiciona um novo jornal ou revista à coleção e o escreve no ficheiro.
     */
    public void adicionarJornalRevista() {
        Jornal_revista jr = new Jornal_revista();
        this.jornaisRevistas.add(jr.criarJornalRevista());
    }

    public void adicionarJornalRevista(Jornal_revista jr) {
        this.jornaisRevistas.add(jr);
    }

    /**
     * Lista todos os jornais e revistas disponíveis na coleção.
     * Caso o ficheiro esteja vazio, informa o utilizador.
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
     * Lista apenas os jornais e revistas que não estão emprestados.
     */
    public void listarJornaisRevistasLivres() {
        for (Jornal_revista jr : this.jornaisRevistas) {
            if (jr.getLivre()) {
                System.out.println("--------------- Jornal/Revista Livre ---------------");
                System.out.println(jr.formataJornalRevistaE());
            }
        }

    }

    public int contJornaisRevistasLivres(){
        int numLivres = 0;
        for (Jornal_revista jr : this.jornaisRevistas) {
            if(jr.getLivre()){
                numLivres++;
            }
        }
        return numLivres;
    }

    /**
     * Lista apenas os jornais e revistas que estão emprestados.
     */
    public void listarJornaisRevistasOcupados() {
        for (Jornal_revista jr : this.jornaisRevistas) {
            if (!jr.getLivre()) {
                System.out.println("--------------- Jornal/Revista Ocupado ---------------");
                System.out.println(jr.formataJornalRevistaE());
            }
        }
        System.out.println("--------------- Fim ---------------");
    }


    public boolean isEmpty(){
        return this.jornaisRevistas.isEmpty();
    }

    /**
     * Exibe o menu de opções para atualizar os dados de um jornal ou revista.
     *
     * @param jr O objeto Jornal_revista a ser atualizado.
     */
    private void menuAtualizar(Jornal_revista jr) {
        Scanner ler = new Scanner(System.in);
        int op;
        System.out.println("--------------- Atualizar Jornal/Revista ---------------");
        System.out.println("1)  Título");
        System.out.println("2)  Editora");
        System.out.println("3)  Categoria");
        System.out.println("4)  ISSN");
        System.out.println("5)  Ano de publicação");
        System.out.println("6)  Todos");
        System.out.println("7)  Cancelar");
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

    /**
     * Solicita ao utilizador o ISSN de um jornal ou revista.
     *
     * @return O ISSN inserido pelo utilizador.
     */
    private String pedeISSN() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o ISSN do jornal/revista: ");
        return ler.nextLine();
    }

    /**
     * Atualiza os dados de um jornal ou revista.
     */
    public void atualizarJornalRevista() {
        Jornal_revista jr = procuraJornalRevista(pedeISSN());
        if (jr.getAno_publicacao() != -1) menuAtualizar(jr);
    }

    /**
     * Lista os dados de um jornal ou revista específico.
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
     * Procura um jornal ou revista pelo ISSN fornecido.
     *
     * @param issn O ISSN do jornal ou revista.
     * @return O objeto correspondente ou um jornal/revista vazio.
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
     * Remove um jornal ou revista pelo ISSN fornecido.
     */
    public boolean eliminarJornalRevista(String issn) {
        if (this.jornaisRevistas.isEmpty()) {
            System.out.println("Ficheiro vazio! Adicione um jornal ou revista");
        } else {
            if (this.jornaisRevistas.removeIf(jr -> jr.getISSN().equals(issn))){
                System.out.println("O jornal/revista foi removido com sucesso!");
                return true;
            }
            System.out.println("Jornal/Revista não encontrado!");
        }
        return false;
    }

    /** Pede o ISSN ao utilizador, verifica se este encontra-se com o formato correto e retorna-o */
    public String pedeIssn(){
        String issn;
        Scanner ler = new Scanner(System.in);
        Jornal_revista  jr = new Jornal_revista();
        boolean flag=false;
        do{
            System.out.print("Insira o ISBN do livro: ");
            issn=ler.nextLine();
            if(jr.validarISSN(issn)) flag=true;
            else System.out.println("Formatação errada! Por favor insira um ISBN válido");
        }while(!flag);
        return issn;
    }

    public ArrayList<Jornal_revista> getJornalRevistas() {
        return this.jornaisRevistas;
    }

    /**
     * Escreve os dados da coleção no ficheiro.
     */
    public void guardarJornaisRevistas(String biblioteca) {
        try {
            FileWriter writer = new FileWriter(biblioteca+"/jornais_revistas.txt");
            for (Jornal_revista jr : this.jornaisRevistas) {
                if (this.jornaisRevistas.getFirst() == jr) writer.write(jr.formataJornalRevistaF());
                else writer.write("\n" + jr.formataJornalRevistaF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Limpa a coleção de jornais e revistas.
     */
    public void limparJornaisRevistas() {
        this.jornaisRevistas.clear();
    }

    /**
     * Lê os dados do ficheiro e os armazena num array.
     *
     * @param nLinhas O número de linhas no ficheiro.
     * @return Um array que contem os dados lidos.
     */
    private String[] lerFicheiro(int nLinhas, String biblioteca) {
        int i = 0;
        String[] jornaisRevistas = new String[nLinhas];

        File myfile = new File(biblioteca+"/jornais_revistas.txt");
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
     * Cria os objetos de jornais e revistas a partir de dados lidos e adiciona-os à coleção.
     *
     * @param jornaisRevistas Um array com os dados lidos do ficheiro.
     */
    private void setJornaisRevistas(String[] jornaisRevistas) {
        for (String s : jornaisRevistas) {
            Jornal_revista jr = new Jornal_revista(s);
            this.jornaisRevistas.add(jr);
        }
    }

    /**
     * Conta o número de linhas presentes no ficheiro.
     *
     * @return O número de linhas no ficheiro.
     */
    private int contLinhas(String biblioteca) {
        int i = 0;
        File myfile = new File(biblioteca+"/jornais_revistas.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader(biblioteca+"/jornais_revistas.txt"))) {
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
