import java.util.Scanner;

public class Utente {

/**Atributos do Utente
 * @param nome do Utente
 * @param genero do Utente
 * @param Nif do Utente
 * @param telemovel do Utente. */
    private String nome, genero;
    private int Nif, telemovel;

    /**Construtor da classe Utente; Inicializa os atributos a zero/vazio. */
    public Utente(){
        this.nome = "";
        this.genero = "";
        this.Nif = 0;
        this.telemovel = 0;
    }

/**Metodo criarUtente
 *Pede ao utilizador os dados (atributos) e faz a validação dos mesmos;
 *@return retorna o Utente criado. */
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

            if (NIF.matches("\\d{9}")){
                Nif = Integer.parseInt(NIF);
                break;
            } else {
                System.out.println("O NIF deve conter 9 dígitos!");
                System.out.println("Caracteres são inválidos!");
            }
        }while (true);


        String tel;
        do {
            System.out.print("Telemóvel:  ");
            tel = ler.next();
            if (tel.matches("\\d{9}")){
                telemovel = Integer.valueOf(tel);
                break;
            } else {
                System.out.println("O número de Telemóvel deve conter 9 dígitos!");
                System.out.println("Caracteres são inválidos!");
            }
        }while (true);

        return this;
    }

    /**Metodo formataUtenteE
     *@return das informações (atributos) do Utente*/
    public String formataUtenteE() {
        return "Nome: "+this.nome
                +"\nGénero: "+this.genero
                +"\nNIF: "+this.Nif
                +"\nContacto: "+this.telemovel;
    }

    /**Metodo formataUtenteF
     *@return das informações (atributos) do Utente*/
    public String formataUtenteF() {
        return nome+"|" +genero +"|"+Nif +"|"+telemovel+";";
    }

    /**Metodo setNome
     *Pede o nome ao utilizador
     *E verifica se o nome contém apenas letras e espaços. */
    public void setNome() {
        Scanner ler = new Scanner(System.in);
        do {
            System.out.print("Insira o novo nome do Utente:  ");
            this.nome = ler.nextLine();

            if (this.nome.matches("[a-zA-ZáéíóúÁÉÍÓÚçÇãõÃÕ\\\\s]+")){
                break;
            }
            else {
                System.out.println("ATENÇÃO: O nome deve conter apenas caracteres alfabéticos!");
            }
        } while (true);
    }

    /**Metodo setGenero
     *Pede o genero ao utilizador
     *E verifica se o genero é uma das palavras propostas.
     *Se não for emite uma mensagem de erro. */
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

    /**Metodo setNif
     *Pede o NIF ao utilizador
     *E verifica se o Nif tem 9 digitos; sendo apenas inteiros.
     *Se não, emite uma mensagem de erro.*/
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

    /**Metodo setTelemovel
     *Pede o telemovel ao utilizador
     *E verifica se o telemovel tem 9 digitos; sendo apenas inteiros.*/
    public void setTelemovel() {
        Scanner ler = new Scanner(System.in);
        String tel;
        do {
            System.out.print("Telemóvel:  ");
            tel = ler.next();
            if (tel.matches("\\d{9}")){
                telemovel = Integer.valueOf(tel);
                break;
            } else {
                System.out.println("O número de Telemóvel deve conter 9 dígitos!");
                System.out.println("Caracteres são inválidos!");
            }
        }while (true);
    }

    /**Metodo getNif
    @return o NIF do Utente*/
    public int getNif(){
        return this.Nif;
    }

    /**Contrutor Utente
     *@param utente é separado segundo a regra definida.
     *Define os atributos conforme o paramêtro.*/
    public Utente (String utente) {
        String regra="[|;]";
        String[] campos = utente.split(regra);

        this.nome = campos[0];
        this.genero = campos[1];
        this.Nif =  Integer.parseInt(campos[2]);
        this.telemovel =  Integer.parseInt(campos[3]);
    }
}
