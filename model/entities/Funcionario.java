package model.entities;

import model.entities.enums.Cargo;

public class Funcionario {
	
	private String nome;
	private Cargo cargo;
	private Double salarioBase;
	private Integer anosDeExperiencia;
	
	// CONSTRUTORES
	
	public Funcionario() {
	}
	
	public Funcionario(String nome, Cargo cargo, Double salarioBase, Integer anosDeExperiencia) {
		this.nome = nome;
		this.cargo = cargo;
		this.salarioBase = salarioBase;
		this.anosDeExperiencia = anosDeExperiencia;
	}
	
	// GETTERS E SETTERS

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public Integer getAnosDeExperiencia() {
		return anosDeExperiencia;
	}

	public void setAnosDeExperiencia(Integer anosDeExperiencia) {
		this.anosDeExperiencia = anosDeExperiencia;
	}

	@Override
	public String toString() {
		return nome + "," 
				+ cargo + "," 
				+ salarioBase + "," 
				+ anosDeExperiencia;
	}
	
	
}
