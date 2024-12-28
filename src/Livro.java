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

    public Livro() {
        titulo = "";
        editora = "";
        autor = "";
        categoria = "";
        ISBN = "";
        ano_edicao = 0;
    }
    public Livro(int ano) {
        titulo = "";
        editora = "";
        autor = "";
        categoria = "";
        ISBN = "";
        ano_edicao = ano;
    }

    /** Cria um livro de acordo com ad informações obtidas do ficheiro */
    public Livro(String livro) {
        String reg="[|;]";
        String[] campos = livro.split(reg);

        this.titulo = campos[0];
        this.editora = campos[1];
        this.autor = campos[2];
        this.categoria = campos[3];
        this.ISBN = campos[4];
        this.ano_edicao = Integer.parseInt(campos[5]);
    }

    /** Pede ao utilizador as informações sobre o livro a adicionar*/
    public Livro criarLivro(){
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o titulo do livro: ");
        titulo = ler.nextLine();
        System.out.print("Insira a editora do livro: ");
        editora = ler.nextLine();
        System.out.print("Insira o autor(es) do livro: ");
        autor= ler.nextLine();
        System.out.print("Insira o categoria do livro: ");
        categoria = ler.nextLine();
        System.out.print("Insira o ISBN do livro: ");
        ISBN = ler.nextLine();
        System.out.print("Insira o ano de edição do livro: ");
        ano_edicao = ler.nextInt();
        return this;
    }

    /** Retorna uma string apropriada à visualização*/
    public String getLivro(){
        return "Titulo: "+this.titulo+"\nEditora: "+this.editora+"\nAutor: "+this.autor+"\nCategoria: "+this.categoria+"\nISBN: "+this.ISBN+"\nAno de edição: "+this.ano_edicao;
    }

    /** Retorna uma string apropriada à escrita no ficheiro*/
    public String getLivrof(){
        return titulo+"|"+editora+"|"+autor+"|"+categoria+"|"+ISBN+"|"+ano_edicao+";";
    }

    public String getISBN() {
        return ISBN;
    }

    public int getAno_edicao() {
        return ano_edicao;
    }

    public void setTitulo() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o titulo do livro: ");
        this.titulo = ler.nextLine();
    }

    public void setEditora() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira a editora do livro: ");
        this.editora = ler.nextLine();
    }

    public void setAutor() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o(s) autor(es) do livro: ");
        this.autor = ler.nextLine();
    }

    public void setISBN() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o ISBN do livro: ");
        this.ISBN = ler.nextLine();
    }

    public void setCategoria() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira a categoria do livro: ");
        this.categoria = ler.nextLine();
    }

    public void setAno_edicao() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o ano de edição do livro: ");
        this.ano_edicao = ler.nextInt();
    }
}
