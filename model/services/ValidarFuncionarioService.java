package model.services;

import model.entities.Empresa;
import model.entities.Funcionario;

public class ValidarFuncionarioService {
	
	public static Funcionario validarFuncionario(Empresa empresa, String nome) {
		return empresa.getFuncionarios().stream().filter(x -> x.getNome().equals(nome)).findFirst().orElseThrow();
	}

}
