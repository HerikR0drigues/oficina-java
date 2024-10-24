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
public class Cliente extends Pessoa{
    private String cnpj;
    private int cd_cliente;
    
	public Cliente(String nome, String cpf, String dt_nascimento, String email, String telefone, String cidade, String bairro, String rua, String numero_cliente, String cep_cliente, String cnpj) {
		super(nome, cpf, dt_nascimento, email, telefone, cidade, bairro, rua, numero_cliente, cep_cliente);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
        
        public int getCd_cliente() {
            return cd_cliente;
        }

        public void setCd_cliente(int cd_cliente) {
            this.cd_cliente = cd_cliente;
        }
}
