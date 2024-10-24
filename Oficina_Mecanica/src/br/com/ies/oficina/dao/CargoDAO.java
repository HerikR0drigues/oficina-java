/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao;

/**
 *
 * @author Weeaboo
 */
public interface CargoDAO {
    	public void criarCargo(String nome, int salario);
	public void alterarCargo(String cpf, int novoSalario);
	public void buscarCargo(String nome, int salario);
	public void deletarCargo(String nome, int salario);
	public void listarCargo();
}
