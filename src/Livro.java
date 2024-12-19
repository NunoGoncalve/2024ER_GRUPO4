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
    private String titulo, editora, autor, categoria, ISBN, ano_edicao;

    /** Pede ao utilizador as informações sobre o livro a adicionar*/
    public Livro add_livro(){
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
        System.out.print("Insira o ano do livro: ");
        ano_edicao = ler.nextLine();
        return this;
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
        this.ano_edicao = campos[5];
    }

    /** Retorna uma string apropriada à visualização*/
    public String get_livro(){
        return "Titulo: "+this.titulo+"\nEditora: "+this.editora+"\nAutor: "+this.autor+"\nCategoria: "+this.categoria+"\nISBN: "+this.ISBN+"\nAno de edição: "+this.ano_edicao;
    }

    /** Retorna uma string apropriada à escrita no ficheiro*/
    public String get_livrof(){
        return titulo+"|"+editora+"|"+autor+"|"+categoria+"|"+ISBN+"|"+ano_edicao+";";
    }

    public String getISBN() {
        return ISBN;
    }

    public Livro() {
        titulo = "";
        editora = "";
        autor = "";
        categoria = "";
        ano_edicao = "";
    }
}
