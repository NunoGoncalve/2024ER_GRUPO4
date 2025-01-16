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
    private int ano_edicao;
    private boolean livre;

    public Livro() {
        titulo = "";
        editora = "";
        autor = "";
        categoria = "";
        ISBN = "";
        ano_edicao = 0;
        livre = true;
    }

    /** Cria um livro de acordo com ad informações obtidas do ficheiro */
    public Livro(String livro) {
        String regra="[|;]";
        String[] campos = livro.split(regra);

        this.titulo = campos[0];
        this.editora = campos[1];
        this.autor = campos[2];
        this.categoria = campos[3];
        this.ISBN = campos[4];
        this.ano_edicao = Integer.parseInt(campos[5]);
        this.livre = Boolean.parseBoolean(campos[6]);
    }

    /** Pede ao utilizador as informações sobre o livro a adicionar*/
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
        boolean flag=false;
        do{

            System.out.print("Insira o ISBN do livro: ");
            ISBN = ler.nextLine();
            if(verificaIsbn(ISBN)) flag=true;
            else System.out.println("Formatação errada! Por favor insira um ISBN válido");
        }while(!flag);
        String ano;
        do{
            System.out.print("Insira o ano de edição do livro: ");
            ano = ler.next();
            if(ano.matches("\\d{4}")){
                ano_edicao = Integer.parseInt(ano);
                break;
            }
            else  System.out.println("Erro insira um ano válido!");
        }while(true);
        livre = true;
        return this;
    }

    public boolean verificaIsbn(String isbn){
        if(isbn.matches("\\d{9}(\\d|[Xx])") || isbn.matches("\\d-\\d{2}-\\d{6}-(\\d|[Xx])")
                || isbn.matches("\\d{13}") || isbn.matches("\\d{3}-\\d-\\d{3}-\\d{5}-\\d")){
            return true;
        }
        else{
            return false;
        }
    }

    /** Retorna uma string apropriada à visualização*/
    public void formataLivroE(){
        System.out.println("--------------- Livro ---------------");
        System.out.println("Titulo: " + titulo);
        System.out.println("Editora: " + editora);
        System.out.println("Autor: " + autor);
        System.out.println("Categoria: " + categoria);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Ano edicao: " + ano_edicao);
    }

    public boolean isEmpty(){
        return titulo.isEmpty();
    }

    /** Retorna uma string apropriada à escrita no ficheiro*/
    public String formataLivroF(){
        return titulo+"|"+editora+"|"+autor+"|"+categoria+"|"+ISBN+"|"+ano_edicao+"|"+livre+";";
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean getLivre() {
        return livre;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setAutor(String autor) {

        this.autor = autor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAno_edicao(int ano_edicao) {
        this.ano_edicao = ano_edicao;
    }

    public void setLivre(boolean livre) {
        this.livre = livre;
    }

}
