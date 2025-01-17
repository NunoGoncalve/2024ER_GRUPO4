import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** Class livros guarda um array de livros */
public class Livros {
    private ArrayList<Livro> livros = new ArrayList<>();

    /** Metodo lerLivros
     * Define a variável nLinhas com o resultado da função contLinhas;
     * Se o nLinhas for diferente de 0 chama a função setLivros com o resultado da função lerFicheiro como parametro*/
    public void lerLivros() {
        int nLinhas = contLinhas();
        if(nLinhas!=0) setLivros(lerFicheiro(nLinhas));
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    /** Metodo adicionarLivro
     * Cria uma variável livro chama a função criarLivro() e adiciona o resultado da mesma ao arraylist */
    public void adicionarLivro(){
        Livro liv = new Livro();
        this.livros.add(liv.criarLivro());
    }
    public int size(){
        return livros.size();
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
        System.out.println();
    }

    /** Metodo listarLivrosLivres
     * Para cada livro marcado como livre dentro do arraylist é chamada a função formataLivroE */
    public void listarLivrosLivres(){
        for (Livro liv : this.livros) {
            if(liv.getLivre()){
                liv.formataLivroE();
            }
        }
        System.out.println("--------------- Fim ---------------");
    }

    public int contLivrosLivres(){
        int numLivres = 0;
        for (Livro liv : this.livros) {
            if(liv.getLivre()){
                numLivres++;
            }
        }
        return numLivres;
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
        System.out.println();
        System.out.println("--------------- Atualizar livro ---------------");
        System.out.println("1)  Titulo");
        System.out.println("2)  Editora");
        System.out.println("3)  Autor");
        System.out.println("4)  Categoria");
        System.out.println("5)  ISBN");
        System.out.println("6)  Ano de edição");
        System.out.println("7)  Todos");
        System.out.println("8)  Cancelar");
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
        boolean flag=false;
        do{
            System.out.print("Insira o ISBN do livro: ");
            isbn=ler.nextLine();
            if(liv.verificaIsbn(isbn)) flag=true;
            else System.out.println("Formatação errada! Por favor insira um ISBN válido (ex:)");
        }while(!flag);
        return isbn;
    }

    /** Cria uma variável Livro e atribui-lhe o resultado da função de procura,
     * se o livro não estiver vazio lista-o e chama o menu atualizar enviando o livro */
    public void atualizarLivro(){
        Livro liv = procuraLivro(pedeIsbn());
        if(!liv.isEmpty()){ listaLivro(liv); menuAtualizar(liv);}
        else System.out.println("ISBN não encontrado! Tente novamente!");
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
        return liv_flag;
    }

    /** Metodo eliminarLivro
     * @param isbn chama a função procura enviando como parametro o isbn recebido, se o livro for encontrado elimina-o do array */
        public boolean eliminarLivro(String isbn){
            Livro liv = procuraLivro(isbn);
            if(!liv.isEmpty()) {
                liv.formataLivroE();
                this.livros.remove(liv);
                System.out.println();
                System.out.println("O livro foi removido com sucesso!");
                return true;
            }else{
                System.out.println("ISBN não encontrado! Tente novamente!");
                return false;
            }
        }



    /** Metodo guardarLivros
     * Guarda o conteúdo do array no ficheiro verificando se é a primeira linha ou as seguintes */
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

    /** Metodo limparLivros
     *  Limpa o array livros para que este seja detetado pelo sistema de recolha de "lixo" do java */
    public void limparLivros(){
        this.livros.clear();
    }

    /** Metodo lerFicheiro
     * @param nLinhas  recebe como parametro o número de linhas que o ficheiro têm e cria um array de strings desse tamanho,
     * de seguida lê as linhas do ficheiro e guarda-as num array retornando o mesmo */
    private String[] lerFicheiro(int nLinhas){
        int i=0;
        String[] livros = new String[nLinhas];

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

    /** Metodo setLivros
     * @param livros recebe como parametro um array de strings
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
