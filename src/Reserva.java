import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

public class Reserva {
    private int numeroReserva;
    private Date dataRegisto;
    private Date dataInicio;
    private Date dataFim;
    private String nifUtente;
    private String tituloLivro;  // Agora guardamos o título do livro
    private Livros livrosDisponiveis; // Lista de livros disponíveis

    // Construtor da reserva
    public Reserva(int numeroReserva, Date dataRegisto, Date dataInicio, String nifUtente, String tituloLivro) {
        this.numeroReserva = numeroReserva;
        this.dataRegisto = dataRegisto;
        this.dataInicio = dataInicio;
        this.nifUtente = nifUtente;
        this.tituloLivro = tituloLivro;
        this.dataFim = calcularDataFim(dataInicio);  // Calcula a data de fim no construtor
    }

    public Reserva() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Livros liv = new Livros();
        Date dataInicio = null;
        try {
            dataInicio = sdf.parse("00/00/0000");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.numeroReserva = 0;
        this.dataRegisto = dataInicio;
        this.dataInicio= dataInicio;
        this.dataFim = dataInicio;
        this.livrosDisponiveis = liv;
    }

    // calc data
    private Date calcularDataFim(Date dataInicio) {
        Calendar calendar = Calendar.getInstance();  // Cria uma instância de Calendar
        calendar.setTime(dataInicio);  // Define a data de início no calendário
        calendar.add(Calendar.DAY_OF_MONTH, 15);  // Adiciona 15 dias à data de início
        return calendar.getTime();  // Retorna a nova data de devolução
    }

    public Reserva criarReserva(int numReserva){
        this.livrosDisponiveis.ler_livros();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner ler = new Scanner(System.in);

        // Exibir os livros disponíveis

        System.out.println("-------- Livros disponiveis ----------");
        this.livrosDisponiveis.listarLivros();
        System.out.print("Escolha um livro para a reserva através do isbn: ");
        String isbn = ler.next();
        livrosDisponiveis.adicionarLivro(livrosDisponiveis.procuraLivro(isbn));   // Selecionando o livro escolhido

        System.out.print(" o número da reserva: "+numReserva);
        this.numeroReserva = numReserva;

        System.out.print("Digite o NIF do utente: ");
        this.nifUtente = ler.next();

        Date dataRegisto = new Date();
        System.out.print("Digite a data de início (dd/MM/yyyy): ");
        String inicioStr = ler.next();
        try {
            Date dataInicio = sdf.parse(inicioStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // Exibir a data de devolução
        System.out.println("A data de devolução será: " + sdf.format(this.dataFim));
        return this;

    }

    // Métodos getters
    public int getNumeroReserva() {
        return numeroReserva;
    }

    public Date getDataRegisto() {
        return dataRegisto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public String getNifUtente() {
        return nifUtente;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    // Exibir detalhes de uma reserva
    public void exibirDetalhes(boolean anonimo) {
        System.out.println("Número da Reserva: " + numeroReserva);
        System.out.println("Data de Registo: " + dataRegisto);
        System.out.println("Data de Início: " + dataInicio);
        System.out.println("Data de Fim: " + dataFim);
        if (!anonimo) {
            System.out.println("NIF do Utente: " + nifUtente);
            System.out.println("Livro Reservado: " + tituloLivro);  // Exibe o título do livro
        }
    }
}
