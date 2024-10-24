/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao;

import br.com.ies.oficina.dominio.Cliente;
import br.com.ies.oficina.dominio.Veiculo;
import javax.swing.table.TableModel;

/**
 *
 * @author Weeaboo
 */
public interface ClienteDAO {
    public boolean cadastrarCliente(Cliente cliente, Veiculo veiculo);
    public boolean alterarCliente(Cliente cliente, Veiculo veiculo);
    public TableModel listarCliente();
    public TableModel listarClienteNome(String nome);
    public TableModel listarClienteAvancado(int cd_cliente);
    public int buscarCodigo(String cpf);
    public Cliente buscarClienteDadosPessoais(int cd_cliente);
    public Veiculo buscarClienteVeiculo(int cd_cliente);
    public boolean deletarCliente(int cd_cliente);
}
