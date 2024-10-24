/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao.jdbc;

import br.com.ies.oficina.dao.CargoDAO;
import br.com.ies.oficina.dominio.Conexao;
import static br.com.ies.oficina.dominio.Conexao.conectar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;
import java.util.StringJoiner;
/**
 *
 * @author Weeaboo
 */
public class JDBCCargoDAO implements CargoDAO{

	private Connection con;
	private Statement stm;
	
	@Override
	public void criarCargo(String nome, int salario) {
		try {
			con = conectar();
			stm = con.createStatement();
			String sql = "insert into cargo(nome_cargo, salario) values ('"+nome+"', '"+salario+"');";
			stm.executeUpdate(sql);
			stm.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void alterarCargo(String cpf, int novoSalario) {
		int cd_pessoa = 0;
		try {
            con = conectar();
            stm = con.createStatement();
            
            String sql = "select (cd_funcionario) from pessoa where cpf = '"+cpf+"';";
            ResultSet rs = stm.executeQuery(sql);
            while ( rs.next() ) {
            	cd_pessoa = rs.getInt(1);
            }
            
            sql = "update funcionario set cd_cargo = '"+novoSalario+"' where cd_pessoa = '"+cd_pessoa+"';"; 
            stm.executeUpdate(sql);          
            sql = "update funcionario_cargo set cd_cargo = '"+novoSalario+"' where cd_funcionario = '"+cd_pessoa+"';"; 
            stm.executeUpdate(sql);
            
            rs.close();
            stm.close();
            con.close();

	 } catch (Exception e) {
		 	e.printStackTrace();
            }
		
	}

	@Override
	public void buscarCargo(String nome, int salario) {
		// TODO Auto-generated method stub
		//Nao existe
	}

	@Override
	public void deletarCargo(String nome, int salario) {
		// TODO Auto-generated method stub
		//Nao existe
	}

	@Override
	public void listarCargo() {
		Scanner scanner = new Scanner(System.in);
		try {
		con = conectar();
            stm = con.createStatement();
            
            String sql = "select * from cargo";
            ResultSet rs = stm.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int numeroDeColunas = metaData.getColumnCount();
            
            System.out.println("Lista de cargos :");
            System.out.println("\n=======================================================");
            System.out.println("-------------------------------------------------------");
            
            while (rs.next()) {
            	StringJoiner joiner = new StringJoiner("\n", "", "\n-------------------------------------------------------");
                for (int coluna = 1; coluna <= numeroDeColunas; coluna++) {
                    String nomeDaColuna = metaData.getColumnName(coluna);
                    joiner.add(nomeDaColuna + " = " + rs.getObject(coluna));
                }
                System.out.println(joiner.toString());
                System.out.print("");
            }	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
