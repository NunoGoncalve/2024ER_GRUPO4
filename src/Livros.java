import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** Class livros guarda um array de livros */
public class Livros {
    private ArrayList<Livro> livros = new ArrayList<>();

    /** Metodo lerLivros
     * Define a variável n_linhas com o resultado da função contLinhas;
     * Se o n_linhas for diferente de 0 chama a função setLivros com o resultado da função lerFicheiro como parametro*/
    public void lerLivros(String biblioteca) {
        int n_linhas = contLinhas(biblioteca);
        if(n_linhas!=0) setLivros(lerFicheiro(n_linhas, biblioteca));
    }

    /** Metodo adicionarLivro
     * Cria uma variável livro chama a função criarLivro() e adiciona o resultado da mesma ao arraylist */
    public void adicionarLivro(){
        Livro liv = new Livro();
        this.livros.add(liv.criarLivro());
    }

    /** Metodo adicionarLivro
     * @param liv adiciona o parametro recebido ao arraylist */
    public void adicionarLivro(Livro liv){
        this.livros.add(liv);
    }

    /** Metodo listarLivros
     * Para cada livro dentro do arraylist é chamada a função formataLivroE*/
    public void listarLivros(){
        for (Livro liv : this.livros) {
            liv.formataLivroE();
        }
        System.out.println("--------------- Fim ---------------");
    }

    /** Metodo listarLivrosLivres
     * Para cada livro marcado como livre dentro do arraylist é chamada a função formataLivroE */
    public void listarLivrosLivres(){
        for (Livro liv : this.livros) {
            if(liv.getLivre())liv.formataLivroE();
        }
        System.out.println("--------------- Fim ---------------");
    }

    /** Metodo listarLivrosUsado
     * Para cada livro marcado como usado dentro do arraylist é chamada a função formataLivroE */
    public void listarLivrosUsado(){
        for (Livro liv : this.livros) {
            if(!liv.getLivre())liv.formataLivroE();
        }
        System.out.println("--------------- Fim ---------------");
    }

    /** Metodo menuAtualizar
     * @param liv e apresenta o menu de atualização da mesma, chamando a função associada á escolha do utilizador*/
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
        ler.skip("\n");
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
                System.out.print("Insira o(s)/a(s) autor(es) do livro: ");
                liv.setAutor(ler.nextLine());
                break;
            case 4:
                System.out.print("Insira a categoria do livro: ");
                liv.setCategoria(ler.nextLine());
                break;
            case 5:
                liv.setISBN(pedeIsbn());
                break;
            case 6:
                String ano;
                do{
                    System.out.print("Insira o ano de edição do livro: ");
                    ano = ler.next();
                    if (ano.matches("\\d{4}")) {
                        liv.setAnoEdicao(Integer.parseInt(ano));
                        break;
                    }
                    else System.out.println("Erro insira um ano válido!");
                }while(true);

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

    /** Metodo pedeIsbn
     * Pede o ISBN ao utilizador, verifica se este encontra-se com o formato correto e retorna-o */
    public String pedeIsbn(){
        String isbn;
        Scanner ler = new Scanner(System.in);
        Livro liv = new Livro();
        do{
            System.out.print("Insira o ISBN do livro: ");
            isbn=ler.nextLine();
        }while(!liv.verificaIsbn(isbn));
        return isbn;
    }

    /** Cria uma variável Livro e atribui-lhe o resultado da função de procura,
     * se o livro não estiver vazio lista-o e chama o menu atualizar enviando o livro */
    public void atualizarLivro(){
        Livro liv = procuraLivro(pedeIsbn());
        if(!liv.isEmpty()){ listaLivro(liv); menuAtualizar(liv);}
    }

    /** Verifica se o array está vazio retorna verdadeiro ou falso conforme */
    public boolean isEmpty(){
        return livros.isEmpty();
    }

    /** Recebe uma variável livro, verifica se a mesma não está vazia e imprime-a no ecrã */
    public void listaLivro(Livro liv){
        if(!liv.isEmpty()){
            liv.formataLivroE();
            System.out.println("--------------- Fim ---------------");
            System.out.println();
        }
    }

    /** Metodo procuraLivro
     * @param isbn procura um livro com o parametro recebido
     * @return de um livro vazio caso não encontre o ISBN inserido pelo utilizador*/
    public Livro procuraLivro(String isbn){
        Livro liv_flag = new Livro();
        for (Livro liv : this.livros) {
            if(liv.getISBN().equals(isbn)) return liv;
        }
        System.out.println("ISBN não encontrado! Tente novamente!");
        return liv_flag;
    }

    /** Metodo eliminarLivro
     * @param isbn chama a função procura enviando como parametro o isbn recebido, se o livro for encontrado elimina-o do array */
    public void eliminarLivro(String isbn){
        Livro liv = procuraLivro(isbn);
        if(!liv.isEmpty()) {
            liv.formataLivroE();
            this.livros.remove(liv);
            System.out.println();
            System.out.println("O livro foi removido com sucesso!");
        }
    }

    /** Metodo guardarLivros
     * Guarda o conteúdo do array no ficheiro verificando se é a primeira linha ou as seguintes */
    public void guardarLivros(String biblioteca){
        try {
            FileWriter writer = new FileWriter(biblioteca+"/livros.txt");
            for (Livro livro : this.livros) {
                if(this.livros.getFirst()==livro) writer.write(livro.formataLivroF());
                else writer.write("\n"+livro.formataLivroF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Metodo limparLivros
     *  Limpa o array livros para que este seja detetado pelo sistema de recolha de "lixo" do java */
    public void limparLivros(){
        this.livros.clear();
    }

    /** Metodo lerFicheiro
     * Recebe o número de linhas que o ficheiro têm e cria um array de strings desse tamanho, a seguir
     * lê as linhas do ficheiro e guarda-as num array retornando o mesmo */
    private String[] lerFicheiro(int n_linhas, String biblioteca){
        int i=0;
        String[] livros = new String[n_linhas];

        File myfile = new File(biblioteca+"/livros.txt");
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

    /** Metodo setLivros
     * Para cada string no array livros cria um livro e adiciona-o ao array conforme a informação encontrada no array */
    private void setLivros(String[] livros) {
        for (String s : livros) {
            Livro livro = new Livro(s);
            this.livros.add(livro);
        }
    }

    /** Metodo contLinhas
     * Cria o ficheiro caso este não exista e retorna o número de linhas a 0.
     * Caso o ficheiro exista conta as linhas presentes no ficheiro
     * @return i número de linhas*/
    private int contLinhas(String biblioteca){
        int i=0;
        File myfile = new File(biblioteca+"/livros.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader(biblioteca+"/livros.txt"))) {
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