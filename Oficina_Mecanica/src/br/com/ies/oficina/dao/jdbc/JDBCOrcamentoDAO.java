/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao.jdbc;
import br.com.ies.oficina.dao.OrcamentoDAO;
import br.com.ies.oficina.dominio.Conexao;
import static br.com.ies.oficina.dominio.Conexao.conectar;
import br.com.ies.oficina.dominio.Orcamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.StringJoiner;
/**
 *
 * @author Weeaboo
 */
public class JDBCOrcamentoDAO implements OrcamentoDAO{

	Conexao dados_conexao = new Conexao();
	private Connection con;
	private Statement stm;
	
	@Override
	public void realizarOrcamento(Orcamento orcamento, String placa, String cpf_cliente, String cpf_funcionario, int cd_peca) {
		int cd_orcamento = 0;
        int cd_cliente = 0;
        int cd_veiculo = 0;
        int cd_inventario = 0;
        int cd_funcionario = 0;

        try {
            con = conectar();
            stm = con.createStatement();
            String sql = "insert into orcamento(comissao, descricao, valor_orcamento, data_orcamento) values(' "+orcamento.getComissao()+" ', ' "+orcamento.getDescricao()+" ', ' "+orcamento.getValor()+" ', ' "+orcamento.getDt_orcamento()+" ');";
            stm.executeUpdate(sql);

            sql = "select max(cd_orcamento) from orcamento"; // Procura e coloca o cd_orcamento 
            ResultSet rs_orcamento = stm.executeQuery(sql);
            while ( rs_orcamento.next() ) {
            	cd_orcamento = rs_orcamento.getInt(1);
            }

            sql = "select cd_pessoa from cliente where cpf = '"+cpf_cliente+"';"; // Procura e coloca o cd_pessoa do cliente
            ResultSet rs_cliente = stm.executeQuery(sql);
            while ( rs_cliente.next() ) {
            	cd_cliente = rs_cliente.getInt(1);
            }
            sql = "insert into orcamento_cliente(cd_orcamento, cd_pessoa) values ('"+cd_orcamento+"', '"+cd_cliente+"');";
            stm.executeUpdate(sql);

            sql = "select cd_veiculo from veiculo where placa = '"+placa+"';"; // Procura e coloca o cd_veiculo
            ResultSet rs_veiculo = stm.executeQuery(sql);
            while ( rs_veiculo.next() ) {
            	cd_veiculo = rs_veiculo.getInt(1);
            }
            sql = "insert into orcamento_veiculo(cd_orcamento, cd_veiculo) values ('"+cd_orcamento+"', '"+cd_veiculo+"');";
            stm.executeUpdate(sql);

            sql = "select cd_pessoa from funcionario where cpf = '"+cpf_funcionario+"';"; // Procura e coloca o cd_pessoa do funcionario
            ResultSet rs_funcionario = stm.executeQuery(sql);
            while ( rs_funcionario.next() ) {
            	cd_funcionario = rs_funcionario.getInt(1);
            }
            sql = "insert into orcamento_funcionario(cd_orcamento, cd_pessoa) values ('"+cd_orcamento+"', '"+cd_funcionario+"');";
            stm.executeUpdate(sql);

            sql = "select cd_inventario from inventario where cd_inventario = '"+cd_peca+"';"; // Procura e coloca o cd_orcamento 
            ResultSet rs_inventario = stm.executeQuery(sql);
            while ( rs_inventario.next() ) {
            	cd_inventario = rs_inventario.getInt(1);
            }
            sql = "insert into orcamento_inventario(cd_orcamento, cd_inventario) values ('"+cd_orcamento+"', '"+cd_inventario+"');";
            stm.executeUpdate(sql);


            rs_orcamento.close();
            rs_cliente.close();
            rs_veiculo.close();
            rs_funcionario.close();
            //rs_inventario.close();
            stm.close();
            con.close();
        	}catch (Exception e) {
                e.printStackTrace();
            }
		
	}

