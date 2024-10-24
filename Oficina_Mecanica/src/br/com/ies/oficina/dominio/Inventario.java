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
public class Inventario {
    private String marca;
    private int quantidade;
    private String nome;
    private String descricao;
    private int cd_inventario;

    public int getCd_inventario() {
        return cd_inventario;
    }

    public void setCd_inventario(int cd_inventario) {
        this.cd_inventario = cd_inventario;
    }
    
	public Inventario(String marca, int quantidade, String nome, String descricao) {
		super();
		this.marca = marca;
		this.quantidade = quantidade;
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
