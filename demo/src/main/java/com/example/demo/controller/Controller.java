package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.BancoService;
import com.example.demo.model.ContaBancaria;

import java.util.List;

@RestController
@RequestMapping("/ContaBancaria")
@CrossOrigin
public class Controller {
	
@Autowired

private BancoService service;


@GetMapping("/status")
public String status() {
	return "Banco Online";
}


@PostMapping("/criar")
public ContaBancaria criarConta(@RequestParam String nome, @RequestParam String cpf, @RequestParam String email)
{
return  service.criarConta(nome, cpf, email);
}

@GetMapping ("/listar")
public List<ContaBancaria> listar()
{
return  service.listar();
}
	
@PostMapping ("/Depositar")
public String depositar(@RequestParam int numeroConta, @RequestParam Double valor) 
{
	
	
    if (service.depositar(numeroConta,  valor)) {
        return "Depósito feito!";
    
    }
    return ("Conta não encontrada");    

}


@PostMapping ("/Sacar")
public String sacar(
		@RequestParam int numeroConta,
		@RequestParam Double valor){
	
	if(service.sacar(numeroConta, valor)) {
		return "Saque realizado";
	}
	return "Conta não encontrada";

}
@PostMapping("/transferir")
public String  transferir(
        @RequestParam int origem,
        @RequestParam int destino,
        @RequestParam double valor) {

    if (service.transferir(origem, destino, valor)) {
        return "Transferência realizada com sucesso!";
    }

    return "Erro na transferência — contas inválidas ou saldo insuficiente";
}

}
