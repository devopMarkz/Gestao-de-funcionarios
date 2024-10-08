package model.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.entities.enums.Cargo;
import model.exceptions.CargoInexistenteException;
import model.exceptions.FuncionarioInexistenteException;
import model.exceptions.PromocaoNaoPermitidaException;
import model.services.RegistrarLogService;
import model.utils.DTFormatter;

public class Empresa {
	
	private static File registroDeFuncionarios = new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\atividade_GPT_heranca-polimorf.-excec.-enum\\registroDeFuncionarios.txt");
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	public Empresa() {
		try (Scanner sc = new Scanner(new BufferedReader(new FileReader(registroDeFuncionarios)))){
			
			while(sc.hasNextLine()) {
				String[] coluna = sc.nextLine().split(",");
				funcionarios.add(new Funcionario(coluna[0], Cargo.valueOf(coluna[1]), Double.parseDouble(coluna[2]), Integer.parseInt(coluna[3])));
			}
			
		} catch (IOException e) {
			System.out.println("A" + e.getMessage());
		}
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void adicionarFuncionario(Funcionario funcionario) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(registroDeFuncionarios, true))){
			bw.write(funcionario.toString());
			bw.newLine();
			funcionarios.add(funcionario);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	public void removerFuncionario(String nome) throws FuncionarioInexistenteException{
		for (Funcionario funcionario : funcionarios) {
			if(nome.equalsIgnoreCase(funcionario.getNome())) {
				RegistrarLogService.registraLog("O funcionário " + funcionario.getNome() + " foi removido do quadro de funcionários. Horário: " + DTFormatter.fmt.format(Instant.now().atZone(ZoneId.systemDefault())));
				getFuncionarios().remove(funcionario);
				break;
			} 
		}
		
		atualizarFuncionarios();
		
	}
	
	public Double calcularBonusSalarial(Funcionario funcionario) {
		double bonusSalarial = 0;

		for (Funcionario funcionarioDaLista : funcionarios) {
			if(funcionario.getNome().equals(funcionarioDaLista.getNome())) {
				bonusSalarial = funcionario.getSalarioBase() * (funcionario.getAnosDeExperiencia() * verificarBonusSalarial(funcionario) / 100);
				break;
			}
		}
		
		return bonusSalarial;
	}
	
	private Double verificarBonusSalarial(Funcionario funcionario) {
		if(funcionario.getCargo().name().equals(Cargo.JUNIOR.name())) return 5.0;
		else if(funcionario.getCargo().name().equals(Cargo.PLENO.name())) return 10.0;
		else return 15.0;
	}

	public void promoverFuncionario(Funcionario funcionario, Cargo cargo) throws CargoInexistenteException, PromocaoNaoPermitidaException{
		verificarPromocao(funcionario, cargo);
		funcionario.setCargo(cargo);
		atualizarFuncionarios();
	}
	
	private void verificarPromocao(Funcionario funcionario, Cargo cargo) throws CargoInexistenteException, PromocaoNaoPermitidaException{
		if(!(Arrays.asList(Cargo.values()).contains(cargo))) {
			throw new CargoInexistenteException("O cargo " + cargo.name() + " passado como parâmetro é inexistente.");
		}
		if(cargo.ordinal() <= funcionario.getCargo().ordinal()) {
			throw new PromocaoNaoPermitidaException("Não é possível rebaixar o cargo do funcionário " + funcionario.getNome() +" de " + funcionario.getCargo().name().toLowerCase() + " pra " + cargo.name().toLowerCase());
		}
	}
	
	private void atualizarFuncionarios() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(registroDeFuncionarios, false))){
			for (Funcionario funcionario : funcionarios) {
				bw.write(funcionario.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
