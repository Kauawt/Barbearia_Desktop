package classes;

public class Servico {
    private int codServico;
    private String descricaoServico;
    private double precoServico;
    private double duracaoServico;
    private String nomeServico;
    private boolean statusServico;

    public Servico(int codServico, String descricaoServico, double precoServico, double duracaoServico, String nomeServico, boolean statusServico) {
        this.codServico = codServico;
        this.descricaoServico = descricaoServico;
        this.precoServico = precoServico;
        this.duracaoServico = duracaoServico;
        this.nomeServico = nomeServico;
        this.statusServico = statusServico;
    }


    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    }

    public double getDuracaoServico() {
        return duracaoServico;
    }

    public void setDuracaoServico(double duracaoServico) {
        this.duracaoServico = duracaoServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public boolean isStatusServico() {
        return statusServico;
    }

    public void setStatusServico(boolean statusServico) {
        this.statusServico = statusServico;
    }
}
