import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Utentes {
   private ArrayList<Utente> utentes = new ArrayList<>();


   /**Metodo isEmpty.
    * @return se o ArrayList está vazio.*/
   public boolean isEmpty(){
       return utentes.isEmpty();
   }

   /**Metodo adicionarUtente
    *Cria um novo Utente com os atributos definidos na Classe Utente.
    *Em seguida adiciona o Utente criado ao ArrayList (utentes). */
   public void adicionarUtente(){
       Utente ut = new Utente();
       this.utentes.add(ut.criarUtente());
       System.out.println("Utente adicionado com sucesso!");
       System.out.println("--------------- Fim ---------------\n");
   }

    /** A função abaixo Pesquisa os Utentes que estão no ArrayList (utentes) e Salvos no doc. ".txt"
     * Lista, separadamente, os imprimindo no ecrã com as informações dos Utentes.
     * A busca é feita através do NIF; após o usúario inserir o NIF  a função 'For' vai comparar com os NIF's registrados
     * e guardados no ficheiro ".txt"; após o encontar, imprime no ecrã. */

   public void listarUtentes(){
       for (Utente ut : this.utentes) {
           System.out.println("--------------- Utente ---------------");
           System.out.println(ut.formataUtenteE());
       }
       System.out.println("--------------- Fim ---------------\n");
   }

    /** A função abaixo Pesquisa os Utentes que estão no ArrayList (utentes) e Salvos no doc. ".txt"
     * Imprime e mostra na tela as informações do Utente.
     * A busca é feita através do NIF; após o usúario inserir o NIF  a função 'For' vai comparar com os NIF's registrados
     * e guardados no ficheiro ".txt"; após o encontar, imprime no ecrã e em seguida elimina o Utente do ArrayList e ficheiro ".txt"*/

    public void eliminarUtente(){
        int Nif;
        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o NIF do Utente:  ");
        Nif = ler.nextInt();
        for (Utente ut : this.utentes) { //definição do ut
            if (ut.getNif() == Nif) {
                System.out.println("--------------- Utente encontrado com sucesso! ---------------");
                System.out.println("A eliminar...\n");
                System.out.println(ut.formataUtenteE());
            }
        }
        if (this.utentes.removeIf(ute -> ute.getNif() == Nif)) {
            System.out.println("Utente eliminado com sucesso!");
        } else System.out.println("Utente não encontrado! Verifique o NIF introduzido");
        System.out.println("A retornar ao Menu...");
        System.out.println("--------------- Fim ---------------\n");
   }

    /** A função abaixo Pesquisa os Utentes que estão no ArrayList (utentes) e Salvos no doc. ".txt"
     * Imprime no ecrã as informações do Utente desejado.
     * A busca é feita através do NIF; após o usúario inserir o NIF  a função 'For' vai comparar com os NIF's registrados
     * e guardados no ficheiro ".txt"; após o encontar, imprime no ecrã. */
    public Utente procurarUtente(int nif){
        Utente utFlag = null;
        for (Utente ut : this.utentes) {
            if (ut.getNif() == nif)  return ut;
        }
        return utFlag;
    }

    public void pesquisarUtente(Utente ut){

        //definição do ut
        if (ut!=null) {
            System.out.println("--------------- Utente encontrado com sucesso! ---------------");
            System.out.println(ut.formataUtenteE());
        } else {
            System.out.println("--------------- Utente não encontrado! ---------------");
            System.out.println("Por favor verifique o NIF introduzido");
            System.out.println("A retornar ao Menu...\n");
        }

        System.out.println("--------------- Fim ---------------\n");

    }

    /** A função abaixo Pesquisa os Utentes que estão no ArrayList (utentes) e Salvos no doc. ".txt"
     * Imprime no ecrã as informações do Utente.
     * A busca é feita através do NIF; após o usúario inserir o NIF  a função 'For' vai comparar com os NIF's registrados
     * e guardados no ficheiro ".txt"; após o encontar, impreime no ecrã.
     * Depois é impresso um menu paar saber o campo que o usúario gostaria de alterar.
     * Após a escolha é chamado a Função para a nova definição*/

   public void atualizarUtente(){
       int Nif, escolha;
       Scanner ler = new Scanner(System.in);
       System.out.print("Insira o NIF do Utente:  ");
       Nif = ler.nextInt();

       boolean encontrado= false;

       for(Utente ut: this.utentes) { //definição do ut
           if (ut.getNif() == Nif) {
               System.out.println("--------------- Utente encontrado com sucesso! ---------------");
               System.out.println(ut.formataUtenteE());
               encontrado = true;
           }
       }

       if (!encontrado){  // Só será alterado para satisfazer a condição de true se não achar no loop for.
           System.out.println("--------------- Utente não encontrado! ---------------");
           System.out.println("Por favor verifique o NIF introduzido");
           System.out.println("A retornar ao Menu...\n");}

       for(Utente ut: this.utentes) { //definição do ut
           if (ut.getNif() == Nif) {
               do {
                   System.out.println("--------------- Selecione Campo Para Atualizar ---------------");
                   System.out.println("1)  Nome");
                   System.out.println("2)  Género");
                   System.out.println("3)  NIF");
                   System.out.println("4)  Telemóvel");
                   System.out.println("5)  Todos os campos acima");
                   System.out.println("6)  Cancelar");
                   System.out.print("Selecione uma opção  ");
                   escolha = ler.nextInt();

                   switch (escolha) {
                       case 1:
                           ut.setNome();
                           break;

                       case 2:
                           ut.setGenero();
                           break;

                       case 3:
                           ut.setNif();
                           break;

                       case 4:
                           ut.setTelemovel();
                           break;

                       case 5:
                           this.utentes.set(this.utentes.indexOf(ut), ut.criarUtente());
                           break;

                       case 6:
                           System.out.println("A cancelar...");
                           break;

                       default:
                           System.out.println("Opção Invalida");
                           break;
                   }
               }while (escolha > 6);
           }
       }
   }

    /**Metodo setUtente
     * Guarda os dados de utente em */
    public void setUtente(String[] utentes) {
        for (String s : utentes) {
            Utente ut = new Utente(s);
            this.utentes.add(ut);
        }
    }
    /**Metodo lerUtente
     * Chama a função contLinhas, e atribui o seu resultado a variável nLinhas
     * e de seguida chama o metodo setUtente com a função lerFicheiro como parâmetro.*/

    public void lerUtentes(String biblioteca) {
        int nLinhas=contLinhas(biblioteca);
        setUtente(lerFicheiro(nLinhas, biblioteca));
    }

    /** Metodo lerFicheiro
     * Recebe o número de linhas que o ficheiro têm e cria um array de strings desse tamanho, a seguir
     * lê as linhas do ficheiro e guarda-as num array retornando o mesmo */

    private String[] lerFicheiro(int nLinhas, String biblioteca){
       int i=0;
        String[] uts = new String[nLinhas];
        File myfile = new File(biblioteca+"/utentes.txt");
        try {
            Scanner myReader = new Scanner(myfile);
            while (myReader.hasNextLine()) {
                uts[i]=myReader.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return uts;
    }
    /** Metodo guardarUtentes
     * Guarda o conteúdo do array no ficheiro verificando se é a primeira linha ou as seguintes */

    public void guardarUtentes(String biblioteca){
        try {
            FileWriter writer = new FileWriter(biblioteca+"/utentes.txt");
            for (Utente ut : this.utentes) {
                if(this.utentes.getFirst()== ut) writer.write(ut.formataUtenteF());
                else writer.write("\n"+ ut.formataUtenteF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Metodo contLinhas
     *Cria o ficheiro caso este não exista e retorna o número de linhas a 0.
     * Caso o ficheiro exista conta as linhas presentes no ficheiro
     * @return i número de linhas*/

    private int contLinhas(String biblioteca){
        int i=0;
        File myfile = new File(biblioteca+"/utentes.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader(biblioteca+"/utentes.txt"))) {
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
