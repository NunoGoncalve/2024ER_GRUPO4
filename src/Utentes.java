import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Utentes {
   private ArrayList<Utente> uts = new ArrayList<>();

   /** A função cria um novo Utente com os atributos definidos na Classe Utente.
    Em seguida adiciona o Utente criado ao ArrayList (uts).*/

   public void adicionarUtente(){
       Utente ut = new Utente();
       this.uts.add(ut.criarUtente());
       System.out.println("Utente adicionado com sucesso!");
       System.out.println("--------------- Fim ---------------\n");
   }

    /** A função abaixo Pesquisa os Utentes que estão no ArrayList (uts) e Salvos no doc. ".txt"
     * Lista, separadamente, os imprimindo no ecrã com as informações dos Utentes.
     * A busca é feita através do NIF; após o usúario inserir o NIF  a função 'For' vai comparar com os NIF's registrados
     * e guardados no ficheiro ".txt"; após o encontar, imprime no ecrã. */

   public void listarUtentes(){
       if (this.uts.isEmpty()) {
           System.out.println("O Ficheiro está vázio");
       }
       else {
           for (Utente ut : this.uts) {
               System.out.println("--------------- Utente ---------------");
               System.out.println(ut.formataUtenteE());
           }
           System.out.println("--------------- Fim ---------------\n");
       }
   }

    /** A função abaixo Pesquisa os Utentes que estão no ArrayList (uts) e Salvos no doc. ".txt"
     * Imprime e mostra na tela as informações do Utente.
     * A busca é feita através do NIF; após o usúario inserir o NIF  a função 'For' vai comparar com os NIF's registrados
     * e guardados no ficheiro ".txt"; após o encontar, imprime no ecrã e em seguida elimina o Utente do ArrayList e ficheiro ".txt"*/

    public void eliminarUtente(){
        if (this.uts.isEmpty()) {
            System.out.println("O Ficheiro está vázio");
        }
        else {
            int Nif;
            Scanner ler = new Scanner(System.in);
            System.out.print("Insira o NIF do Utente:  ");
            Nif = ler.nextInt();
            for (Utente ut : this.uts) { //definição do ut
                if (ut.getNif() == Nif) {
                    System.out.println("--------------- Utente encontrado com sucesso! ---------------");
                    System.out.println("A eliminar...\n");
                    System.out.println(ut.formataUtenteE());
                }
            }
            if (this.uts.removeIf(ute -> ute.getNif() == Nif)) {
                System.out.println("Utente eliminado com sucesso!");
            } else System.out.println("Utente não encontrado! Verifique o NIF introduzido");
            System.out.println("A retornar ao Menu...");
            System.out.println("--------------- Fim ---------------\n");
        }
   }

/** A função abaixo Pesquisa os Utentes que estão no ArrayList (uts) e Salvos no doc. ".txt"
 * Imprime no ecrã as informações do Utente desejado.
 * A busca é feita através do NIF; após o usúario inserir o NIF  a função 'For' vai comparar com os NIF's registrados
 * e guardados no ficheiro ".txt"; após o encontar, imprime no ecrã. */

    public Utente pesquisarUtente(int nif){
        Utente utFlag = new Utente();
        if (this.uts.isEmpty()) {
            System.out.println("O Ficheiro está vázio...");
        }
        else {
            boolean encontrado = false;

            for (Utente ut : this.uts) { //definição do ut
                if (ut.getNif() == nif) {
                    System.out.println("--------------- Utente encontrado com sucesso! ---------------");
                    System.out.println(ut.formataUtenteE());
                    encontrado = true;
                    return ut;
                }
            }
            if (!encontrado) {  // Só será alterado para satisfazer a condição de true se não achar no loop for.
                System.out.println("--------------- Utente não encontrado! ---------------");
                System.out.println("Por favor verifique o NIF introduzido");
                System.out.println("A retornar ao Menu...\n");
            }

            System.out.println("--------------- Fim ---------------\n");

        }
        return utFlag;
    }

    /** A função abaixo Pesquisa os Utentes que estão no ArrayList (uts) e Salvos no doc. ".txt"
     * Imprime no ecrã as informações do Utente.
     * A busca é feita através do NIF; após o usúario inserir o NIF  a função 'For' vai comparar com os NIF's registrados
     * e guardados no ficheiro ".txt"; após o encontar, impreime no ecrã.
     * Depois é impresso um menu paar saber o campo que o usúario gostaria de alterar.
     * Após a escolha é chamado a Função para a nova definição*/

   public void atualizarUtente(){
       if (this.uts.isEmpty()) {
           System.out.println("O Ficheiro está vázio");
       }
       else {
           int Nif, escolha;
           Scanner ler = new Scanner(System.in);
           System.out.print("Insira o NIF do Utente:  ");
           Nif = ler.nextInt();

           boolean encontrado= false;

           for(Utente ut: this.uts) { //definição do ut
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

           for(Utente ut: this.uts) { //definição do ut
               if (ut.getNif() == Nif) {
                   do {
                       System.out.println("--------------- Selecione Campo Para Atualizar ---------------");
                       System.out.println("1) Nome");
                       System.out.println("2) Género");
                       System.out.println("3) NIF");
                       System.out.println("4) Telemóvel");
                       System.out.println("5) Todos os campos acima");
                       System.out.println("6) Cancelar");
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
                               this.uts.set(this.uts.indexOf(ut), ut.criarUtente());
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
   }


    public void setUtente(String[] utentes) {
        for (String s : utentes) {
            Utente ut = new Utente(s);
            this.uts.add(ut);
        }
    }

    public void lerUtentes() {
        int n_linhas=contLinhas();
        setUtente(lerFicheiro(n_linhas));
    }

    private String[] lerFicheiro(int n_linhas){
       int i=0;
        String[] uts = new String[n_linhas];
        File myfile = new File("utentes.txt");
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

    public void guardarUtentes(){
        try {
            FileWriter writer = new FileWriter("utentes.txt");
            for (Utente ut : this.uts) {
                if(this.uts.getFirst()== ut) writer.write(ut.formataUtenteF());
                else writer.write("\n"+ ut.formataUtenteF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int contLinhas(){
        int i=0;
        File myfile = new File("utentes.txt");
        try {
            if (myfile.createNewFile()) return i;
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader("utentes.txt"))) {
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
