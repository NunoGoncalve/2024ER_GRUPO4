import java.util.Scanner;

public class Jornal_revista {
    private String titulo, editora, categoria, ISSN;
    private int ano_publicacao;

    public Jornal_revista() {
        titulo = "";
        editora = "";
        categoria = "";
        ISSN = "";
        ano_publicacao = 0;
    }

    public Jornal_revista(int ano) {
        titulo = "";
        editora = "";
        categoria = "";
        ISSN = "";
        ano_publicacao = ano;
    }

    /** Cria um jornal/revista de acordo com as informações obtidas do ficheiro */
    public Jornal_revista(String jornalRevista) {
        String reg = "[|;]";
        String[] campos = jornalRevista.split(reg);

        this.titulo = campos[0];
        this.editora = campos[1];
        this.categoria = campos[2];
        this.ISSN = campos[3];
        this.ano_publicacao = Integer.parseInt(campos[4]);
    }

    /** Pede ao utilizador as informações sobre o jornal/revista a adicionar */
    public Jornal_revista criarJornalRevista() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o título do jornal/revista: ");
        titulo = ler.nextLine();
        System.out.print("Insira a editora do jornal/revista: ");
        editora = ler.nextLine();
        System.out.print("Insira a categoria do jornal/revista: ");
        categoria = ler.nextLine();
        System.out.print("Insira o ISSN do jornal/revista: ");
        ISSN = ler.nextLine();
        System.out.print("Insira o ano de publicação do jornal/revista: ");
        ano_publicacao = ler.nextInt();
        return this;
    }

    /** Retorna uma string apropriada à visualização */
    public String formataJornalRevistaE() {
        return "Título: " + this.titulo + "\nEditora: " + this.editora + "\nCategoria: " + this.categoria + "\nISSN: " + this.ISSN + "\nAno de publicação: " + this.ano_publicacao;
    }

    /** Retorna uma string apropriada à escrita no ficheiro */
    public String formataJornalRevistaF() {
        return titulo + "|" + editora + "|" + categoria + "|" + ISSN + "|" + ano_publicacao + ";";
    }

    public String getISSN() {
        return ISSN;
    }

    public int getAno_publicacao() {
        return ano_publicacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }
}
