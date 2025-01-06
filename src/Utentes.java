import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Utentes {
   private ArrayList<Utente> uts = new ArrayList<>();


   public void adicionarUtente(){
       Utente ut = new Utente();
       this.uts.add(ut.criarUtente());
       System.out.println("Utente adicionado com sucesso!");
       System.out.println("--------------- Fim ---------------\n");
   }

   public void listarUtentes(){
       for(Utente ut: this.uts){
           System.out.println("--------------- Utente ---------------");
           System.out.println(ut.getUtente());
       }
       System.out.println("--------------- Fim ---------------\n");
   }
    public void eliminarUtente(){
        int Nif;
        Scanner ler = new Scanner(System.in);
        System.out.println("Insira o NIF do Utente");
        Nif = ler.nextInt();
        if (this.uts.removeIf(ute -> ute.getNif() == Nif)){
        System.out.println("Utente eliminado com sucesso!");}
        else System.out.println("Utente não encontrado! Verifique o NIF introduzido");
        System.out.println("A retornar ao Menu...");
        System.out.println("--------------- Fim ---------------\n");
   }
    public void pesquisarUtente(){
        int Nif;
        Scanner ler = new Scanner(System.in);
        System.out.println("Insira o NIF do Utente");
        Nif = ler.nextInt();

        boolean encontrado= false;

        for(Utente ut: this.uts) { //definição do ut
            if (ut.getNif() == Nif) {
                System.out.println("--------------- Utente encontrado com sucesso! ---------------");
                System.out.println(ut.getUtente());
                encontrado = true;
            }
        }
        if (!encontrado){  // Só será alterado para satisfazer a condição de true se não achar no loop for.
            System.out.println("--------------- Utente não encontrado! ---------------");
            System.out.println("Por favor verifique o NIF introduzido");
            System.out.println("A retornar ao Menu...\n");}

        System.out.println("--------------- Fim ---------------\n");
    }

   public void atualizarUtente(){
       int Nif, escolha;
       Scanner ler = new Scanner(System.in);
       System.out.println("Insira o NIF do Utente");
       Nif = ler.nextInt();

       boolean encontrado= false;

       for(Utente ut: this.uts) { //definição do ut
           if (ut.getNif() == Nif) {
               System.out.println("--------------- Utente encontrado com sucesso! ---------------");
               System.out.println(ut.getUtente());
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
                   System.out.println("4) Contacto");
                   System.out.println("5) Todos os campos acima");
                   System.out.println("6) Cancelar");
                   System.out.println("Selecione uma opção");
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

    private void setUts(String[] utentes) {
        for (String s : utentes) {
            Livro livro = new Livro(s);
            this.uts.add(utente);
        }
    }

    private String[] lerUtente(int n_linhas){
        int i=0;
        String[] livros = new String[n_linhas];

        File myfile = new File("utentes.txt");
        try {
            Scanner myReader = new Scanner(myfile);
            while (myReader.hasNextLine()) {
                livros[i] = myReader.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return uts;
    }

    public void guardarUtentes(){
        try {
            FileWriter writer = new FileWriter("livros.txt");
            for (Utente utente : this.uts) {
                if(this.uts.getFirst()== Utente) writer.write(Utente.formataUtenteF());
                else writer.write("\n"+ Utente.formataUtenteF());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
