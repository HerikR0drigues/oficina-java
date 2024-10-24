/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao.jdbc;

import br.com.ies.oficina.dao.OrdemServicoDAO;
import static br.com.ies.oficina.dominio.Conexao.conectar;
import br.com.ies.oficina.dominio.OrdemServico;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Herik
 */
public class JDBCOrdemServicoDAO implements OrdemServicoDAO{
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    
    @Override
    public boolean salvarOrdemServico(OrdemServico ordemServico) {
        int cd_ordem_servico = 0;
        int cd_cliente = 0;
        int cd_veiculo = 0;
        
        try {
                    con = conectar();
                    stm = con.createStatement();
                    
                    String sql = "insert into ordem_servico(data, hora, valor_total, descricao, status) values ('"+ordemServico.getData()+"', '"+ordemServico.getHora()+"', "+ordemServico.getValorTotal()+",'"+ordemServico.getDescricao()+"', 'Em progresso');";
                    stm.executeUpdate(sql);
                    
                    sql = "select max(cd_ordem_servico) from ordem_servico;";
                    ResultSet rs = stm.executeQuery(sql);
                    while ( rs.next() ) {
                        cd_ordem_servico = rs.getInt(1);
                    }
                    
                    sql = "select cd_cliente from cliente where cpf = '"+ordemServico.getCliente().getCpf()+"';";
                    rs = stm.executeQuery(sql);
                    while ( rs.next() ) {
                        cd_cliente = rs.getInt(1);
                    }
                    
                    sql = "select cd_veiculo from veiculo where placa = '"+ordemServico.getVeiculo().getPlaca()+"';";
                    rs = stm.executeQuery(sql);
                    while ( rs.next() ) {
                        cd_veiculo = rs.getInt(1);
                    }
                    
                    sql = "insert into ordem_servico_inventario(cd_ordem_servico, cd_inventario) values ('"+cd_ordem_servico+"','"+ordemServico.getInventario().getCd_inventario()+"');";
                    stm.executeUpdate(sql);
                    
                    sql = "insert into ordem_servico_cliente(cd_ordem_servico, cd_cliente) values ('"+cd_ordem_servico+"','"+cd_cliente+"');";
                    stm.executeUpdate(sql);
                    
                    sql = "insert into ordem_servico_funcionario(cd_ordem_servico, cd_funcionario) values ('"+cd_ordem_servico+"','"+ordemServico.getFuncionario().getCd_funcionario()+"');";
                    stm.executeUpdate(sql);
                    
                    sql = "insert into ordem_servico_veiculo(cd_ordem_servico, cd_veiculo) values ('"+cd_ordem_servico+"','"+cd_veiculo+"');";
                    stm.executeUpdate(sql);
                    
                    stm.close();
                    con.close();
                    return true;
		} catch (Exception e) {
			e.printStackTrace();
                        return false;
		}
    }

    @Override
    public TableModel listarOrdemServico() {
        try {
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT cd_ordem_servico as Código, data as Data, hora as Hora, valor_total as Valor, descricao as Descrição, status as Situação \n" +
                             "FROM ordem_servico\n" +
                             "WHERE status = 'Em progresso';";
                rs = stm.executeQuery(sql);                   
                TableModel tableModel = DbUtils.resultSetToTableModel(rs);
                rs.close();
                stm.close();
                con.close();
                return tableModel;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public TableModel listarOSFinalizado() {
        try {
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT cd_ordem_servico as Código, data as Data, hora as Hora, valor_total as Valor, descricao as Descrição, status as Situação \n" +
                             "FROM ordem_servico\n" +
                             "WHERE status = 'Finalizado';";
                rs = stm.executeQuery(sql);                   
                TableModel tableModel = DbUtils.resultSetToTableModel(rs);
                rs.close();
                stm.close();
                con.close();
                return tableModel;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public TableModel listarOSCancelada() {
        try {
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT cd_ordem_servico as Código, data as Data, hora as Hora, valor_total as Valor, descricao as Descrição, status as Situação \n" +
                             "FROM ordem_servico\n" +
                             "WHERE status = 'Cancelado';";
                rs = stm.executeQuery(sql);                   
                TableModel tableModel = DbUtils.resultSetToTableModel(rs);
                rs.close();
                stm.close();
                con.close();
                return tableModel;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public void alterarSituacaoF(int cd_servico) {
        try {
                con = conectar();
                stm = con.createStatement();
                String sql = "UPDATE ordem_servico\n" +
                             "SET status = 'Finalizado'\n" +
                             "WHERE cd_ordem_servico = "+ cd_servico +";";
                stm.executeUpdate(sql);                   
                rs.close();
                stm.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void alterarSituacaoC(int cd_servico) {
        try {
                con = conectar();
                stm = con.createStatement();
                String sql = "UPDATE ordem_servico\n" +
                             "SET status = 'Cancelado'\n" +
                             "WHERE cd_ordem_servico = "+ cd_servico +";";
                stm.executeUpdate(sql);                   
                rs.close();
                stm.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    
}
