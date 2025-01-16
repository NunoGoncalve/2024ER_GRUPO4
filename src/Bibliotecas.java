import java.io.File;
import java.util.Scanner;

public class Bibliotecas {

    public Bibliotecas() {
        File mydir = new File("Bibliotecas");
        mydir.mkdir();
    }

    public boolean isEmpty(){
        File myfile = new File("Bibliotecas");

        return myfile.list().length == 0;

    }

    public String escolherBibliotecas() {
        Scanner ler = new Scanner(System.in);
        String op;
        int opt;
        File directoryPath = new File("Bibliotecas");
        String[] contents = directoryPath.list();

        System.out.println(" ---- Bibliotecas ---");
        for(int i=0; i<contents.length; i++) {
            System.out.println(i+1+") - "+contents[i]);
        }
        System.out.println(contents.length+1+") - Sair");

        do{
            opt=0;
            System.out.print("Escolha uma biblioteca: ");
            op=ler.next();
            // testar se é inteiro valido
            if(op.matches("\\d") && !op.equals("0")){
                opt = Integer.parseInt(op);
            }

            if(opt==contents.length+1){
                System.out.println("A sair ...");
                opt=contents.length+1;
            } else if (Integer.parseInt(op)>contents.length || opt==0) {
                System.out.println("Opção inválida! Tente novamente");
                opt=0;
            }
        }while(opt==0);
        if(opt!=contents.length+1)  return "Bibliotecas/"+contents[opt-1];
        else return "";
    }

    public void adicionarBiblioteca() {
        Scanner ler = new Scanner(System.in);

        System.out.print("Insira o nome da Biblioteca: ");
        File directoryPath = new File("Bibliotecas/"+ler.nextLine());
        if(directoryPath.mkdir()) System.out.println("Biblioteca criada com sucesso!");

    }

    public void eliminarBiblioteca(String biblioteca) {
        char op;
        Scanner ler = new Scanner(System.in);
        System.out.print("Apagar a biblioteca: "+biblioteca.substring(biblioteca.indexOf("/") + 1)+"? (s/n) ");
        op=ler.next().charAt(0);
        if(op=='s' || op=='S'){
            File directoryPath = new File(biblioteca);
            String[] contents = directoryPath.list();
            for (String s : contents) {
                File currentFile = new File(directoryPath.getPath(), s);
                currentFile.delete();
            }
            if(directoryPath.delete()) System.out.println("Biblioteca "+biblioteca.substring(biblioteca.indexOf("/") + 1)+" eliminada com sucesso!");
        }else{
            System.out.println("A cancelar...");
        }
    }
}
