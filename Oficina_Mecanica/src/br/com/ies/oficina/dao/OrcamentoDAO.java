/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao;

import br.com.ies.oficina.dominio.Orcamento;

/**
 *
 * @author Weeaboo
 */
public interface OrcamentoDAO {
    public void realizarOrcamento(Orcamento orcamento, String placa, String cpf_cliente, String cpf_funcionario, int cd_peca);
    public void buscarOrcamento(String cpf);
}
