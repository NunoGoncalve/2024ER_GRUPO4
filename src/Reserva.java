import java.util.Date;
import java.util.Calendar;

public class Reserva {
    private int numeroReserva;
    private Date dataRegisto;
    private Date dataInicio;
    private Date dataFim;
    private String nomeUtente; // Mudar para NIF, se necessário

    // Construtor da reserva
    public Reserva(int numeroReserva, Date dataRegisto, Date dataInicio, String nomeUtente) {
        this.numeroReserva = numeroReserva;
        this.dataRegisto = dataRegisto;
        this.dataInicio = dataInicio;
        this.nomeUtente = nomeUtente;

        // Calcular a data de fim automaticamente (15 dias após a data de início)
        this.dataFim = calcularDataFim(dataInicio);
    }

    // Método para calcular a data de fim (15 dias após a data de início)
    private Date calcularDataFim(Date dataInicio) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataInicio);
        calendar.add(Calendar.DAY_OF_MONTH, 15); // Adiciona 15 dias
        return calendar.getTime();
    }

    // Métodos de acesso
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

    public String getNomeUtente() {
        return nomeUtente;
    }

    // Exibir os detalhes da reserva
    public void exibirDetalhes() {
        System.out.println("Número da Reserva: " + numeroReserva);
        System.out.println("Data de Registo: " + dataRegisto);
        System.out.println("Data de Início: " + dataInicio);
        System.out.println("Data de Fim: " + dataFim);
        System.out.println("Nome do Utente: " + nomeUtente);
    }
}
