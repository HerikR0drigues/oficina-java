/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao.jdbc;

import br.com.ies.oficina.dao.ClienteDAO;
import br.com.ies.oficina.dominio.Conexao;
import br.com.ies.oficina.dominio.*;
import static br.com.ies.oficina.dominio.Conexao.conectar;
import static br.com.ies.oficina.dominio.Pdf.BOLD;
import static br.com.ies.oficina.dominio.Pdf.BOLD_BIG;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class JDBCClienteDAO implements ClienteDAO{

	private Connection con;
	private Statement stm;
	private ResultSet rs;        
        
	@Override
	public boolean cadastrarCliente(Cliente cliente, Veiculo veiculo) {
		int cd_pessoa = 0;
                int cd_veiculo = 0;
		try {	
                    con = conectar();
                    stm = con.createStatement();
                    String sql = "insert into cliente(nome, cpf, dt_nascimento, email, cnpj) values ('"+cliente.getNome()+"', '"+cliente.getCpf()+"', '"+cliente.getDt_nascimento()+"', '"+ cliente.getEmail()+"', '"+cliente.getCnpj()+"');";
                    stm.executeUpdate(sql);							
                    sql = "select max(cd_cliente) from cliente";
	        
                    ResultSet rs = stm.executeQuery(sql);
                    while ( rs.next() ) {
                        cd_pessoa = rs.getInt(1);
                    }
	            
                    sql = "insert into telefone_cliente(cd_cliente, telefone_cliente) values ('"+cd_pessoa+"', '"+cliente.getTelefone()+"');";
                    stm.executeUpdate(sql);
				
                    sql = "insert into endereco_cliente(cd_cliente, cidade, bairro, rua, numero, cep) values ('"+cd_pessoa+"', '"+cliente.getCidade()+"', '"+cliente.getBairro()+"', '"+cliente.getRua()+"', '"+cliente.getNumero()+"', '"+cliente.getCep()+"');";
                    stm.executeUpdate(sql);										
			
                    sql = "insert into veiculo(modelo, marca, cor, placa) values ('"+veiculo.getModelo()+"', '"+veiculo.getMarca()+"', '"+veiculo.getCor()+"','"+veiculo.getPlaca()+"');";
                    stm.executeUpdate(sql);		
           
                    sql = "select max(cd_veiculo) from veiculo";
                    ResultSet rs1 = stm.executeQuery(sql);
                    while ( rs1.next() ) {
                        cd_veiculo = rs1.getInt(1);
                    }
            
                    sql = "insert into cliente_veiculo(cd_cliente, cd_veiculo) values ('"+cd_pessoa+"','"+cd_veiculo+"');";
                    stm.executeUpdate(sql);
			
                    rs1.close();
                    rs.close();
                    con.close();
                    stm.close();
                    return true;
                    
                    } catch (Exception e) {
			e.printStackTrace();
                        return false;
                    }
		
	}

	@Override
	public boolean alterarCliente(Cliente cliente, Veiculo veiculo) {
		int cd_cliente = 0;
                int cd_veiculo = 0;
		try {	
                    con = conectar();
                    stm = con.createStatement();
                                        
                    String sql = "update cliente set (nome, cpf, dt_nascimento, email, cnpj) = ('"+cliente.getNome()+"', '"+cliente.getCpf()+"', '"+cliente.getDt_nascimento()+"', '"+ cliente.getEmail()+"', '"+cliente.getCnpj()+"') where cd_cliente = "+cliente.getCd_cliente()+";";
                    stm.executeUpdate(sql);							
	            
                    sql = "update telefone_cliente set telefone_cliente = '"+cliente.getTelefone()+"' where cd_cliente = '"+cliente.getCd_cliente()+"';";
                    stm.executeUpdate(sql);
				
                    sql = "update endereco_cliente set (cidade, bairro, rua, numero, cep) = ('"+cliente.getCidade()+"', '"+cliente.getBairro()+"', '"+cliente.getRua()+"', '"+cliente.getNumero()+"', '"+cliente.getCep()+"') where cd_cliente = '"+cliente.getCd_cliente()+"';";
                    stm.executeUpdate(sql);										
			
                    sql = "select cd_veiculo from cliente_veiculo where cd_cliente = '"+cliente.getCd_cliente()+"';";
                    rs = stm.executeQuery(sql);
                    while ( rs.next() ) {
                        cd_veiculo = rs.getInt(1);
                    }
                    
                    sql = "update veiculo set (marca, cor, modelo, placa) = ('"+veiculo.getMarca()+"', '"+veiculo.getCor()+"', '"+veiculo.getModelo()+"','"+veiculo.getPlaca()+"') where cd_veiculo = '"+cd_veiculo+"';";
                    stm.executeUpdate(sql);		                              
			
                    rs.close();
                    con.close();
                    stm.close();
                    return true;
                    
                    } catch (Exception e) {
			e.printStackTrace();
                        return false;
                    }
	}
	
	@Override
	public TableModel listarCliente() {
		try {
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT c.cd_cliente as N°, c.nome as Nome, c.cpf as CPF, c.email as Email, t.telefone_cliente as telefone FROM cliente as c, telefone_cliente as t WHERE t.cd_telefone_cliente = c.cd_cliente;";
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
	public TableModel listarClienteAvancado(int cd_cliente) {
            try {
                con = conectar();
                stm = con.createStatement();
                String sql ="SELECT c.nome as Nome, c.cpf as CPF, c.email as Email, c.cnpj as CNPJ, e.cidade as Cidade, t.telefone_cliente as Telefone, CONCAT(v.marca,' ', v.modelo, ' ', v.placa) as Veiculo\n" +
                            "FROM veiculo as v, cliente as c, endereco_cliente as e, telefone_cliente as t\n" +
                            "WHERE v.cd_veiculo = c.cd_cliente\n" +
                            "AND t.cd_telefone_cliente = c.cd_cliente\n" +
                            "AND e.cd_endereco_cliente = c.cd_cliente\n" +
                            "AND c.cd_cliente = "+cd_cliente+";";
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
        public TableModel listarClienteNome(String nome) {
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "select * from cliente where nome like '"+nome+"%';";
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
        public int buscarCodigo(String cpf) {
            int codigo = 0;
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "select cd_cliente from cliente where cpf = '"+cpf+"';";
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    codigo = rs.getInt(1);                    
                }               
                return codigo;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        
        @Override
        public Cliente buscarClienteDadosPessoais(int cd_cliente) {
            int cd_veiculo = 0; 
            String nome = null;
            String cpf = null;
            String data = null;
            String email = null;
            String cnpj = null;
            String celular = null;
            String cidade = null;
            String bairro = null;
            String rua = null;
            String numero = null;
            String cep = null;
            String marca = null; 
            String cor = null;
            String modelo = null;
            String placa = null;
            
            try {                
                con = conectar();
                stm = con.createStatement();
                String sql = "select * from cliente where cd_cliente = "+cd_cliente+";"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){
                    cd_cliente = rs.getInt("cd_cliente");
                    nome = rs.getString("nome");
                    cpf = rs.getString("cpf");
                    email = rs.getString("email");
                    cnpj = rs.getString("cnpj");
                }
                
                sql = "select to_char(dt_nascimento, 'DD/MM/YYYY') as dt_nascimento from cliente where cd_cliente = "+cd_cliente+";"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                   
                    data = rs.getString("dt_nascimento");
                }
                
                sql = "select telefone_cliente from telefone_cliente where cd_cliente = '"+cd_cliente+"';"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    celular = rs.getString("telefone_cliente");
                }
                
                sql = "select * from endereco_cliente where cd_cliente = '"+cd_cliente+"';"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    cidade = rs.getString("cidade");
                    bairro = rs.getString("bairro");
                    rua = rs.getString("rua");
                    numero = rs.getString("numero");
                    cep = rs.getString("cep");
                }
                
                sql = "select cd_veiculo from cliente_veiculo where cd_cliente = '"+cd_cliente+"';"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    cd_veiculo = rs.getInt("cd_veiculo");                   
                }
                
                sql = "select * from veiculo where cd_veiculo = '"+cd_veiculo+"';"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    marca = rs.getString("marca"); 
                    cor = rs.getString("cor");
                    modelo = rs.getString("modelo");
                    placa = rs.getString("placa");
                }
                
                Cliente cliente = new Cliente(nome, cpf, data, email, celular, cidade, bairro, rua, numero, cep, cnpj);
                cliente.setCd_cliente(cd_cliente);
                return cliente;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
	
        @Override
        public Veiculo buscarClienteVeiculo(int cd_cliente) {
            int cd_veiculo = 0; 
            String marca = null; 
            String cor = null;
            String modelo = null;
            String placa = null;
            
            try {
                con = conectar();
                stm = con.createStatement();
                
                String sql = "select cd_veiculo from cliente_veiculo where cd_cliente = '"+cd_cliente+"';"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    cd_veiculo = rs.getInt("cd_veiculo");                   
                }
                
                sql = "select * from veiculo where cd_veiculo = '"+cd_veiculo+"';"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    marca = rs.getString("marca"); 
                    cor = rs.getString("cor");
                    modelo = rs.getString("modelo");
                    placa = rs.getString("placa");
                }
                Veiculo veiculo = new Veiculo(marca, cor, modelo, placa);
                return veiculo;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        
        @Override
	public boolean deletarCliente(int cd_cliente) {
            try {				   		
                con = conectar();
                stm = con.createStatement();

                String sql = "delete from cliente_veiculo where cd_cliente = '"+cd_cliente+"';";
                stm.executeUpdate(sql);
                sql = "delete from endereco_cliente where cd_cliente = '"+cd_cliente+"';";
                stm.executeUpdate(sql);
                sql = "delete from telefone_cliente where cd_cliente = '"+cd_cliente+"';";
                stm.executeUpdate(sql);
                sql = "delete from cliente where cd_cliente = '"+cd_cliente+"';";
                stm.executeUpdate(sql);       

                stm.close();
                con.close();
                return true;
                
            } catch (Exception e) {
		e.printStackTrace();
                return false;
            }
        }
        
        public void relatorioCliente(){
            String RESULT = "C:\\Users\\Weeaboo\\Desktop\\Relatorio_Cliente.pdf";
            Document document = new Document();
            
            try{
                //pesquisa do banco
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT c.nome , c.cpf , c.email , c.cnpj , e.cidade , t.telefone_cliente \n" +
                             "FROM cliente as c, endereco_cliente as e, telefone_cliente as t\n" +
                             "WHERE t.cd_telefone_cliente = c.cd_cliente\n" +
                             "AND e.cd_endereco_cliente = c.cd_cliente\n";
                rs = stm.executeQuery(sql);
                
                //gerar pdf
                PdfWriter.getInstance(document, new FileOutputStream(RESULT));
                document.open();

                Paragraph blankLine = new Paragraph();
                blankLine.add(Chunk.NEWLINE);

                Paragraph titulo = new Paragraph();
                titulo.add(new Phrase("Oficina Mecanica",BOLD_BIG));
                titulo.setAlignment(Element.ALIGN_CENTER);            

                Paragraph subTitulo = new Paragraph();
                subTitulo.add(new Phrase("Relatório Cliente",BOLD));
                subTitulo.setAlignment(Element.ALIGN_CENTER);
                subTitulo.add(Chunk.NEWLINE);
                subTitulo.add(Chunk.NEWLINE);
                subTitulo.add(Chunk.NEWLINE);
                
                PdfPTable tableCliente = new PdfPTable(5);
                tableCliente.addCell("Nome");
                tableCliente.addCell("CPF");
                tableCliente.addCell("Email");
                tableCliente.addCell("Cidade");
                tableCliente.addCell("Telefone");
                
                while(rs.next()){
                    tableCliente.addCell(rs.getString("nome"));
                    tableCliente.addCell(rs.getString("cpf"));
                    tableCliente.addCell(rs.getString("email"));
                    tableCliente.addCell(rs.getString("cidade"));
                    tableCliente.addCell(rs.getString("telefone_cliente"));
                }
                
                rs.close();
                stm.close();
                con.close();
                document.add(titulo);
                document.add(subTitulo);
                document.add(blankLine);
                document.add(tableCliente);
                document.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
}

