import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** Class livros guarda um array de livros */
public class Livros {
    ArrayList<Livro> livros = new ArrayList<>();

    /** Chama a função ler_ficheiro e guarda as linhas lidas num array e envia-as para a função setLivros*/
    public void ler_livros() {
        int n_linhas = contLinhas();
        if(n_linhas!=0)  setLivros(lerFicheiro(n_linhas));
    }

    /** Cria um livro adiciona-o ao array livros e chama a função write_livro*/
    public void adicionarLivro(){
        Livro liv = new Livro();
        this.livros.add(liv.criarLivro());
    }

    /** Verifica se o ficheiro está vazio, se não estiver imprime os livros que estão no array*/
    public void listarLivros(){
        if(this.livros.isEmpty()){
            System.out.println("Ficheiro vazio! Adicione um livro");
        }else {
            for (Livro liv : this.livros) {
                System.out.println("--------------- Livro ---------------");
                System.out.println(liv.getLivro());
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
                liv.setTitulo();
                break;
            case 2:
                liv.setEditora();
                break;
            case 3:
                liv.setAutor();
                break;
            case 4:
                liv.setCategoria();
                break;
            case 5:
                liv.setISBN();
                break;
            case 6:
                liv.setAno_edicao();
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

    /** Verifica se o livro retornado tem valores, caso tenha chama o menu atualizar*/
    public void atualizarLivro(){
        Livro liv = procuraLivro();
        if(liv.getAno_edicao()!=-1) menuAtualizar(liv);
    }

    /** Verifica se o livro retornado tem valores, caso tenha imprime-o no ecrã */
    public void pesquisaLivro(){
        Livro liv = procuraLivro();
        if(liv.getAno_edicao()!=-1){
            System.out.println("--------------- Livro ---------------");
            System.out.println(liv.getLivro());
            System.out.println("--------------- Fim ---------------");
        }
    }

    /** Procura o livro no array, caso não encontre retorna um livro vazio, caso encontre retorna o livro que encontrou*/
    private Livro procuraLivro(){
        Livro liv_flag = new Livro(-1);
        if(this.livros.isEmpty()){
            System.out.println("Ficheiro vazio! Adicione um livro");
        }else{
            Scanner ler = new Scanner(System.in);
            System.out.print("Insira o ISBN do livro: ");
            String isbn = ler.nextLine();
            for (Livro liv : this.livros) {
                if(liv.getISBN().equals(isbn)) {
                    return liv;
                }
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
            Scanner ler = new Scanner(System.in);
            System.out.print("Insira o ISBN do livro: ");
            String isbn = ler.nextLine();
            if(this.livros.removeIf(liv -> liv.getISBN().equals(isbn))) System.out.println("O livro foi removido com sucesso!");
            else System.out.println("Livro não encontrado!");
        }
    }

    /** Escreve no ficheiro*/
    public void guardarLivros(){
        try {
            FileWriter writer = new FileWriter("livros.txt");
            for (Livro livro : this.livros) {
                if(this.livros.getFirst()==livro) writer.write(livro.getLivrof());
                else writer.write("\n"+livro.getLivrof());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Verifica se o ficheiro está vazio, se não estiver lê as linhas do ficheiro e guarda-as num array */
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
