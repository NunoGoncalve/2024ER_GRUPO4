import java.util.Scanner;

public class Utente {

        // Atributos - Caracteristicas
        private String nome, genero;
        private int Nif, contacto;


        //adicionar utente
        // Metodo - Função
        public Utente criarUtente(){
            Scanner ler = new Scanner(System.in);
            System.out.println("Nome: ");
            this.nome = ler.nextLine();
            System.out.println("Genero: ");
            this.genero = ler.nextLine();
            System.out.println("NIF: ");
            this.Nif = ler.nextInt();
            System.out.println("Contacto: ");
            this.contacto = ler.nextInt();
            return this;

            //fazer sistema de guardar valores em outra classe
            // mudar para a classe UTENTES
        }
        // Metodo
        public void eliminarUtente(){
            System.out.println("Insira o NIF:  " + Nif);

            //motor de busca com for até achar o nif
            // eleminar nif
        }
        // Metodo
        public void atualizarUtente(){
            System.out.println("Insira o NIF:  " + Nif);
            // Sistema de atualização com vetores e armazenamento em ficheiros ".txt"
        }
        // Metodo
        public void listarUtentes(){
            System.out.println("NIF´s Registrados:  ");
            //criar lista com outra classe UTENTES
        }



        // Para a função MOSTRAR
        public String getUtente() {
            return "Nome: "+this.nome
                    +"\nGénero: "+this.genero
                    +"\nNIF: "+this.Nif
                    +"\nContacto: "+this.contacto;
        }
        // Redefinir o Nome na função ATUALIZAR
        public void setNome() {
            Scanner ler = new Scanner(System.in);
            System.out.println("Insira o nome do Utente");
            this.nome = ler.nextLine();
        }
        // Redefinir o Nome na função ATUALIZAR
        public void setGenero() {
            Scanner ler = new Scanner(System.in);
            System.out.println("Insira o género do Utente");
            this.genero = ler.nextLine();
        }
        // Redefinir o Nome na função ATUALIZAR
        public void setNif() {
            Scanner ler = new Scanner(System.in);
            System.out.println("Insira o NIF do Utente");
            this.Nif = ler.nextInt();
        }
        // Redefinir o Nome na função ATUALIZAR
        public void setContacto() {
            Scanner ler = new Scanner(System.in);
            System.out.println("Insira o cotacto do Utente");
            this.contacto = ler.nextInt();
        }

        public int getNif(){
            return this.Nif;
        }

}
