import java.util.ArrayList;
import java.util.Scanner;

// Classe Primária
public class Jornal_revista {
    String titulo;
    String issn;
    String editora;
    int dataPublicacao;
    String categoria;

    // Construtor
    public Jornal_revista(String titulo, String issn, String editora, int dataPublicacao, String categoria) {
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

    public int getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(int dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static boolean validarISSN(String issn) {
        // Verificar se segue o formato "XXXX-XXXX"
        if (issn == null || !issn.matches("\\d{4}-\\d{3}[\\dX]")) {
            return false;
        }

        // Verificar se os 7 primeiros caracteres são números
        for (int i = 0; i < 7; i++) {
            if (!Character.isDigit(issn.charAt(i)) && i != 4) { // Ignorar o hífen na posição 4
                return false;
            }
        }

        // Verificar se o último caractere é um número ou 'X'
        char lastChar = issn.charAt(8); // Última posição do ISSN
        return Character.isDigit(lastChar) || lastChar == 'X';
    }

    // Método para exibir informações
    @Override
    public String toString() {
        return "Jornal/Revista: " + titulo + "\n" +
                "ISSN: " + issn + "\n" +
                "Editora: " + editora + "\n" +
                "Data da publicação: " + dataPublicacao + "\n" +
                "Categoria: " + categoria + "\n";
    }

    // Classe onde apresenta o MENU
    public void Menu() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jornal_revista> listaJornais = new ArrayList<>();
        int escolha;

        do {
            System.out.println("1)Adicionar Jornal/Revista");
            System.out.println("2)Listar Jornais/Revistas");
            System.out.println("3)Pesquisar por ISSN");
            System.out.println("4)Remover por ISSN");
            System.out.println("5)Sair");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next(); // Descarta a entrada inválida
            }

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    // Entrada de dados do usuário
                    scanner.nextLine(); // Limpar o buffer
                    System.out.println("Insira o título: ");
                    String titulo = scanner.nextLine(); // Usar nextLine() para capturar strings com espaços

                    System.out.println("Insira o ISSN: ");
                    String issn = scanner.nextLine(); // Usar nextLine() para capturar o ISSN

                    // Valida o ISSN
                    if (!validarISSN(issn)) {
                        System.out.println("ISSN inválido. Tente novamente.");
                        break;
                    }

                    System.out.println("Insira a editora: ");
                    String editora = scanner.nextLine(); // Usar nextLine() para capturar strings

                    System.out.println("Insira a data de publicação: ");
                    int dataPublicacao = scanner.nextInt(); // Usar nextInt() para capturar números inteiros

                    scanner.nextLine(); // Limpar o buffer

                    System.out.println("Insira a categoria: ");
                    String categoria = scanner.nextLine(); // Usar nextLine() para capturar strings

                    // Cria um novo objeto Jornal_revista e adiciona à lista
                    Jornal_revista novoJornal = new Jornal_revista(titulo, issn, editora, dataPublicacao, categoria);
                    listaJornais.add(novoJornal); // Agora estamos adicionando corretamente à lista
                    System.out.println("Jornal/Revista adicionado com sucesso.");
                    break;

                case 2:
                    // Listar Jornais/Revistas
                    if (listaJornais.isEmpty()) {
                        System.out.println("Não há jornais/revistas cadastrados.");
                    } else {
                        for (Jornal_revista jornal : listaJornais) {
                            System.out.println(jornal); // Exibe o conteúdo de cada jornal
                        }
                    }
                    break;

                case 3:
                    // Pesquisar por ISSN
                    scanner.nextLine(); // Limpar o buffer
                    System.out.println("Insira o ISSN para pesquisa: ");
                    String pesquisaIssn = scanner.nextLine();
                    boolean encontrado = false;
                    for (Jornal_revista jornal : listaJornais) {
                        if (jornal.getIssn().equals(pesquisaIssn)) {
                            System.out.println("Jornal/Revista encontrado: ");
                            System.out.println(jornal);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("ISSN não encontrado.");
                    }
                    break;

                case 4:
                    // Remover por ISSN
                    scanner.nextLine(); // Limpar o buffer
                    System.out.println("Insira o ISSN para remoção: ");
                    String issnRemover = scanner.nextLine();
                    boolean removido = false;
                    for (Jornal_revista jornal : listaJornais) {
                        if (jornal.getIssn().equals(issnRemover)) {
                            listaJornais.remove(jornal);
                            System.out.println("Jornal/Revista removido com sucesso.");
                            removido = true;
                            break;
                        }
                    }
                    if (!removido) {
                        System.out.println("ISSN não encontrado para remoção.");
                    }
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

    // Método main para testar a classe
    public static void main(String[] args) {
        // Cria uma instância da classe Jornal_revista para testar
        Jornal_revista jornal = new Jornal_revista("Revista Exemplo", "1234-567X", "Editora Exemplo", 2023, "Tecnologia");

        // Testa a validação de ISSN
        System.out.println("ISSN válido? " + validarISSN(jornal.getIssn()));

        // Testa a exibição dos dados
        System.out.println(jornal);

        // Testa o Menu
        jornal.Menu();
    }
}
