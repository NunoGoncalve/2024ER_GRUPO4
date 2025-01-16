import java.util.Date;

public class Reserva {
    private String nif;
    private Date dataInicio;
    private Date dataFim;
    private String isbn;

    // Construtor
    public Reserva(String nif, Date dataInicio, Date dataFim, String isbn) {
        this.nif = nif;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.isbn = isbn;
    }

    // Métodos getters e setters
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
