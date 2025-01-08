import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** Class livros guarda um array de livros */
public class Livros {
    private ArrayList<Livro> livros = new ArrayList<>();

    /** Chama a função ler_ficheiro e guarda as linhas lidas num array e envia-as para a função setLivros*/
    public void ler_livros() {
        int n_linhas = contLinhas();
        if(n_linhas!=0) setLivros(lerFicheiro(n_linhas));
    }

    /** Cria um livro adiciona-o ao array livros e chama a função write_livro*/
    public void adicionarLivro(){
        Livro liv = new Livro();
        this.livros.add(liv.criarLivro());
    }

    public void adicionarLivro(Livro liv){
        this.livros.add(liv);
    }

    /** Verifica se o ficheiro está vazio, se não estiver imprime os livros que estão no array*/
    public void listarLivros(){
        if(this.livros.isEmpty()){
            System.out.println("Ficheiro vazio! Adicione um livro");
        }else {
            for (Livro liv : this.livros) {
                System.out.println("--------------- Livro ---------------");
                System.out.println(liv.formataLivroE());
            }
            System.out.println("--------------- Fim ---------------");
        }
    }

    private void menuAtualizar(Livro liv){
        Scanner ler = new Scanner(System.in);
        int op;
        System.out.println("--------------- Atualizar Livro ---------------");
        System.out.println("1) Titulo");
        System.out.println("2) Editora");
        System.out.println("3) Autor");
        System.out.println("4) Categoria");
        System.out.println("5) ISBN");
        System.out.println("6) Ano de edição");
        System.out.println("7) Todos");
        System.out.println("8) Cancelar");
        System.out.print("Selecione uma opção: ");
        op = ler.nextInt();
        switch (op){
            case 1:
                System.out.print("Insira o titulo do livro: ");
                liv.setTitulo(ler.nextLine());
                break;
            case 2:
                System.out.print("Insira a editora do livro: ");
                liv.setEditora(ler.nextLine());
                break;
            case 3:
                System.out.print("Insira o(s) autor(es) do livro: ");
                liv.setAutor(ler.nextLine());
                break;
            case 4:
                System.out.print("Insira a categoria do livro: ");
                liv.setCategoria(ler.nextLine());
                break;
            case 5:
                System.out.print("Insira o ISBN do livro: ");
                liv.setISBN(ler.nextLine());
                break;
            case 6:
                System.out.print("Insira o ano de edição do livro: ");
                liv.setAno_edicao(ler.nextInt());
                break;
            case 7:
                this.livros.set(this.livros.indexOf(liv),liv.criarLivro());
                break;
            case 8:
                System.out.println("A cancelar...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private String pedeIsbn(){
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o ISBN do livro: ");
        return ler.nextLine();
    }

    /** Verifica se o livro retornado tem valores, caso tenha chama o menu atualizar*/
    public void atualizarLivro(){
        Livro liv = procuraLivro(pedeIsbn());
        if(liv.getAno_edicao()!=-1) menuAtualizar(liv);
    }

    /** Verifica se o livro retornado tem valores, caso tenha imprime-o no ecrã */
    public void listaLivro(){
        Livro liv = procuraLivro(pedeIsbn());
        if(liv.getAno_edicao()!=-1){
            System.out.println("--------------- Livro ---------------");
            System.out.println(liv.formataLivroE());
            System.out.println("--------------- Fim ---------------");
        }
    }

    /** Procura o livro no array, caso não encontre retorna um livro vazio, caso encontre retorna o livro que encontrou*/
    public Livro procuraLivro(String isbn){
        Livro liv_flag = new Livro(-1);
        if(this.livros.isEmpty()){
            System.out.println("Ficheiro vazio! Adicione um livro");
        }else{
            for (Livro liv : this.livros) {
                if(liv.getISBN().equals(isbn)) return liv;
            }
            System.out.println("ISBN não encontrado! Tente novamente!");
        }
        return liv_flag;
    }

    /** Verifica se o ficheiro está vazio, se não estiver procura o ISBN no array elimina-o, e chama a função reescrever_livros */
    public void eliminarLivro(){
        if(this.livros.isEmpty()){
            System.out.println("Ficheiro vazio! Adicione um livro");
        }else{
            String isbn = pedeIsbn();
            if(this.livros.removeIf(liv -> liv.getISBN().equals(isbn))) System.out.println("O livro foi removido com sucesso!");
            else System.out.println("Livro não encontrado!");
        }
    }

    /** Escreve no ficheiro*/
    public void guardarLivros(){
        try {
            FileWriter writer = new FileWriter("livros.txt");
            for (Livro livro : this.livros) {
                if(this.livros.getFirst()==livro) writer.write(livro.formataLivroF());
                else writer.write("\n"+livro.formataLivroF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void limparLivros(){
        this.livros.clear();
    }

    /** Lê as linhas do ficheiro e guarda-as num array */
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

    /** Cria os livros e adiciona-os ao array conforme a informação no ficheiro */
    private void setLivros(String[] livros) {
        for (String s : livros) {
            Livro livro = new Livro(s);
            this.livros.add(livro);
        }
    }

    /** Conta as linhas presentes no ficheiro */
    private int contLinhas(){
        int i=0;
        File myfile = new File("livros.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader("livros.txt"))) {
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
