package com.example.demo.model;


import com.example.demo.utilitarios.*;

public class ContaBancaria {
	
	private static int contadorContas = 1;
	
	private int numeroConta;
	private Pessoa pessoa;
	private Double saldo = 0.0;
	
	public ContaBancaria(Pessoa pessoa) {
		this.numeroConta = contadorContas;
		this.pessoa = pessoa;
		contadorContas += 1;
		
		
		
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public String toString() {
		return "\n Nome: " +this.pessoa.getNome() +
		"\n Numero de Conta: " + this.getNumeroConta() +
		"\n Email: " + this.pessoa.getEmail() +
		"\n CPF: " + this.pessoa.getCPF() +
		"\n Saldo" + Utils.doubleToString(this.getSaldo()) +
		"\n";
			
	}
	
	public void depositar(Double valor) {
		if (valor >0) {
			setSaldo(getSaldo() + valor);
			System.out.println("Seu depósito foi realizado com sucesso!");
		}else {
			System.out.println("Não foi possivel realizar depósito. (Valor menor que 0.)");
		}
	}
	
	public void sacar(Double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			System.out.println("Saque realizado com sucesso");
		}else {
			System.out.println("Não foi possivel realizar o saque");
		}
	}
	
	public void transferir(ContaBancaria ContaDeposito, Double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
		setSaldo(getSaldo() - valor);
		
		ContaDeposito.saldo = ContaDeposito.getSaldo() + valor;
		System.out.println("Trasnferencia realizada com sucesso!");
	}else {
		System.out.println("Não foi possivel realizar a trasnferencia.(Saldo insuficiente)");
	}
}
}

