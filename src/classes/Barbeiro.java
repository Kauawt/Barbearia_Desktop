package classes;
import java.time.LocalDate;

public class Barbeiro extends Usuario {
    private boolean statusBarbeiro;

    public Barbeiro(int codUsuario, String nomeUsuario, String cpfUsuario, LocalDate dataNascimento, double salarioUsuario, int horarioTurno, boolean statusBarbeiro) {
        super(codUsuario, nomeUsuario, cpfUsuario, dataNascimento, salarioUsuario, horarioTurno);
        this.statusBarbeiro = statusBarbeiro;
    }


    public boolean isStatusBarbeiro() {
        return statusBarbeiro;
    }

    public void setStatusBarbeiro(boolean statusBarbeiro) {
        this.statusBarbeiro = statusBarbeiro;
    }
}
