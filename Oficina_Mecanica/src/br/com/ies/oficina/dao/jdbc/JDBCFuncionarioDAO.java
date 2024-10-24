/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao.jdbc;
import br.com.ies.oficina.dao.FuncionarioDAO;
import br.com.ies.oficina.dominio.Cargo;
import static br.com.ies.oficina.dominio.Conexao.conectar;
import br.com.ies.oficina.dominio.Funcionario;
import static br.com.ies.oficina.dominio.Pdf.BOLD;
import static br.com.ies.oficina.dominio.Pdf.BOLD_BIG;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Weeaboo
 */
public class JDBCFuncionarioDAO implements FuncionarioDAO{

	private Connection con;
	private Statement stm;
	private ResultSet rs;
        
	@Override
	public boolean cadastrarFuncionario(Funcionario funcionario){
		try {			
                    int cd_funcionario = 0;
                    int cd_cargo = 0;
                    con = conectar();
                    stm = con.createStatement();
                    
                    String sql = "select cd_cargo from cargo where nome_cargo = '"+funcionario.getCargo()+"';";
                    ResultSet rs = stm.executeQuery(sql);
                    while ( rs.next() ) {
                            cd_cargo = rs.getInt(1);
                    }
                    
                    sql = "insert into funcionario(nome, cpf, dt_nascimento, email, cd_cargo) values ('"+funcionario.getNome()+"', '"+funcionario.getCpf()+"', '"+funcionario.getDt_nascimento()+"', '"+funcionario.getEmail()+"', '"+cd_cargo+"');";
                    stm.executeUpdate(sql);

                    sql = "select max(cd_funcionario) from funcionario";
                    rs = stm.executeQuery(sql);
                    while ( rs.next() ) {
                            cd_funcionario = rs.getInt(1);
                    }

                    sql = "insert into telefone_funcionario(cd_funcionario, telefone_funcionario) values ('"+cd_funcionario+"', '"+funcionario.getTelefone()+"');";
                    stm.executeUpdate(sql);

                    sql = "insert into endereco_funcionario(cd_funcionario, cidade, bairro, rua, numero, cep) values ('"+cd_funcionario+"', '"+funcionario.getCidade()+"', '"+funcionario.getBairro()+"', '"+funcionario.getRua()+"', '"+funcionario.getNumero()+"', '"+funcionario.getCep()+"');";
                    stm.executeUpdate(sql);

                    sql = "insert into funcionario_cargo(cd_funcionario, cd_cargo) values ('"+cd_funcionario+"', '"+cd_cargo+"');";
                    stm.executeUpdate(sql);

                    rs.close();
                    stm.close();
                    con.close();
                    return true;
			
		} catch (Exception e) {
                    e.printStackTrace();
                    return false;
		}
	}

	@Override
        public boolean alterarFuncionario(Funcionario funcionario) {
            int cd_cargo = 0;
            try {
                    con = conectar();
                    stm = con.createStatement();
                    String sql = "select cd_cargo from cargo where nome_cargo = '"+funcionario.getCargo()+"';";
                    ResultSet rs = stm.executeQuery(sql);
                    while ( rs.next() ) {
                            cd_cargo = rs.getInt(1);
                    }
                    
                    sql = "update funcionario set (nome, cpf, dt_nascimento, email, cd_cargo) = ('"+funcionario.getNome()+"', '"+funcionario.getCpf()+"', '"+funcionario.getDt_nascimento()+"', '"+funcionario.getEmail()+"', '"+cd_cargo+"') where cd_funcionario = "+funcionario.getCd_funcionario()+";";
                    stm.executeUpdate(sql);							
	            
                    sql = "update telefone_funcionario set telefone_funcionario = '"+funcionario.getTelefone()+"' where cd_funcionario = '"+funcionario.getCd_funcionario()+"';";
                    stm.executeUpdate(sql);
				
                    sql = "update endereco_funcionario set (cidade, bairro, rua, numero, cep) = ('"+funcionario.getCidade()+"', '"+funcionario.getBairro()+"', '"+funcionario.getRua()+"', '"+funcionario.getNumero()+"', '"+funcionario.getCep()+"') where cd_funcionario = "+funcionario.getCd_funcionario()+";";
                    stm.executeUpdate(sql);	
                    
                    sql = "update funcionario_cargo set cd_cargo = "+cd_cargo+" where cd_funcionario = "+funcionario.getCd_funcionario()+";";
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
        public Funcionario buscarFuncionarioDadosPessoais(int cd_funcionario) {
            String nome = null;            
            String cpf = null;
            String data = null;
            String email = null;
            String celular = null;
            String cidade = null;
            String bairro = null;
            String rua = null;
            String numero = null;
            String cep = null;
            int cd_cargo = 0;
            String cargo = null;
            
            try {                
                con = conectar();
                stm = con.createStatement();
                
                String sql = "select * from funcionario where cd_funcionario = "+cd_funcionario+";"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){
                    cd_funcionario = rs.getInt("cd_funcionario");
                    nome = rs.getString("nome");
                    cpf = rs.getString("cpf");
                    email = rs.getString("email");
                    cd_cargo = rs.getInt("cd_cargo");
                }
                
                sql = "select to_char(dt_nascimento, 'DD/MM/YYYY') as dt_nascimento from funcionario where cd_funcionario = "+cd_funcionario+";"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                   
                    data = rs.getString("dt_nascimento");
                }
                                               
                sql = "select telefone_funcionario from telefone_funcionario where cd_funcionario = '"+cd_funcionario+"';"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    celular = rs.getString("telefone_funcionario");
                }
                
                sql = "select * from endereco_funcionario where cd_funcionario = '"+cd_funcionario+"';"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    cidade = rs.getString("cidade");
                    bairro = rs.getString("bairro");
                    rua = rs.getString("rua");
                    numero = rs.getString("numero");
                    cep = rs.getString("cep");
                }
                
