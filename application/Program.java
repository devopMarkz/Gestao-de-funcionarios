package application;
	
import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Empresa;
import model.entities.Funcionario;
import model.entities.enums.Cargo;
import model.services.RegistrarLogService;
import model.utils.DTFormatter;
	
	public class Program {
	
		public static void main(String[] args) {
	
			Locale.setDefault(Locale.US);
			Scanner sc = new Scanner(System.in);
			
			Empresa empresa = new Empresa();
			empresa.atualizarFuncionarios();

			try {
				System.out.println("CADASTRE UM FUNCIONÁRIO: ");
				
				System.out.print("Nome: ");
				String nome = sc.nextLine();
				
				System.out.print("Cargo (JUNIOR/PLENO/SENIOR): ");
				String cargo = sc.next().toUpperCase();
				
				System.out.print("Salário base: ");
				Double salarioBase = sc.nextDouble();
				
				System.out.print("Anos de experiência: ");
				Integer anosDeExperiencia = sc.nextInt();
				
				Funcionario funcionario = new Funcionario(nome, Cargo.valueOf(cargo), salarioBase, anosDeExperiencia);
				
				empresa.adicionarFuncionario(funcionario);
				
				empresa.atualizarFuncionarios();
				
				RegistrarLogService.registraLog("O funcionario " + funcionario.getNome() + " foi registrado. Horário: " + DTFormatter.fmt.format(Instant.now().atZone(ZoneId.systemDefault())));
				System.out.println();
				System.out.println("--------------------- LISTA DE FUNCIONARIOS --------------------------------");
				
				for (Funcionario funcionarios : empresa.getFuncionarios()) {
					System.out.println(funcionarios);
				}
				
				System.out.println("--------------------- LISTA DE FUNCIONARIOS ---------------------");
				System.out.println();
				
				System.out.println("--------------------- FUNCIONÁRIO A PROMOVER ---------------------");
				sc.nextLine();
				System.out.print("Qual funcionário você deseja promover? ");
				nome = sc.nextLine();
				
				System.out.print("Para qual cargo? ");
				cargo = sc.next().toUpperCase();
				

				for (int i = 0; i < empresa.getFuncionarios().size(); i++) {
					if(empresa.getFuncionarios().get(i).getNome().equals(nome)) {
						String c2 = empresa.getFuncionarios().get(i).getCargo().toString();
						empresa.promoverFuncionario(empresa.getFuncionarios().get(i), Cargo.valueOf(cargo));
						RegistrarLogService.registraLog("O funcionário " + empresa.getFuncionarios().get(i).getNome() + " foi promovido de " + c2 + " para " + cargo.toString() + ". Horário: " + DTFormatter.fmt.format(Instant.now().atZone(ZoneId.systemDefault())));
						break;
					}
				}
				
				System.out.println("--------------------- FUNCIONÁRIO A PROMOVER ---------------------");
				System.out.println();
				
				System.out.println("--------------------- FUNCIONÁRIO A REMOVER ---------------------");
	
				System.out.print("Que funcionário deseja remover? ");
				sc.nextLine();
				nome = sc.nextLine();
				
				empresa.removerFuncionario(nome);
				System.out.println("--------------------- FUNCIONÁRIO A REMOVER ---------------------");
				
				System.out.println("--------------------- LISTA DE FUNCIONARIOS --------------------------------");
				
				for (Funcionario funcionarios : empresa.getFuncionarios()) {
					System.out.println(funcionarios);
				}
				
				System.out.println("--------------------- LISTA DE FUNCIONARIOS ---------------------");

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			
			
			sc.close();
	
		}
	
	}
