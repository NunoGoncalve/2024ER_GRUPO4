import java.util.Scanner;

public class Utente {

        // Atributos - Caracteristicas
        private String nome, genero;
        private int Nif, telemovel;


        //adicionar utente
        // Metodo - Função
        public Utente criarUtente() {
            Scanner ler = new Scanner(System.in);

            do {  //Restrição do nome para apenas letras
                System.out.println("Nome: ");
                this.nome = ler.nextLine();

                if (this.nome.matches("[a-zA-ZáéíóúÁÉÍÓÚçÇãõÃÕ\\\\s]+")){
                    break;
                }
                else {
                    System.out.println("ATENÇÃO :  O nome deve conter apenas caracteres alfabéticos!");
                }
            } while (true);

            do {  //validação de Género
                System.out.println("Género (Masculino/Feminino): ");
                this.genero = ler.nextLine();
                if(this.genero.equalsIgnoreCase("Masculino") || this.genero.equalsIgnoreCase("Feminino") ||
                    this.genero.equalsIgnoreCase("masculino") || this.genero.equalsIgnoreCase("feminino")){
                    break;
                } else {
                    System.out.println("Género inválido!");
                    System.out.println("Escolha entre as opções apresentadas");}
            } while (true);


            do { // validaçãodo NIF com 9 digitos
                System.out.println("NIF (9 Dígitos): ");
                this.Nif = ler.nextInt();
                if (this.Nif >= 100000000 && this.Nif <= 999999999) {
                    break;
                } else {
                    System.out.println("O NIF deve conter 9 dígitos!");
                }
            }while (true);

            do {
                System.out.println("Telemóvel: ");
                this.telemovel = ler.nextInt();
                if (this.telemovel >= 100000000 && this.telemovel <= 999999999) {
                    break;
                } else {
                    System.out.println("O número de Telemóvel deve conter 9 dígitos!");
                }
            }while (true);

            return this;
        }

        // Metodo
        public void eliminarUtente(){
            System.out.println("Insira o NIF:  " + Nif);
        }
        // Metodo
        public void atualizarUtente(){
            System.out.println("Insira o NIF:  " + Nif);
        }
        // Metodo
        public void listarUtentes(){
            System.out.println("NIF´s Registrados:  ");
        }

        // Para a função MOSTRAR
        public String getUtente() {
            return "Nome: "+this.nome
                    +"\nGénero: "+this.genero
                    +"\nNIF: "+this.Nif
                    +"\nContacto: "+this.telemovel;
        }

        // Redefinir o Nome na função ATUALIZAR
        public void setNome() {
            Scanner ler = new Scanner(System.in);
            do {
                System.out.println("Insira o novo nome do Utente");
                this.nome = ler.nextLine();

                if (this.nome.matches("[a-zA-ZáéíóúÁÉÍÓÚçÇãõÃÕ\\\\s]+")){  // Apenas letras e espaços
                    break;
                }
                else {
                    System.out.println("ATENÇÃO: O nome deve conter apenas caracteres alfabéticos!");
                }
            } while (true);

        }
        // Redefinir o Genero na função ATUALIZAR
        public void setGenero() {
            Scanner ler = new Scanner(System.in);
            do {  //validação de Género
                System.out.println("Insira o novo género do Utente (Masculino/Feminino): ");
                this.genero = ler.nextLine();
                if(this.genero.equalsIgnoreCase("Masculino") || this.genero.equalsIgnoreCase("Feminino") ||
                        this.genero.equalsIgnoreCase("masculino") || this.genero.equalsIgnoreCase("feminino")){
                    break;
                } else {
                    System.out.println("Género inválido!");
                    System.out.println("Escolha entre as opções apresentadas");}
            } while (true);

        }
        // Redefinir o NIF na função ATUALIZAR
        public void setNif() {
            Scanner ler = new Scanner(System.in);
            do { // validaçãodo NIF com 9 digitos
                System.out.println("NIF (9 Dígitos): ");
                this.Nif = ler.nextInt();
                if (this.Nif >= 100000000 && this.Nif <= 999999999) {
                    break;
                } else {
                    System.out.println("O NIF deve conter 9 dígitos!");
                }
            }while (true);
        }
        // Redefinir o Telemovel na função ATUALIZAR
        public void setTelemovel() {
            Scanner ler = new Scanner(System.in);
            do{
                System.out.println("Telemóvel: ");
                this.telemovel = ler.nextInt();
                if (this.telemovel >= 100000000 && this.telemovel <= 999999999) {
                    break;
                } else {
                    System.out.println("O número de Telemóvel deve conter 9 dígitos!");
                }
            }while (true);
        }

        public int getNif(){
            return this.Nif;
        }

}
