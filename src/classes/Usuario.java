package classes;
import java.time.LocalDate;

import conexao.ModuloConexao;

public class Usuario {
    private int codUsuario;
    private String nomeUsuario;
    private String cpfUsuario;
    private LocalDate dataNascimento;
    private double salarioUsuario;
    private int horarioTurno;

    public Usuario(int codUsuario, String nomeUsuario, String cpfUsuario, LocalDate dataNascimento, double salarioUsuario, int horarioTurno) {
        this.codUsuario = codUsuario;
        this.nomeUsuario = nomeUsuario;
        this.cpfUsuario = cpfUsuario;
        this.dataNascimento = dataNascimento;
        this.salarioUsuario = salarioUsuario;
        this.horarioTurno = horarioTurno;
    }
    
    public static void cadastraCliente(ModuloConexao conexao ,Cliente cliente) {
    	
    	
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getSalarioUsuario() {
        return salarioUsuario;
    }

    public void setSalarioUsuario(double salarioUsuario) {
        this.salarioUsuario = salarioUsuario;
    }

    public int getHorarioTurno() {
        return horarioTurno;
    }

    public void setHorarioTurno(int horarioTurno) {
        this.horarioTurno = horarioTurno;
    }
}
