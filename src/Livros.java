    import java.io.*;
    import java.util.ArrayList;
    import java.util.Scanner;

    /** Class livros guarda um array de livros */
    public class Livros {
        private ArrayList<Livro> livros = new ArrayList<>();

        public ArrayList<Livro> getLivros() {
            return livros;
        }

        /** Chama a função ler_ficheiro e guarda as linhas lidas num array e envia-as para a função setLivros*/
        public void lerLivros() {
            int n_linhas = contLinhas();
            if(n_linhas!=0) setLivros(lerFicheiro(n_linhas));
        }

        public int size(){
            return livros.size();
        }

        /** Cria um livro adiciona-o ao array livros e chama a função write_livro*/
        public void adicionarLivro(){
            Livro liv = new Livro();
            this.livros.add(liv.criarLivro());
        }

        public void adicionarLivro(Livro liv){
            this.livros.add(liv);
        }

        /** Imprime os livros encontrados no array */
        public void listarLivros(){
            for (Livro liv : this.livros) {
                liv.formataLivroE();
            }
            System.out.println("--------------- Fim ---------------");
            System.out.println();
        }

        public void listarLivrosLivres(){
            for (Livro liv : this.livros) {
                if(liv.getLivre())liv.formataLivroE();
            }
            System.out.println("--------------- Fim ---------------");
        }

        public void listarLivrosUsado(){
            for (Livro liv : this.livros) {
                if(!liv.getLivre())liv.formataLivroE();
            }
            System.out.println("--------------- Fim ---------------");
        }

        /** Recebe uma variável Livro e apresenta o menu de atualização da mesma, chamando a função associada á escolha do utilizador*/
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
                            liv.setAno_edicao(Integer.parseInt(ano));
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

        /** Pede o ISBN ao utilizador, verifica se este encontra-se com o formato correto e retorna-o */
        public String pedeIsbn(){
            String isbn;
            Scanner ler = new Scanner(System.in);
            Livro liv = new Livro();
            boolean flag=false;
            do{
                System.out.print("Insira o ISBN do livro: ");
                isbn=ler.nextLine();
                if(liv.verificaIsbn(isbn)) flag=true;
                else System.out.println("Formatação errada! Por favor insira um ISBN válido");
            }while(!flag);
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

        /** Procura o livro no array, caso não encontre retorna um livro vazio, caso encontre retorna o livro que encontrou */
        public Livro procuraLivro(String isbn){
            Livro liv_flag = new Livro();
            for (Livro liv : this.livros) {
                if(liv.getISBN().equals(isbn)) return liv;
            }
            System.out.println("ISBN não encontrado! Tente novamente!");
            return liv_flag;
        }

        /** Recebe a String isbn e procura o livro correspondente, se o livro encontrado não estiver vazio elimina-o do array */
        public boolean eliminarLivro(String isbn){
            Livro liv = procuraLivro(isbn);
            if(!liv.isEmpty()) {
                liv.formataLivroE();
                this.livros.remove(liv);
                System.out.println();
                System.out.println("O livro foi removido com sucesso!");
                return true;
            }
            return false;
        }

        /** Guarda o conteúdo do array no ficheiro verificando se é a primeira linha ou as seguintes */
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

        /** Limpa o array livros para que este seja detetado pelo sistema de recolha de "lixo" do java */
        public void limparLivros(){
            this.livros.clear();
        }

        /** Recebe o número de linhas que o ficheiro têm e cria um array de strings desse tamanho, a seguir
         * lê as linhas do ficheiro e guarda-as num array retornando o mesmo */
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

        /** Cria os livros e adiciona-os ao array conforme a informação encontrada no array de strings livros */
        private void setLivros(String[] livros) {
            for (String s : livros) {
                Livro livro = new Livro(s);
                this.livros.add(livro);
            }
        }

        /** Cria o ficheiro caso este não exista e retorna o número de linhas a 0.
         * Caso o ficheiro exista conta as linhas presentes no ficheiro */
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