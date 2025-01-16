import java.util.Scanner;

/** Class livro usada para criar objetos livro com as propriedades:
 * Titulo
 * Editora
 * Categoria
 * ISBN
 * Ano de edição
 * Todas as propriedades são Strings
 */
public class Livro {
    private String titulo, editora, autor, categoria, ISBN;
    private int anoEdicao;
    private boolean livre;

    /** Construtor da classe Livro
     * Define as propriedades do Livro a vazio */
    public Livro() {
        titulo = "";
        editora = "";
        autor = "";
        categoria = "";
        ISBN = "";
        anoEdicao = 0;
        livre = true;
    }

    /** Construtor da classe Livro
     * @param livro é usado para definir as propriedades do Livro, através da separação seguindo a regra definida. */
    public Livro(String livro) {
        String regra="[|;]";
        String[] campos = livro.split(regra);

        this.titulo = campos[0];
        this.editora = campos[1];
        this.autor = campos[2];
        this.categoria = campos[3];
        this.ISBN = campos[4];
        this.anoEdicao = Integer.parseInt(campos[5]);
        this.livre = Boolean.parseBoolean(campos[6]);
    }

    /** Metodo criarLivro
     *  Pede ao utilizador as informações sobre o livro e verifica-as
     *  @return this (próprio objeto)*/
    public Livro criarLivro(){
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o titulo do livro: ");
        titulo = ler.nextLine();
        System.out.print("Insira a editora do livro: ");
        editora = ler.nextLine();
        System.out.print("Insira o(s)/a(s) autor(es) do livro: ");
        autor= ler.nextLine();
        System.out.print("Insira a categoria do livro: ");
        categoria = ler.nextLine();
        do{
            System.out.print("Insira o ISBN do livro: ");
            ISBN = ler.nextLine();
        }while(!verificaIsbn(ISBN));
        String ano;
        do{
            System.out.print("Insira o ano de edição do livro: ");
            ano = ler.next();
            if(ano.matches("\\d{4}")){
                anoEdicao = Integer.parseInt(ano);
                break;
            }
            else  System.out.println("Erro insira um ano válido!");
        }while(true);
        livre = true;
        return this;
    }

    /** Metodo verificaIsbn
     * Verifica o isbn através de uma regra.
     * @return true se o isbn estiver de acordo com a regra e false se o isbn não estiver de acordo com a regra*/
    public boolean verificaIsbn(String isbn){
        if(isbn.matches("\\d{9}(\\d|[Xx])") || isbn.matches("\\d-\\d{2}-\\d{6}-(\\d|[Xx])")
                || isbn.matches("\\d{13}") || isbn.matches("\\d{3}-\\d-\\d{3}-\\d{5}-\\d")){
            return true;
        }
        else{
            System.out.println("Formatação errada! Por favor insira um ISBN válido");
            return false;
        }
    }

    /** Metodo formataLivroE
     * Imprime as propriadades do livro para o ecrã*/
    public void formataLivroE(){
        System.out.println("--------------- Livro ---------------");
        System.out.println("Titulo: " + titulo);
        System.out.println("Editora: " + editora);
        System.out.println("Autor: " + autor);
        System.out.println("Categoria: " + categoria);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Ano edicao: " + anoEdicao);
        System.out.println("Livre: " + (this.livre ? "Sim" : "Não"));
    }

    /** Metodo isEmpty()
     * @return de true ou false conforme o titulo estar vazio ou não ou seja se o livro está vazio */
    public boolean isEmpty(){
        return titulo.isEmpty();
    }

    /** Metodo formataLivroF
     * @return das propriedades do livro formatado para inserir no ficheiro*/
    public String formataLivroF(){
        return titulo+"|"+editora+"|"+autor+"|"+categoria+"|"+ISBN+"|"+anoEdicao+"|"+livre+";";
    }

    /** Metodo getISBN
     * @return do isbn*/
    public String getISBN() {
        return ISBN;
    }

    /** Metodo getLivre
     * @return da propriedade livre*/
    public boolean getLivre() {
        return livre;
    }

    /** Metodo setTitulo
     * @param titulo define o titulo do livro com o titulo recebido*/
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** Metodo setEditora
     * @param editora define a categoria do livro com a editora recebida*/
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /** Metodo setAutor
     * @param autor define o autor do livro com o autor recebido*/
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /** Metodo setCategoria
     * @param categoria define a categoria do livro com o parametro recebida*/
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /** Metodo setISBN
    * @param ISBN define o ISBN do livro com o parametro recebido*/
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /** Metodo setAnoEdicao
     * @param anoEdicao define o ano_edicao do livro com o parametro recebido*/
    public void setAnoEdicao(int anoEdicao) {
        this.anoEdicao = anoEdicao;
    }

    /** Metodo setLivre
     * @param livre define a propriedade Livre do livro com o parametro recebido*/
    public void setLivre(boolean livre) {
        this.livre = livre;
    }

}
