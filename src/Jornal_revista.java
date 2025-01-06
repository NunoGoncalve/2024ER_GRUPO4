import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

class JornalRevista implements Serializable {
    private String titulo;
    private String issn;
    private String editora;
    private String dataPublicacao;
    private String categoria;

    public JornalRevista(String titulo, String issn, String editora, String dataPublicacao, String categoria) {
        this.titulo = titulo;
        this.issn = issn;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
    }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIssn() { return issn; }

    public void setIssn(String issn) { this.issn = issn; }

    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }
    public String getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(String dataPublicacao) { this.dataPublicacao = dataPublicacao; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public static boolean validarISSN(String issn) {
        return issn != null && issn.matches("\\d{4}-\\d{3}[\\dX]");
    }

    public static boolean validarData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(data);
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

class Biblioteca implements Serializable {
    private String nome;
    private ArrayList<JornalRevista> listaJornais = new ArrayList<>();

    public Biblioteca(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

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
        for (JornalRevista jornal : listaJornais) {
            if (jornal.getIssn().equals(issn)) {
                System.out.println("Jornal/Revista encontrado: ");
                System.out.println(jornal);
                return;
            }
        }
        System.out.println("ISSN não encontrado.");
    }

    public void pesquisarPorTituloOuCategoria(String termo) {
        boolean encontrado = false;
        for (JornalRevista jornal : listaJornais) {
            if (jornal.getTitulo().equalsIgnoreCase(termo) || jornal.getCategoria().equalsIgnoreCase(termo)) {
                System.out.println(jornal);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum jornal/revista encontrado com o termo: " + termo);
        }
    }

    public boolean podeRemover(String issn) {
        return true;
    }

    public void removerPorISSN(String issn) {
        if (!podeRemover(issn)) {
            System.out.println("Não é possível remover. Há dependências associadas.");
            return;
        }
        for (JornalRevista jornal : listaJornais) {
            if (jornal.getIssn().equals(issn)) {
                listaJornais.remove(jornal);
                System.out.println("Jornal/Revista removido com sucesso.");
                return;
            }
        }
        System.out.println("ISSN não encontrado para remoção.");
    }

    public void salvarParaFicheiro(String nomeFicheiro) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFicheiro))) {
            oos.writeObject(this);
            System.out.println("Dados salvos com sucesso no ficheiro: " + nomeFicheiro);
        }
    }

    public static Biblioteca carregarDeFicheiro(String nomeFicheiro) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
            return (Biblioteca) ois.readObject();
        }
    }
}

public class Jornal_revista {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        int escolha;

        do {
            System.out.println("\nMenu:");
            System.out.println("1) Adicionar Jornal/Revista");
            System.out.println("2) Listar Jornais/Revistas");
            System.out.println("3) Pesquisar por ISSN");
            System.out.println("4) Pesquisar por Título ou Categoria");
            System.out.println("5) Remover por ISSN");
            System.out.println("6) Salvar para Ficheiro");
            System.out.println("7) Carregar de Ficheiro");
            System.out.println("8) Sair");
            System.out.print("Escolha: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
            }
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Insira o título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Insira o ISSN: ");
                    String issn = scanner.nextLine();
                    if (!JornalRevista.validarISSN(issn)) {
                        System.out.println("ISSN inválido.");
                        break;
                    }
                    System.out.print("Insira a editora: ");
                    String editora = scanner.nextLine();
                    System.out.print("Insira a data de publicação (dd-MM-yyyy): ");
                    String dataPublicacao = scanner.nextLine();
                    if (!JornalRevista.validarData(dataPublicacao)) {
                        System.out.println("Data inválida.");
                        break;
                    }
                    System.out.print("Insira a categoria: ");
                    String categoria = scanner.nextLine();
                    biblioteca.adicionarJornalRevista(new JornalRevista(titulo, issn, editora, dataPublicacao, categoria));
                    break;
                case 2:
                    biblioteca.listarJornaisRevistas();
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Insira o ISSN: ");
                    String issnPesquisa = scanner.nextLine();
                    biblioteca.pesquisarPorISSN(issnPesquisa);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.print("Insira o título ou categoria: ");
                    String termo = scanner.nextLine();
                    biblioteca.pesquisarPorTituloOuCategoria(termo);
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.print("Insira o ISSN para remover: ");
                    String issnRemover = scanner.nextLine();
                    biblioteca.removerPorISSN(issnRemover);
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.print("Insira o nome do ficheiro para salvar: ");
                    String nomeFicheiro = scanner.nextLine();
                    try {
                        biblioteca.salvarParaFicheiro(nomeFicheiro);
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar no ficheiro.");
                    }
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.print("Insira o nome do ficheiro para carregar: ");
                    String nomeFicheiroCarregar = scanner.nextLine();
                    try {
                        biblioteca = Biblioteca.carregarDeFicheiro(nomeFicheiroCarregar);
                        System.out.println("Dados carregados com sucesso.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao carregar do ficheiro.");
                    }
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolha != 8);

        scanner.close();
    }
}
