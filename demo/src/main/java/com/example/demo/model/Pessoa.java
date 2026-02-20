package com.example.demo.model;

public class Pessoa {
	private static int counter = 1;

	private String nome;
	private String cpf;
	private String Email;

	public Pessoa(String nome, String cpf, String Email) {
		this.nome = nome;
		this.cpf = cpf;
		this.Email = Email;
		counter += 1;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		cpf = cpf;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	public String toString() {
		return "\nNome: " + this.getNome() +
				"\nCPF: " + this.getCPF() +
				"\nEmail: " + this.getEmail();
	}

}
