/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dominio;

/**
 *
 * @author Herik
 */
public class OrdemServico {
    private Cliente cliente;
    private Veiculo veiculo;
    private Inventario inventario;
    private Funcionario funcionario;
    private String data;
    private String hora;
    private double valorTotal;
    private String descricao;
    
    public OrdemServico(Cliente cliente, Veiculo veiculo, Inventario inventario, Funcionario funcionario, String data, String hora, int valorTotal, String descricao) {
        this.cliente = cliente;
        this.data = data;
        this.descricao = descricao;
        this.funcionario = funcionario;
        this.hora = hora;
        this.inventario = inventario;
        this.valorTotal = valorTotal;
        this.veiculo = veiculo;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