	@Override
	public void buscarOrcamento(String cpf) {
		
		try {
			int cd_cliente = 0;
			int cd_orcamento = 0;
			int cd_funcionario = 0;
			int cd_veiculo = 0;
			int cd_inventario = 0;
            con = conectar();
            stm = con.createStatement();
            
            String sql = "select cd_cliente from cliente where cpf = '"+cpf+"';";
            ResultSet rs = stm.executeQuery(sql);
            while ( rs.next() ) {
            	cd_cliente = rs.getInt(1);
            }
            
            sql = "select cd_orcamento from orcamento_cliente where cd_cliente = '"+cd_cliente+"';";
            rs = stm.executeQuery(sql);
            while ( rs.next() ) {
            	cd_orcamento = rs.getInt(1);
            }
            
            sql = "select cd_funcionario from orcamento_funcionario where cd_orcamento = '"+cd_orcamento+"';";
            rs = stm.executeQuery(sql);
            while ( rs.next() ) {
            	cd_funcionario = rs.getInt(1);
            }
            
            sql = "select cd_veiculo from orcamento_veiculo where cd_orcamento = '"+cd_orcamento+"';";
            rs = stm.executeQuery(sql);
            while ( rs.next() ) {
            	cd_veiculo = rs.getInt(1);
            }
            
            sql = "select cd_inventario from orcamento_inventario where cd_orcamento = '"+cd_orcamento+"';";
            rs = stm.executeQuery(sql);
            while ( rs.next() ) {
            	cd_inventario = rs.getInt(1);
            }
            
            System.out.println("\n=======================================================");
            System.out.println("-------------------------------------------------------");
            sql = "select * from orcamento where cd_orcamento = '"+cd_orcamento+"';";
            rs = stm.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int numeroDeColunas = metaData.getColumnCount();

            while (rs.next()) {
            	StringJoiner joiner = new StringJoiner("\n", "", "\n-------------------------------------------------------");
                for (int coluna = 1; coluna <= numeroDeColunas; coluna++) {
                    String nomeDaColuna = metaData.getColumnName(coluna);
                    joiner.add(nomeDaColuna + " = " + rs.getObject(coluna));
                }       
                System.out.println(joiner.toString());
                System.out.print("");
            }     
            
            sql = "select * from cliente where cd_cliente = '"+cd_cliente+"';";
            rs = stm.executeQuery(sql);
            metaData = rs.getMetaData();
            numeroDeColunas = metaData.getColumnCount();

            while (rs.next()) {
            	StringJoiner joiner = new StringJoiner("\n", "", "\n-------------------------------------------------------");
                for (int coluna = 1; coluna <= numeroDeColunas; coluna++) {
                    String nomeDaColuna = metaData.getColumnName(coluna);
                    joiner.add(nomeDaColuna + " = " + rs.getObject(coluna));
                }       
                System.out.println(joiner.toString());
                System.out.print("");
            }
            
            sql = "select * from funcionario where cd_funcionario = '"+cd_funcionario+"';";
            rs = stm.executeQuery(sql);
            metaData = rs.getMetaData();
            numeroDeColunas = metaData.getColumnCount();

            while (rs.next()) {
            	StringJoiner joiner = new StringJoiner("\n", "", "\n-------------------------------------------------------");
                for (int coluna = 1; coluna <= numeroDeColunas; coluna++) {
                    String nomeDaColuna = metaData.getColumnName(coluna);
                    joiner.add(nomeDaColuna + " = " + rs.getObject(coluna));
                }       
                System.out.println(joiner.toString());
                System.out.print("");
            }
            
            sql = "select * from veiculo where cd_veiculo = '"+cd_veiculo+"';";
            rs = stm.executeQuery(sql);
            metaData = rs.getMetaData();
            numeroDeColunas = metaData.getColumnCount();

            while (rs.next()) {
            	StringJoiner joiner = new StringJoiner("\n", "", "\n-------------------------------------------------------");
                for (int coluna = 1; coluna <= numeroDeColunas; coluna++) {
                    String nomeDaColuna = metaData.getColumnName(coluna);
                    joiner.add(nomeDaColuna + " = " + rs.getObject(coluna));
                }       
                System.out.println(joiner.toString());
                System.out.print("");
            }
            
            sql = "select * from inventario where cd_inventario = '"+cd_inventario+"';";
            rs = stm.executeQuery(sql);
            metaData = rs.getMetaData();
            numeroDeColunas = metaData.getColumnCount();

            while (rs.next()) {
            	StringJoiner joiner = new StringJoiner("\n", "", "\n-------------------------------------------------------");
                for (int coluna = 1; coluna <= numeroDeColunas; coluna++) {
                    String nomeDaColuna = metaData.getColumnName(coluna);
                    joiner.add(nomeDaColuna + " = " + rs.getObject(coluna));
                }       
                System.out.println(joiner.toString());
                System.out.print("");
            }
            System.out.println("=======================================================");
            rs.close();
            stm.close();
            con.close();

	 } catch (Exception e) {
		 	e.printStackTrace();
            }
		
	}
}
