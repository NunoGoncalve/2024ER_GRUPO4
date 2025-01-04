import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// Classe representando um Jornal ou Revista
class JornalRevista {
    private String titulo;
    private String issn;
    private String editora;
    private String dataPublicacao; // Alterado para String para suportar o formato dd-MM-yyyy
    private String categoria;

    // Construtor
    public JornalRevista(String titulo, String issn, String editora, String dataPublicacao, String categoria) {
        this.titulo = titulo;
        this.issn = issn;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static boolean validarISSN(String issn) {
        if (issn == null || !issn.matches("\\d{4}-\\d{3}[\\dX]")) {
            return false;
        }
        for (int i = 0; i < 7; i++) {
            if (!Character.isDigit(issn.charAt(i)) && i != 4) {
                return false;
            }
        }
        char lastChar = issn.charAt(8);
        return Character.isDigit(lastChar) || lastChar == 'X';
    }

    public static boolean validarData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Jornal/Revista: " + titulo + "\n" +
                "ISSN: " + issn + "\n" +
                "Editora: " + editora + "\n" +
                "Data da publicação: " + dataPublicacao + "\n" +
                "Categoria: " + categoria + "\n";
    }
}

// Classe Biblioteca para gerenciar jornais e revistas
class Biblioteca {
    private ArrayList<JornalRevista> listaJornais = new ArrayList<>();

    public void adicionarJornalRevista(JornalRevista jornalRevista) {
        listaJornais.add(jornalRevista);
        System.out.println("Jornal/Revista adicionado com sucesso.");
    }

    public void listarJornaisRevistas() {
        if (listaJornais.isEmpty()) {
            System.out.println("Não há jornais/revistas cadastrados.");
        } else {
            for (JornalRevista jornal : listaJornais) {
                System.out.println(jornal);
            }
        }
    }

    public void pesquisarPorISSN(String issn) {
        boolean encontrado = false;
        for (JornalRevista jornal : listaJornais) {
            if (jornal.getIssn().equals(issn)) {
                System.out.println("Jornal/Revista encontrado: ");
                System.out.println(jornal);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("ISSN não encontrado.");
        }
    }

    public void removerPorISSN(String issn) {
        boolean removido = false;
        for (JornalRevista jornal : listaJornais) {
            if (jornal.getIssn().equals(issn)) {
                listaJornais.remove(jornal);
                System.out.println("Jornal/Revista removido com sucesso.");
                removido = true;
                break;
            }
        }
        if (!removido) {
            System.out.println("ISSN não encontrado para remoção.");
        }
    }
}

// Classe Main para testar a aplicação
public class Jornal_revista {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("1) Adicionar Jornal/Revista");
            System.out.println("2) Listar Jornais/Revistas");
            System.out.println("3) Pesquisar por ISSN");
            System.out.println("4) Remover por ISSN");
            System.out.println("5) Sair");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
            }

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Insira o título: ");
                    String titulo = scanner.nextLine();

                    System.out.println("Insira o ISSN: ");
                    String issn = scanner.nextLine();

                    if (!JornalRevista.validarISSN(issn)) {
                        System.out.println("ISSN inválido. Tente novamente.");
                        break;
                    }

                    System.out.println("Insira a editora: ");
                    String editora = scanner.nextLine();

                    System.out.println("Insira a data de publicação (dd-MM-yyyy): ");
                    String dataPublicacao = scanner.nextLine();

                    if (!JornalRevista.validarData(dataPublicacao)) {
                        System.out.println("Data inválida. Tente novamente.");
                        break;
                    }

                    System.out.println("Insira a categoria: ");
                    String categoria = scanner.nextLine();

                    JornalRevista novoJornal = new JornalRevista(titulo, issn, editora, dataPublicacao, categoria);
                    biblioteca.adicionarJornalRevista(novoJornal);
                    break;

                case 2:
                    biblioteca.listarJornaisRevistas();
                    break;

                case 3:
                    scanner.nextLine();
                    System.out.println("Insira o ISSN para pesquisa: ");
                    String pesquisaIssn = scanner.nextLine();
                    biblioteca.pesquisarPorISSN(pesquisaIssn);
                    break;

                case 4:
                    scanner.nextLine();
                    System.out.println("Insira o ISSN para remoção: ");
                    String issnRemover = scanner.nextLine();
                    biblioteca.removerPorISSN(issnRemover);
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 5);

        scanner.close();
    }
}
