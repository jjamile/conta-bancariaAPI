package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ContaBancaria;
import com.example.demo.model.Pessoa;

@Service
public class BancoService {

    private List<ContaBancaria> contas = new ArrayList<>();

    public  ContaBancaria criarConta(String nome, String cpf, String email) {
        Pessoa pessoa = new Pessoa(nome, cpf, email);
        ContaBancaria conta = new ContaBancaria(pessoa);
        contas.add(conta);
        return conta;
    }

    public ContaBancaria encontrarConta(int numeroConta) {
        for (ContaBancaria c : contas) {
            if (c.getNumeroConta() == numeroConta) {
                return c;
            }
        }
        return null;
    }

    public boolean depositar(int numeroConta, double valor) {
        ContaBancaria c = encontrarConta(numeroConta);
        if (c != null) {
            c.depositar(valor);
            return true;
        }
        return false;
    }
    

    public boolean sacar(int numeroConta, double valor) {
        ContaBancaria c = encontrarConta(numeroConta);
        if (c != null) {
        	c.sacar(valor);
        	return true;
    }
        return false;
    }

    public boolean transferir(int origem, int destino, double valor) {
        ContaBancaria c1 = encontrarConta(origem);
        ContaBancaria c2 = encontrarConta(destino);
        if (c1 != null && c2 != null) {
            c1.transferir(c2, valor);
            return true;
        }
        return false;
    }

    public List<ContaBancaria> listar() {
        return contas;
    }
}
