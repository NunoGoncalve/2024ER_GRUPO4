import java.util.Scanner;

public class Jornal_revista {
    private String titulo, editora, categoria, ISSN;
    private int ano_publicacao;
    private boolean livre; // Indica se o jornal/revista está livre

    // Construtor padrão
    public Jornal_revista() {
        titulo = "";
        editora = "";
        categoria = "";
        ISSN = "";
        ano_publicacao = 0;
        livre = true; // Inicialmente, está livre
    }

    // Construtor com ano_publicacao apenas
    public Jornal_revista(int ano) {
        titulo = "";
        editora = "";
        categoria = "";
        ISSN = "";
        ano_publicacao = ano;
        livre = true; // Inicialmente, está livre
    }

    // Construtor com todos os atributos
    public Jornal_revista(String titulo, String editora, String categoria, String ISSN, int ano_publicacao, boolean livre) {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
        this.ISSN = ISSN;
        this.ano_publicacao = ano_publicacao;
        this.livre = livre;
    }

    // Construtor para leitura do ficheiro
    public Jornal_revista(String jornalRevista) {
        String reg = "[|;]";
        String[] campos = jornalRevista.split(reg);

        this.titulo = campos[0];
        this.editora = campos[1];
        this.categoria = campos[2];
        this.ISSN = campos[3];
        this.ano_publicacao = Integer.parseInt(campos[4]);
        this.livre = Boolean.parseBoolean(campos[5]); // Inicialmente, está livre
    }

    // Função de validação para o ISSNor
    public boolean validarISSN(String ISSN) {
        // Verifica se o ISSN possui o formato correto (Ex: "1234-567X")
        String regexISSN = "\\d{4}-\\d{3}X";
        return ISSN.matches(regexISSN);
    }

    // Função de validação para a data (ano de publicação)
    private boolean validarAnoPublicacao(int ano) {
        int anoAtual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return (ano >= 1900 && ano <= anoAtual);
    }

    public boolean validarData(String data) {
        String regexData = "\\d{2}-\\d{2}-\\d{4}";
        return data.matches(regexData);
    }

    // Método para criar um novo jornal/revista
    public Jornal_revista criarJornalRevista() {
        Scanner ler = new Scanner(System.in);

        System.out.print("Insira o título do jornal/revista: ");
        this.titulo = ler.nextLine();

        System.out.print("Insira a editora do jornal/revista: ");
        this.editora = ler.nextLine();

        System.out.print("Insira a categoria do jornal/revista: ");
        this.categoria = ler.nextLine();

        System.out.print("Insira o ISSN do jornal/revista: ");
        while (true) {
            this.ISSN = ler.nextLine();
            if (validarISSN(this.ISSN)) {
                break; // ISSN válido
            } else {
                System.out.print("ISSN inválido. Insira um ISSN válido (Ex: 1234-567X): ");
            }
        }

        System.out.print("Insira a data de publicação (dd-MM-yyyy): ");
        while (true) {
            String data = ler.nextLine();
            if (validarData(data)) {
                String[] partes = data.split("-");
                this.ano_publicacao = Integer.parseInt(partes[2]); // Extrai o ano
                break;
            } else {
                System.out.print("Data inválida. Insira no formato dd-MM-yyyy: ");
            }
        }

        this.livre = true; // Inicialmente, está livre
        return this;
    }

    public String formataJornalRevistaE() {
        return "Título: " + this.titulo + "\nEditora: " + this.editora + "\nCategoria: " + this.categoria + "\nISSN: " + this.ISSN + "\nAno de publicação: " + this.ano_publicacao + "\nlivre: " + (this.livre ? "Sim" : "Não");
    }

    public String formataJornalRevistaF() {
        return titulo + "|" + editora + "|" + categoria + "|" + ISSN + "|" + ano_publicacao + "|"+ livre +";";
    }

    public boolean getLivre() {
        return livre;
    }

    public void setLivre(boolean livre) {
        this.livre = livre;
    }

    // Métodos para visualizar se estão livres ou não
    public static void listarJornaisRevistasOcupadas(Jornal_revista[] jornaisRevistas) {
        for (Jornal_revista jr : jornaisRevistas) {
            if (!jr.getLivre()) {
                System.out.println(jr.formataJornalRevistaE());
            }
        }
        System.out.println("--------------- Fim ---------------");
    }

    public static void listarJornaisRevistaslivres(Jornal_revista[] jornaisRevistas) {
        for (Jornal_revista jr : jornaisRevistas) {
            if (jr.getLivre()) {
                System.out.println(jr.formataJornalRevistaE());
            }
        }
        System.out.println("--------------- Fim ---------------");
    }

    public boolean isEmpty(){
        return this.titulo.isEmpty();
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public int getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }
}
