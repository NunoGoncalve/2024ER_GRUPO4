import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Reserva {
    private int numeroReserva;
    private Date dataRegisto;
    private Date dataInicio;
    private Date dataFim;
    private String nifUtente;

    // Construtor da reserva
    public Reserva(int numeroReserva, Date dataRegisto, Date dataInicio, String nifUtente) {
        this.numeroReserva = numeroReserva;
        this.dataRegisto = dataRegisto;
        this.dataInicio = dataInicio;
        this.nifUtente = nifUtente;

        // Calcular a data de fim automaticamente (15 dias após a data de início)
        this.dataFim = calcularDataFim(dataInicio);
    }

    // Método para calcular a data de fim (15 dias após a data de início)
    private Date calcularDataFim(Date dataInicio) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataInicio);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        return calendar.getTime();
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

    // Exibir detalhes de uma reserva
    public void exibirDetalhes(boolean anonimo) {
        System.out.println("Número da Reserva: " + numeroReserva);
        System.out.println("Data de Registo: " + dataRegisto);
        System.out.println("Data de Início: " + dataInicio);
        System.out.println("Data de Fim: " + dataFim);
        if (!anonimo) {
            System.out.println("NIF do Utente: " + nifUtente);
        }
    }
}

