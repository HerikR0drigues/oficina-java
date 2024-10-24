/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao.jdbc;
import br.com.ies.oficina.dao.VeiculoDAO;
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
import java.sql.Statement;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Weeaboo
 */
public class JDBCVeiculoDAO implements VeiculoDAO{
        private ResultSet rs;
	private Connection con;
	private Statement stm;
	
	@Override
	public boolean cadastrarVeiculo(Veiculo veiculo, String cpf) {
		int cd_pessoa = 0;
                int cd_veiculo = 0;
                
		try {
                    con = conectar();
                    stm = con.createStatement();
                    String sql = "insert into veiculo(marca, cor, modelo, placa) values ('"+veiculo.getModelo()+"', '"+veiculo.getMarca()+"', '"+veiculo.getCor()+"','"+veiculo.getPlaca()+"');";
                    stm.executeUpdate(sql);
			
                    sql = "select cd_cliente from cliente where cpf = '"+cpf+"';";
                    ResultSet rs = stm.executeQuery(sql);
                    while ( rs.next() ) {
                        cd_pessoa = rs.getInt(1);
                    }
            
                    sql = "select max(cd_veiculo) from veiculo";
                    ResultSet rs1 = stm.executeQuery(sql);
                                while ( rs1.next() ) {
                        cd_veiculo = rs1.getInt(1);
                    }
            
                    sql = "insert into cliente_veiculo(cd_cliente, cd_veiculo) values ('"+cd_pessoa+"','"+cd_veiculo+"');";
                    stm.executeUpdate(sql);

                    rs1.close();
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
	public boolean alterarVeiculo(Veiculo veiculo) {

                try {
                    con = conectar();
                    stm = con.createStatement();

                    String sql = "update veiculo set (marca, cor, modelo, placa) = ('"+veiculo.getMarca()+"', '"+veiculo.getCor()+"', '"+veiculo.getModelo()+"', '"+veiculo.getPlaca()+"') where cd_veiculo = "+veiculo.getCd_veiculo()+";";                   
                    stm.executeUpdate(sql);
                    return true;
                }catch (Exception e) {
                        e.printStackTrace();
                        return false;
                   }
		
	}
        
        @Override
        public Veiculo buscarVeiculoDados(int cd_veiculo){
            String marca = null;
            String modelo = null;
            String cor = null;
            String placa = null;
            int cd_cliente = 0;
            
            try{
                con = conectar();
                stm = con.createStatement();
                
                String sql = "select * from veiculo where cd_veiculo = "+cd_veiculo+";"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){
                    cd_veiculo = rs.getInt("cd_veiculo");
                    marca = rs.getString("marca");
                    modelo = rs.getString("modelo");
                    cor = rs.getString("cor");
                    placa = rs.getString("placa");
                }
                
                Veiculo veiculo = new Veiculo(marca, cor, modelo, placa);
                veiculo.setCd_veiculo(cd_veiculo);
                return veiculo;
            }catch (Exception e) {
                
                return null;
            }    
        }
            
        @Override
        public TableModel listarVeiculo(){
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "select cd_veiculo as N°, marca as Marca, modelo as modelo, cor as Cor, placa as Placa from veiculo";
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
        public int buscarCodigo(String placa) {
            int codigo = 0;
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "select cd_veiculo from veiculo where placa = '"+placa+"';";
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
	public TableModel listarVeiculoPlaca(String placa) {
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "select * from veiculo where placa = '"+placa+"';";
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
	public boolean deletarVeiculo(int cd_veiculo) {
		try {
                    con = conectar();
                    stm = con.createStatement();
                    
                    String sql = "delete from cliente_veiculo where cd_veiculo = '"+cd_veiculo+"';";
                    stm.executeUpdate(sql);
                    sql = "delete from veiculo where cd_veiculo = '"+cd_veiculo+"';";
                    stm.executeUpdate(sql);
                    
                    stm.close();
                    con.close();
                    return true;
		} catch (Exception e) {
			e.printStackTrace();
                        return false;
		}	
	}
        
        public void relatorioVeic(){
            String RESULT = "C:\\Users\\Weeaboo\\Desktop\\Relatorio_Veiculo.pdf";
            Document document = new Document();
            
            try{
                //pesquisa do banco
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT marca , modelo , cor , placa\n" +
                             "FROM veiculo;";
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
                
                PdfPTable tableVeic = new PdfPTable(4);
                tableVeic.addCell("Marca");
                tableVeic.addCell("Modelo");
                tableVeic.addCell("Cor");
                tableVeic.addCell("Placa");
                
                
                while(rs.next()){
                    tableVeic.addCell(rs.getString("marca"));
                    tableVeic.addCell(rs.getString("modelo"));
                    tableVeic.addCell(rs.getString("cor"));
                    tableVeic.addCell(rs.getString("placa"));
                    
                }
                
                rs.close();
                stm.close();
                con.close();
                document.add(titulo);
                document.add(subTitulo);
                document.add(blankLine);
                document.add(tableVeic);
                document.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
}