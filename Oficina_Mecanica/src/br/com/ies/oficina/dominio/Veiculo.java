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
public class Veiculo {
    private String marca;
	private String cor;
	private String modelo;
	private String placa;
	private int cd_veiculo;
        
	public Veiculo(String marca, String cor, String modelo, String placa) {
		super();
		this.marca = marca;
		this.cor = cor;
		this.modelo = modelo;
		this.placa = placa;
	}
        
        public int getCd_veiculo() {
        return cd_veiculo;
    }

    public void setCd_veiculo(int cd_veiculo) {
        this.cd_veiculo = cd_veiculo;
    }
        
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
        
        
}
