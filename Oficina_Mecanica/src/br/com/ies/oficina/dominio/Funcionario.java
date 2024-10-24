/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dominio;

/**
 *
 * @author Weeaboo
 */

public class Funcionario extends Pessoa{
    private String cargo;
    private int cd_funcionario;
	public Funcionario(String nome, String cpf, String dt_nascimento, String email, String telefone, String cidade, String bairro, String rua, String numero, String cep, String cargo) {
		super(nome, cpf, dt_nascimento, email, telefone, cidade, bairro, rua, numero, cep);
		this.cargo = cargo;
	}

    public int getCd_funcionario() {
        return cd_funcionario;
    }

    public void setCd_funcionario(int cd_funcionario) {
        this.cd_funcionario = cd_funcionario;
    }

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