                sql = "select nome_cargo from cargo where cd_cargo = '"+cd_cargo+"';"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    cargo = rs.getString("nome_cargo");
                }
                
                Funcionario funcionario = new Funcionario(nome, cpf, data, email, celular, cidade, bairro, rua, numero, cep, cargo);
                funcionario.setCd_funcionario(cd_funcionario);
                return funcionario;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
	   
        
        @Override
        public TableModel listarFuncionario(){
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT f.cd_funcionario as N°, f.nome as Nome, f.cpf as CPF, f.email as Email, t.telefone_funcionario as telefone FROM funcionario as f, telefone_funcionario as t WHERE t.cd_telefone_funcionario = f.cd_funcionario;";
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
        public TableModel listarFuncionarioNome(String nome) {
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "select * from funcionario where nome like '"+nome+"%';";
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
	public TableModel listarFuncionarioAvancado(int cd_funcionario){       
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT f.nome as Nome, f.cpf as CPF, f.dt_nascimento as Data_de_Nascimento, f.email as Email, e.cidade as Cidade, t.telefone_funcionario as Telefone, c.salario as Salario, c.nome_cargo as Cargo\n" +
                                "FROM cargo as c, funcionario as f, endereco_funcionario as e, telefone_funcionario as t\n" +
                                "WHERE c.cd_cargo = f.cd_cargo\n" +
                                "AND t.cd_telefone_funcionario = f.cd_cargo\n" +
                                "AND e.cd_endereco_funcionario = f.cd_cargo\n" +
                                "AND f.cd_funcionario = '"+cd_funcionario+"';";
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
	public boolean deletarFuncionario(int cd_funcionario) {
	    try {			
		con = conectar();
                stm = con.createStatement();
            
                String sql = "delete from endereco_funcionario where cd_funcionario = '"+cd_funcionario+"';";
                stm.executeUpdate(sql);
                sql = "delete from telefone_funcionario where cd_funcionario = '"+cd_funcionario+"';";
                stm.executeUpdate(sql);
                sql = "delete from funcionario_cargo where cd_funcionario = '"+cd_funcionario+"';";
                stm.executeUpdate(sql);
                sql = "delete from funcionario where cd_funcionario = '"+cd_funcionario+"';";
                stm.executeUpdate(sql);

                stm.close();
                con.close();
                return true;
            } catch (Exception e) {
		e.printStackTrace();
                return false;
            }
		
	}
        
        public void relatorioFunc(){
            String RESULT = "C:\\Users\\Weeaboo\\Desktop\\Relatorio_Funcionario.pdf";
            Document document = new Document();
            
            try{
                //pesquisa do banco
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT f.nome , f.cpf , f.email , c.salario as Salario, c.nome_cargo as Cargo\n" +
                             "FROM cargo as c, funcionario as f\n" +
                             "WHERE c.cd_cargo = f.cd_cargo\n";
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
                subTitulo.add(new Phrase("Relatório Funcionário",BOLD));
                subTitulo.setAlignment(Element.ALIGN_CENTER);
                subTitulo.add(Chunk.NEWLINE);
                subTitulo.add(Chunk.NEWLINE);
                subTitulo.add(Chunk.NEWLINE);
                
                PdfPTable tableFuncionario = new PdfPTable(5);
                tableFuncionario.addCell("Nome");
                tableFuncionario.addCell("CPF");
                tableFuncionario.addCell("Email");
                tableFuncionario.addCell("Salario");
                tableFuncionario.addCell("Cargo");
                
                while(rs.next()){
                    tableFuncionario.addCell(rs.getString("nome"));
                    tableFuncionario.addCell(rs.getString("cpf"));
                    tableFuncionario.addCell(rs.getString("email"));
                    tableFuncionario.addCell(rs.getString("salario"));
                    tableFuncionario.addCell(rs.getString("cargo"));
                }
                
                rs.close();
                stm.close();
                con.close();
                document.add(titulo);
                document.add(subTitulo);
                document.add(blankLine);
                document.add(tableFuncionario);
                document.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

}