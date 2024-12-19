import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** Class livros guarda um array de livros */
public class Livros {
    ArrayList<Livro> livros = new ArrayList<>();

    /** Chama a função ler_ficheiro e guarda as linhas lidas num array e envia-as para a função setLivros*/
    public void ler_livros() {
        int n_linhas = cont_linhas();
        if(n_linhas!=0)  setLivros(ler_ficheiro(n_linhas));
    }

    /** Cria um livro adiciona-o ao array livros e chama a função write_livro*/
    public void adicionar_livro(){
        Livro liv = new Livro();
        this.livros.add(liv.add_livro());
        write_livro();
    }

    /** Verifica se o ficheiro está vazio, se não estiver imprime os livros que estão no array*/
    public void mostrar_livros(){
        if(this.livros.isEmpty()){
            System.out.println("Ficheiro vazio! Adicione um livro");
        }else {
            for (Livro liv : this.livros) {
                System.out.println("--------------- Livro ---------------");
                System.out.println(liv.get_livro());
            }
            System.out.println("--------------- Fim ---------------");
        }
    }

    /** Verifica se o ficheiro está vazio, se não estiver procura o ISBN no array e imprime o livro correspondente */
    public void mostra_livro(){

        if(this.livros.isEmpty()){
            System.out.println("Ficheiro vazio! Adicione um livro");
        }else{
            Scanner ler = new Scanner(System.in);
            System.out.print("Insira o ISBN do livro: ");
            String isbn = ler.nextLine();
            for (Livro liv : this.livros) {
                if (liv.getISBN().equals(isbn)) {
                    System.out.println("--------------- Livro ---------------");
                    System.out.println(liv.get_livro());
                }
            }
            System.out.println("--------------- Fim ---------------");
        }
    }

    /** Escreve no ficheiro*/
    private void write_livro(){
        FileWriter writer;
        int n_linhas = cont_linhas();
        try {
            if(n_linhas!=0) {
                writer = new FileWriter("livros.txt", true);
                writer.write("\n"+this.livros.getLast().get_livrof());
                writer.close();

            }else {
                writer = new FileWriter("livros.txt");
                writer.write(this.livros.getLast().get_livrof());
                writer.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Verifica se o ficheiro está vazio, se não estiver lê as linhas do ficheiro e guarda-as num array */
    private String[] ler_ficheiro(int n_linhas){
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
    private int cont_linhas(){
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
