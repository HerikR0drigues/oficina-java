/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao;

import br.com.ies.oficina.dominio.Funcionario;
import javax.swing.table.TableModel;

/**
 *
 * @author Weeaboo
 */
public interface FuncionarioDAO {
        public boolean cadastrarFuncionario(Funcionario funcionario);
	public boolean alterarFuncionario(Funcionario funcionario);
	public Funcionario buscarFuncionarioDadosPessoais(int cd_funcionario);
	public TableModel listarFuncionario();
        public TableModel listarFuncionarioNome(String nome);
        public TableModel listarFuncionarioAvancado(int cd_funcionario);
	public boolean deletarFuncionario(int cd_funcionario);    
}
