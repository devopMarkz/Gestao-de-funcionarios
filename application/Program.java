	package application;
	
	import java.util.Locale;
import java.util.Scanner;

import model.entities.Empresa;
import model.entities.Funcionario;
import model.entities.enums.Cargo;
	
	public class Program {
	
		public static void main(String[] args) {
	
			Locale.setDefault(Locale.US);
			Scanner sc = new Scanner(System.in);
			
			Empresa empresa = new Empresa();

			try {
				System.out.println("CADASTRE UM FUNCIONÁRIO: ");
				
				System.out.print("Nome: ");
				String nome = sc.nextLine();
				
				System.out.print("Cargo (JUNIOR/PLENO/SENIOR): ");
				String cargo = sc.nextLine().toUpperCase();
				
				System.out.print("Salário base: ");
				Double salarioBase = sc.nextDouble();
				
				System.out.print("Anos de experiência: ");
				Integer anosDeExperiencia = sc.nextInt();
				
				empresa.adicionarFuncionario(new Funcionario(nome, Cargo.valueOf(cargo), salarioBase, anosDeExperiencia));
				
				System.out.println("--------------------- LISTA DE FUNCIONARIOS --------------------------------");
				for (Funcionario funcionario : empresa.getFuncionarios()) {
					System.out.println(funcionario);
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			
//			String nome = sc.nextLine();
//			
//			
//			try {
//				for (int i = 0; i < empresa.getFuncionarios().size(); i++) {
//					if(empresa.getFuncionarios().get(i).getNome().equals(nome)) {
//						System.out.println(empresa.calcularBonusSalarial(empresa.getFuncionarios().get(i)));
//						empresa.promoverFuncionario(empresa.getFuncionarios().get(i), Cargo.JUNIOR);
//						break;
//					}
//				}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
			
			sc.close();
	
		}
	
	}
