package classes;
import java.time.LocalDate;

public class Atendimento {
    private int codAtendimento;
    private int codCliente;
    private int codBarbeiro;
    private int codServico;
    private double valorTotalAtendimento;
    private LocalDate dataAtendimento;
    private int duracaoAtendimento;


    public Atendimento(int codAtendimento, int codCliente, int codBarbeiro, int codServico, double valorTotalAtendimento, LocalDate dataAtendimento, int duracaoAtendimento) {
        this.codAtendimento = codAtendimento;
        this.codCliente = codCliente;
        this.codBarbeiro = codBarbeiro;
        this.codServico = codServico;
        this.valorTotalAtendimento = valorTotalAtendimento;
        this.dataAtendimento = dataAtendimento;
        this.duracaoAtendimento = duracaoAtendimento;
    }

    public int getCodAtendimento() {
        return codAtendimento;
    }

    public void setCodAtendimento(int codAtendimento) {
        this.codAtendimento = codAtendimento;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodBarbeiro() {
        return codBarbeiro;
    }

    public void setCodBarbeiro(int codBarbeiro) {
        this.codBarbeiro = codBarbeiro;
    }

    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public double getValorTotalAtendimento() {
        return valorTotalAtendimento;
    }

    public void setValorTotalAtendimento(double valorTotalAtendimento) {
        this.valorTotalAtendimento = valorTotalAtendimento;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public int getDuracaoAtendimento() {
        return duracaoAtendimento;
    }

    public void setDuracaoAtendimento(int duracaoAtendimento) {
        this.duracaoAtendimento = duracaoAtendimento;
    }
}
