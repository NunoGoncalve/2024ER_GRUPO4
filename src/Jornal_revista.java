import java.util.Scanner;

/**
 * A classe Jornal_revista representa um jornal ou revista.
 * Inclui informações como título, editora, categoria, ISSN, ano de publicação e estado de empréstimo.
 */
public class Jornal_revista {
    // Atributos
    private String titulo;
    private String editora;
    private String categoria;
    private String ISSN;
    private int ano_publicacao;
    private boolean emprestado;

    /**
     * Construtor padrão que inicializa os atributos com valores padrão.
     */
    public Jornal_revista() {
        titulo = "";
        editora = "";
        categoria = "";
        ISSN = "";
        ano_publicacao = 0;
        emprestado = false;
    }

    /**
     * Construtor que inicializa o ano de publicação.
     *
     * @param ano Ano de publicação do jornal ou revista.
     */
    public Jornal_revista(int ano) {
        titulo = "";
        editora = "";
        categoria = "";
        ISSN = "";
        ano_publicacao = ano;
        emprestado = false;
    }

    /**
     * Construtor que inicializa todos os atributos.
     *
     * @param titulo         Título do jornal ou revista.
     * @param editora        Editora do jornal ou revista.
     * @param categoria      Categoria do jornal ou revista.
     * @param ISSN           Código ISSN do jornal ou revista.
     * @param ano_publicacao Ano de publicação do jornal ou revista.
     * @param emprestado     Estado de empréstimo do jornal ou revista.
     */
    public Jornal_revista(String titulo, String editora, String categoria, String ISSN, int ano_publicacao, boolean emprestado) {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
        this.ISSN = ISSN;
        this.ano_publicacao = ano_publicacao;
        this.emprestado = emprestado;
    }

    /**
     * Construtor que inicializa os atributos a partir de uma string formatada.
     *
     * @param jornalRevista String contendo os dados do jornal ou revista separados por delimitadores.
     */
    public Jornal_revista(String jornalRevista) {
        String reg = "[|;]";
        String[] campos = jornalRevista.split(reg);

        this.titulo = campos[0];
        this.editora = campos[1];
        this.categoria = campos[2];
        this.ISSN = campos[3];
        this.ano_publicacao = Integer.parseInt(campos[4]);
        this.emprestado = false;
    }

    /**
     * Valida se um ISSN está no formato correto.
     *
     * @param ISSN Código ISSN a ser validado.
     * @return true se o ISSN for válido, false caso contrário.
     */
    public boolean validarISSN(String ISSN) {
        String regexISSN = "\\d{4}-\\d{3}X";
        return ISSN.matches(regexISSN);
    }

    /**
     * Valida se o ano de publicação está dentro de um intervalo válido.
     *
     * @param ano Ano de publicação a ser validado.
     * @return true se o ano for válido, false caso contrário.
     */
    private boolean validarAnoPublicacao(int ano) {
        int anoAtual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return (ano >= 1900 && ano <= anoAtual);
    }

    /**
     * Valida se uma data está no formato correto.
     *
     * @param data Data a ser validada.
     * @return true se a data for válida, false caso contrário.
     */
    public boolean validarData(String data) {
        String regexData = "\\d{2}-\\d{2}-\\d{4}";
        return data.matches(regexData);
    }

    /**
     * Cria um novo jornal ou revista, solicitando os dados ao utilizador.
     *
     * @return Objeto Jornal_revista criado.
     */
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
                break;
            } else {
                System.out.print("ISSN inválido. Insira um ISSN válido (Ex: 1234-567X): ");
            }
        }

        System.out.print("Insira a data de publicação (dd-MM-yyyy): ");
        while (true) {
            String data = ler.nextLine();
            if (validarData(data)) {
                String[] partes = data.split("-");
                this.ano_publicacao = Integer.parseInt(partes[2]);
                break;
            } else {
                System.out.print("Data inválida. Insira no formato dd-MM-yyyy: ");
            }
        }

        this.emprestado = false;
        return this;
    }

    /**
     * Retorna uma string formatada para exibição dos dados do jornal ou revista.
     *
     * @return String formatada.
     */
    public String formataJornalRevistaE() {
        return "Título: " + this.titulo + "\nEditora: " + this.editora + "\nCategoria: " + this.categoria + "\nISSN: " + this.ISSN + "\nAno de publicação: " + this.ano_publicacao + "\nEmprestado: " + (this.emprestado ? "Sim" : "Não");
    }

    /**
     * Retorna uma string formatada para armazenamento em ficheiro.
     *
     * @return String formatada.
     */
    public String formataJornalRevistaF() {
        return titulo + "|" + editora + "|" + categoria + "|" + ISSN + "|" + ano_publicacao + ";";
    }

    /**
     * Retorna o estado de empréstimo do jornal ou revista.
     *
     * @return true se o jornal/revista estiver emprestado, false caso contrário.
     */
    public boolean getEmprestado() {
        return emprestado;
    }

    /**
     * Define o estado de empréstimo do jornal ou revista.
     *
     * @param emprestado Estado de empréstimo a ser definido.
     */
    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    /**
     * Exibe todos os jornais e revistas disponíveis (não emprestados).
     *
     * @param jornaisRevistas Lista de jornais/revistas a ser verificada.
     */
    public static void listarJornaisRevistasLivres(Jornal_revista[] jornaisRevistas) {
        for (Jornal_revista jr : jornaisRevistas) {
            if (!jr.getEmprestado()) {
                System.out.println(jr.formataJornalRevistaE());
            }
        }
        System.out.println("--------------- Fim ---------------");
    }

    /**
     * Exibe todos os jornais e revistas emprestados.
     *
     * @param jornaisRevistas Lista de jornais/revistas a ser verificada.
     */
    public static void listarJornaisRevistasEmprestados(Jornal_revista[] jornaisRevistas) {
        for (Jornal_revista jr : jornaisRevistas) {
            if (jr.getEmprestado()) {
                System.out.println(jr.formataJornalRevistaE());
            }
        }
        System.out.println("--------------- Fim ---------------");
    }

    // Getters e Setters
    /**
     * Retorna o título do jornal ou revista.
     *
     * @return Título do jornal ou revista.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título do jornal ou revista.
     *
     * @param titulo Novo título do jornal ou revista.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Retorna a editora do jornal ou revista.
     *
     * @return Editora do jornal ou revista.
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Define a editora do jornal ou revista.
     *
     * @param editora Nova editora do jornal ou revista.
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Retorna a categoria do jornal ou revista.
     *
     * @return Categoria do jornal ou revista.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do jornal ou revista.
     *
     * @param categoria Nova categoria do jornal ou revista.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna o ISSN do jornal ou revista.
     *
     * @return ISSN do jornal ou revista.
     */
    public String getISSN() {
        return ISSN;
    }

    /**
     * Define o ISSN do jornal ou revista.
     *
     * @param ISSN Novo ISSN do jornal ou revista.
     */
    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    /**
     * Retorna o ano de publicação do jornal ou revista.
     *
     * @return Ano de publicação.
     */
    public int getAno_publicacao() {
        return ano_publicacao;
    }

    /**
     * Define o ano de publicação do jornal ou revista.
     *
     * @param ano_publicacao Novo ano de publicação.
     */
    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }
}
