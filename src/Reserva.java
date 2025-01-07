import java.util.Date;
import java.util.Calendar;

public class Reserva {
    private int numeroReserva;
    private Date dataRegisto;
    private Date dataInicio;
    private Date dataFim;
    private String nifUtente;
    private String tituloLivro;  // Agora guardamos o título do livro

    // Construtor da reserva
    public Reserva(int numeroReserva, Date dataRegisto, Date dataInicio, String nifUtente, String tituloLivro) {
        this.numeroReserva = numeroReserva;
        this.dataRegisto = dataRegisto;
        this.dataInicio = dataInicio;
        this.nifUtente = nifUtente;
        this.tituloLivro = tituloLivro;
        this.dataFim = calcularDataFim(dataInicio);  // Calcula a data de fim no construtor
    }

    // Método para calcular a data de fim (15 dias após a data de início)
    private Date calcularDataFim(Date dataInicio) {
        Calendar calendar = Calendar.getInstance();  // Cria uma instância de Calendar
        calendar.setTime(dataInicio);  // Define a data de início no calendário
        calendar.add(Calendar.DAY_OF_MONTH, 15);  // Adiciona 15 dias à data de início
        return calendar.getTime();  // Retorna a nova data de devolução
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
