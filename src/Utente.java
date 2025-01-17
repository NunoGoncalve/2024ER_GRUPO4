import java.util.Scanner;

public class Utente {

    // Atributos - Caracteristicas
    private String nome, genero;
    private int Nif, telemovel;

    public Utente(){
        this.nome = "";
        this.genero = "";
        this.Nif = 0;
        this.telemovel = 0;
    }

    //adicionar utente
    // Metodo - Função
    public Utente criarUtente() {
        Scanner ler = new Scanner(System.in);

        do {  //Restrição do nome para apenas letras
            System.out.print("Nome:  ");
            this.nome = ler.nextLine();

            if (this.nome.matches("[a-zA-ZáéíóúÁÉÍÓÚçÇãõÃÕ\\\\s]+")){
                break;
            }
            else {
                System.out.println("ATENÇÃO: O nome deve conter apenas caracteres alfabéticos!");
            }
        } while (true);

        do {  //validação de Género
            System.out.print("Género (Masculino/Feminino):  ");
            this.genero = ler.nextLine();
            if(this.genero.equalsIgnoreCase("Masculino") || this.genero.equalsIgnoreCase("Feminino")){
                break;
            } else {
                System.out.println("Género inválido!");
                System.out.println("Escolha entre as opções apresentadas");}
        } while (true);


        String NIF;
        do { // validaçãodo NIF com 9 digitos
            System.out.print("NIF (9 Dígitos):  ");
            NIF = ler.next();
        }while (verificaNif(NIF));


        String tel;
        do {
            System.out.print("Telemóvel:  ");
            tel = ler.next();
            if (tel.matches("\\d{9}")){
                telemovel = Integer.parseInt(tel);
                break;
            } else {
                System.out.println("O número de Telemóvel deve conter 9 dígitos!");
                System.out.println("Caracteres são inválidos!");
            }
        }while (true);

        return this;
    }

    public boolean verificaNif(String nif) {
        if (nif.matches("\\d{9}")){
            Nif = Integer.parseInt(nif);
            return true;
        } else {
            System.out.println("O NIF deve conter 9 dígitos!");
            System.out.println("Caracteres são inválidos!");
            return false;
        }
    }

    /** Metodo formataUtenteE
     * Para a função MOSTRAR*/
    public String formataUtenteE() {
        return "Nome: "+this.nome
                +"\nGénero: "+this.genero
                +"\nNIF: "+this.Nif
                +"\nContacto: "+this.telemovel;
    }

    public String formataUtenteF() {
        return nome+"|" +genero +"|"+Nif +"|"+telemovel+";";
    }

    // Redefinir o Nome na função ATUALIZAR
    public void setNome() {
        Scanner ler = new Scanner(System.in);
        do {
            System.out.print("Insira o novo nome do Utente:  ");
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
            System.out.print("Insira o novo género do Utente (Masculino/Feminino):  ");
            this.genero = ler.nextLine();
            if(this.genero.equalsIgnoreCase("Masculino") || this.genero.equalsIgnoreCase("Feminino")){
                break;
            } else {
                System.out.println("Género inválido!");
                System.out.println("Escolha entre as opções apresentadas");}
        } while (true);
    }

    // Redefinir o NIF na função ATUALIZAR
    public void setNif() {
        Scanner ler = new Scanner(System.in);
        String NIF;
        do { // validaçãodo NIF com 9 digitos
            System.out.print("NIF (9 Dígitos):  ");
            NIF = ler.next();

            if (NIF.matches("\\d{9}")){
                Nif = Integer.parseInt(NIF);
                System.out.println("NIF atualizado com sucesso!");
                break;
            } else {
                System.out.println("O NIF deve conter 9 dígitos!");
                System.out.println("Caracteres são inválidos!");
            }
        }while (true);
    }
    // Redefinir o Telemovel na função ATUALIZAR
    public void setTelemovel() {
        Scanner ler = new Scanner(System.in);
        String tel;
        do {
            System.out.print("Telemóvel:  ");
            tel = ler.next();
            if (tel.matches("\\d{9}")){
                telemovel = Integer.parseInt(tel);
                break;
            } else {
                System.out.println("O número de Telemóvel deve conter 9 dígitos!");
                System.out.println("Caracteres são inválidos!");
            }
        }while (true);
    }


    public int getNif(){
        return this.Nif;
    }

    public Utente (String utente) {
        String reg="[|;]";
        String[] campos = utente.split(reg);

        this.nome = campos[0];
        this.genero = campos[1];
        this.Nif =  Integer.parseInt(campos[2]);
        this.telemovel =  Integer.parseInt(campos[3]);
    }
}
