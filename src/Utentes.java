import java.util.ArrayList;
import java.util.Scanner;

public class Utentes {
   private ArrayList<Utente> uts = new ArrayList<>();


   public void adicionarUtente(){
       Utente ut = new Utente();
       this.uts.add(ut.criarUtente());
       System.out.println("Utente adicionado com sucesso!");
       System.out.println("--------------- Fim ---------------");
   }

   public void listarUtentes(){
       for(Utente ut: this.uts){
           System.out.println("--------------- Utente ---------------");
           System.out.println(ut.getUtente());
       }
       System.out.println("--------------- Fim ---------------");
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
        System.out.println("--------------- Fim ---------------");
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
            System.out.println("A retornar ao Menu...");}

        System.out.println("--------------- Fim ---------------");
    }

   public void atualizarUtente(){
       int Nif, escolha;
       Scanner ler = new Scanner(System.in);
       System.out.println("Insira o NIF do Utente");
       Nif = ler.nextInt();
       do {
           System.out.println("--------------- Atualizar Utente ---------------");
           System.out.println("1) Nome");
           System.out.println("2) Género");
           System.out.println("3) NIF");
           System.out.println("4) Contacto");
           System.out.println("5) Todos");
           System.out.println("6) Cancelar");
           System.out.println("Selecione uma opção");
           escolha = ler.nextInt();

           for(Utente ut: this.uts) { //definição do ut
               if (ut.getNif() == Nif) {
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
                           ut.setContacto();
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
               }
           }
       }while (escolha > 6);
   }
}
