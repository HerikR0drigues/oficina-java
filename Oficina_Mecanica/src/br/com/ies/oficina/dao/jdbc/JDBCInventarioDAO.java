/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao.jdbc;
import br.com.ies.oficina.dao.InventarioDAO;
import static br.com.ies.oficina.dominio.Conexao.conectar;
import br.com.ies.oficina.dominio.Inventario;
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
public class JDBCInventarioDAO implements InventarioDAO{
	private Connection con;
	private Statement stm;
        private ResultSet rs;
	
	@Override
	public boolean cadastrarInventario(Inventario inventario) {
		try {
                    con = conectar();
                    stm = con.createStatement();
                    String sql = "insert into inventario(nome_item, marca_item, quantidade_item, descricao) values ('"+inventario.getNome()+"', '"+inventario.getMarca()+"', '"+inventario.getQuantidade()+"','"+inventario.getDescricao()+"');";
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
        public Inventario buscarInventario(int cd_inventario) {
            String marca = null;            
            int quantidade = 0;
            String descricao = null;
            String nome_item = null;
            
            try {
                con = conectar();
                stm = con.createStatement();

                String sql = "select * from inventario where cd_inventario = "+cd_inventario+";"; 
                rs = stm.executeQuery(sql);
                
                while(rs.next()){                    
                    cd_inventario = rs.getInt("cd_inventario");
                    marca = rs.getString("marca_item");
                    quantidade = rs.getInt("quantidade_item");
                    descricao = rs.getString("descricao");
                    nome_item = rs.getString("nome_item");
                }
                
                Inventario inventario = new Inventario(marca, quantidade, nome_item, descricao);
                inventario.setCd_inventario(cd_inventario);
                return inventario;
            } catch (Exception e) {
                return null;
            }                                                                        
        }
        
	@Override
	public boolean alterarInventario(Inventario inventario) {
		try {
                    con = conectar();
                    stm = con.createStatement();

                    String sql = "update inventario set (marca_item, quantidade_item, descricao, nome_item) = ('"+inventario.getMarca()+"', '"+inventario.getQuantidade()+"', '"+inventario.getDescricao()+"', '"+inventario.getNome()+"') where cd_inventario = "+inventario.getCd_inventario()+";";
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
        public TableModel listarInventario(){
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT cd_inventario as Codigo, marca_item as Marca, nome_item as Nome, quantidade_item as Quantidade, descricao as Descrição FROM inventario;";
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
	public TableModel listarInventarioNome(String nome) {
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "select * from inventario where nome_item like '"+nome+"%' order by cd_inventario;";
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
	public TableModel listarInventarioMarca(String marca) {
            try {
                con = conectar();
                stm = con.createStatement();
                String sql = "select * from inventario where marca_item like '"+marca+"%' order by cd_inventario;";
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
	public boolean deletarInventario(int cd_inventario) {
		try {
                    con = conectar();
                    stm = con.createStatement();
			
                    String sql = "delete from inventario where cd_inventario = "+cd_inventario+";";
                    stm.executeUpdate(sql);
                    return true;
		} catch (Exception e) {
                    e.printStackTrace();
                    return false;
		}
		
	}
        
        public void relatorioPeca(){
            String RESULT = "C:\\Users\\Weeaboo\\Desktop\\Relatorio_Inventario.pdf";
            Document document = new Document();
            
            try{
                //pesquisa do banco
                con = conectar();
                stm = con.createStatement();
                String sql = "SELECT marca_item , nome_item , quantidade_item , descricao \n" +
                             "FROM inventario;";
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
                
                PdfPTable tablePeca = new PdfPTable(4);
                tablePeca.addCell("Marca");
                tablePeca.addCell("Nome");
                tablePeca.addCell("Quantidade");
                tablePeca.addCell("Descrição");
                
                
                while(rs.next()){
                    tablePeca.addCell(rs.getString("marca_item"));
                    tablePeca.addCell(rs.getString("nome_item"));
                    tablePeca.addCell(rs.getString("quantidade_item"));
                    tablePeca.addCell(rs.getString("descricao"));
                    
                }
                
                rs.close();
                stm.close();
                con.close();
                document.add(titulo);
                document.add(subTitulo);
                document.add(blankLine);
                document.add(tablePeca);
                document.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
}
