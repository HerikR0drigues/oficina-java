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
public class Orcamento {
    private int comissao;
	private String descricao;
	private int valor;
	private String dt_orcamento;
	
	public Orcamento(int comissao, String descricao, int valor, String dt_orcamento) {
		super();
		this.comissao = comissao;
		this.descricao = descricao;
		this.valor = valor;
		this.dt_orcamento = dt_orcamento;
	}

	public int getComissao() {
		return comissao;
	}

	public void setComissao(int comissao) {
		this.comissao = comissao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getDt_orcamento() {
		return dt_orcamento;
	}

	public void setDt_orcamento(String dt_orcamento) {
		this.dt_orcamento = dt_orcamento;
	}
	
	public void realizarOrcamento() {
		
	}
	
	public void buscarOrcamento() {
		
	}
}
