package controller.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Classe utilitária para validação de dados comuns, como CPF, status, e-mail e data de nascimento.
 * Contém métodos estáticos para validar cada tipo de dado de acordo com regras específicas.
 */
public class ValidadorHelper {
	/**
     * Valida um CPF válido, com diversos métodos padrões para não inserirmos CPFs fictícios.
     *
     * @param cpf O CPF a ser validado.
     * @return true se o CPF for válido, false caso contrário.
     */
	public static boolean validadorCpf(String cpf) {
		// Remove caracteres não numéricos do CPF
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verifica se o CPF tem 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}

		// Verifica se todos os dígitos são iguais
		if (cpf.matches("(\\d)\\1{10}")) {
			return false;
		}

		// Calcula e verifica o primeiro dígito verificador
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}
		int resto = soma % 11;
		int digito1 = (resto < 2) ? 0 : (11 - resto);
		if (digito1 != Character.getNumericValue(cpf.charAt(9))) {
			return false;
		}

		// Calcula e verifica o segundo dígito verificador
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}
		resto = soma % 11;
		int digito2 = (resto < 2) ? 0 : (11 - resto);
		if (digito2 != Character.getNumericValue(cpf.charAt(10))) {
			return false;
		}

		return true; // CPF válido
	}
	/**
     * Valida o status fornecido.
     *
     * @param status O status a ser validado.
     * @return true se o status for "Ativo", false caso contrário.
     */
	public static boolean validadorStatus(String status) {
		if (status.equals("Ativo")) {
			return true;
		} else {
			return false;
		}
	}
	/**
     * Valida um endereço de email baseado em domínios específicos.
     *
     * @param email O email a ser validado.
     * @return true se o email for válido conforme os domínios especificados, false caso contrário.
     */
	
	public static boolean validadorEmail(String email) {
        // Expressão regular para validar os domínios especificados
        String regex = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|hotmail\\.com|outlook\\.com|yahoo\\.com|terra\\.com|icloud\\.com)$";

        // Compilação do padrão de expressão regular
        Pattern pattern = Pattern.compile(regex);

        // Corresponde a expressão regular ao endereço de e-mail fornecido
        Matcher matcher = pattern.matcher(email);

        // Retorna verdadeiro se a correspondência for encontrada, falso caso contrário
        return matcher.matches();
    }
	/**
     * Valida a data de nascimento para verificar se o usuário tem entre 18 e 100 anos.
     *
     * @param dataNascimento A data de nascimento a ser validada no formato "dd/MM/yyyy".
     * @return true se a data de nascimento for válida e o usuário tiver entre 18 e 100 anos, false caso contrário.
     */
	public static boolean validadorDataNascimento(String dataNascimento) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Configura como padrão ("dd/MM/yyyy")
            LocalDate nascimento = LocalDate.parse(dataNascimento, formatter);  // Compara o padrão estabelecido com o padrão da String dataNascimento
            LocalDate hoje = LocalDate.now(); // Obtém a data atual
            int idade = hoje.getYear() - nascimento.getYear();  //Calcula a idade subtraindo o ano de nascimento do ano atual
            return (idade >= 18 && idade <= 100); // Verifica se o usuário tem mais que 18 anos e menos que 100 anos
        } catch (DateTimeParseException e) {
            // Se ocorrer um erro ao tentar fazer o parsing da data, retorna falso
            return false;
        }
    }
	
}
