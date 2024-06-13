package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

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

	public static boolean validadorStatus(String status) {
		if (status.equals("Ativo")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validadorTelefone(String celular) {
		
	        // Expressão regular para validar o formato (##)#####-####
	        String regex = "\\(\\d{2}\\)\\d{5}-\\d{4}";

	        // Compilação do padrão de expressão regular  // Instância um padrão basado na expressão regex
	        Pattern pattern = Pattern.compile(regex);

	        // Compara o padrão criado com a String Obtida
	        Matcher matcher = pattern.matcher(celular);

	        // Retorna verdadeiro se a correspondência for encontrada, falso caso contrário
	        return matcher.matches();
	    }
	
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
	public static boolean isNumero(String str) {
	    try {
	    	System.out.println(str);
	        Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
}
