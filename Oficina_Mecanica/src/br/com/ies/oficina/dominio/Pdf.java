/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dominio;

import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
  
public class Pdf {
  
  //public static final String RESULT = "C:\\Users\\Weeaboo\\Desktop\\teste.pdf";        
  public static final Font BOLD = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD);
  public static final Font NORMAL = new Font(FontFamily.TIMES_ROMAN, 14);
  public static final Font BOLD_BIG = new Font(FontFamily.TIMES_ROMAN, 24, Font.BOLD );
  public static final Chunk NEWLINE = new Chunk("\n");
  public static final Chunk TABBING = new Chunk("\t");
  
    public static void gerarPdf(OrdemServico ordemServico){
        String RESULT = "C:\\Users\\Weeaboo\\Desktop\\Ordem_de_Serviço.pdf"; 
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(RESULT));
            document.open();
            
            Paragraph blankLine = new Paragraph();
            blankLine.add(Chunk.NEWLINE);
            
            Paragraph titulo = new Paragraph();
            titulo.add(new Phrase("Oficina Mecanica",BOLD_BIG));
            titulo.setAlignment(Element.ALIGN_CENTER);            
            
            Paragraph subTitulo = new Paragraph();
            subTitulo.add(new Phrase("Ordem de serviço",BOLD));
            subTitulo.setAlignment(Element.ALIGN_CENTER);
            subTitulo.add(Chunk.NEWLINE);
            subTitulo.add(Chunk.NEWLINE);
            subTitulo.add(Chunk.NEWLINE);
            
//==================================CLIENTE=====================================            
            /*Paragraph dadosCliente = new Paragraph();
            dadosCliente.add(new Phrase("Cliente",BOLD));
            dadosCliente.setAlignment(Element.ALIGN_CENTER);
            dadosCliente.add(Chunk.NEWLINE);
            dadosCliente.add(Chunk.NEWLINE);*/
            
            PdfPTable tableCliente = new PdfPTable(2);
            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Nome:"+ordemServico.getCliente().getNome()));
            cell.setColspan(1);
            tableCliente.addCell(cell);

            cell = new PdfPCell(new Phrase("Cidade:"+ordemServico.getCliente().getCidade()));
            cell.setColspan(1);
            tableCliente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Telefone:"+ordemServico.getCliente().getTelefone()));
            cell.setColspan(1);
            tableCliente.addCell(cell);

            cell = new PdfPCell(new Phrase("Bairro:"+ordemServico.getCliente().getBairro()));
            cell.setColspan(1);
            tableCliente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("CPF:"+ordemServico.getCliente().getCpf()));
            cell.setColspan(1);
            tableCliente.addCell(cell);
            tableCliente.addCell("");            
            
//==================================Veiculo=====================================            
            /*Paragraph dadosVeiculo = new Paragraph();
            dadosVeiculo.add(new Phrase("Veiculo",BOLD));
            dadosVeiculo.setAlignment(Element.ALIGN_CENTER);
            dadosVeiculo.add(Chunk.NEWLINE);
            dadosVeiculo.add(Chunk.NEWLINE);*/
            
            PdfPTable tableVeiculo = new PdfPTable(2);
            
            cell = new PdfPCell(new Phrase("Modelo:"+ordemServico.getVeiculo().getModelo()));
            cell.setColspan(1);
            tableVeiculo.addCell(cell);

            cell = new PdfPCell(new Phrase("Cor:"+ordemServico.getVeiculo().getCor()));
            cell.setColspan(1);
            tableVeiculo.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Marca:"+ordemServico.getVeiculo().getMarca()));
            cell.setColspan(1);
            tableVeiculo.addCell(cell);

            cell = new PdfPCell(new Phrase("Placa:"+ordemServico.getVeiculo().getPlaca()));
            cell.setColspan(1);
            tableVeiculo.addCell(cell);            

//====================================Peca======================================
            
            /*Paragraph dadosPeca = new Paragraph();
            dadosPeca.add(new Phrase("Peça",BOLD));
            dadosPeca.setAlignment(Element.ALIGN_CENTER);
            dadosPeca.add(Chunk.NEWLINE);
            dadosPeca.add(Chunk.NEWLINE);*/
            
            PdfPTable tablePeca = new PdfPTable(2);
            
            cell = new PdfPCell(new Phrase("Peça:"+ordemServico.getInventario().getNome()));
            cell.setColspan(1);
            tablePeca.addCell(cell);

            cell = new PdfPCell(new Phrase("Marca:"+ordemServico.getInventario().getMarca()));
            cell.setColspan(1);
            tablePeca.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Descricao:"+ordemServico.getInventario().getDescricao()));
            cell.setColspan(1);
            tablePeca.addCell(cell);

            cell = new PdfPCell(new Phrase("Quantidade:"+ordemServico.getInventario().getQuantidade()));
            cell.setColspan(1);
            tablePeca.addCell(cell);   
            
//=================================Descricao====================================            
            Paragraph descricao = new Paragraph();
            descricao.add(new Phrase("Descriçao do serviço",BOLD));
            descricao.setAlignment(Element.ALIGN_CENTER);
            descricao.add(Chunk.NEWLINE);
            descricao.add(Chunk.NEWLINE);
            
            PdfPTable tableDescricao = new PdfPTable(1);
            
            cell = new PdfPCell(new Phrase(ordemServico.getDescricao()));
            cell.setColspan(1);
            tableDescricao.addCell(cell);

//=================================Detalhes=====================================
 
            Paragraph detalhes = new Paragraph();
            
            detalhes.add(Chunk.NEWLINE);
            detalhes.add(Chunk.NEWLINE);
            
            detalhes.add(Chunk.TABBING);
            detalhes.add(new Phrase("    "));
            detalhes.add(new Phrase("Data: "+ordemServico.getData(),NORMAL));
            
            detalhes.add(Chunk.TABBING);
            detalhes.add(Chunk.TABBING);
            detalhes.add(Chunk.TABBING);
            detalhes.add(Chunk.TABBING);
             
            detalhes.add(new Phrase("Funcionario:"+ordemServico.getFuncionario().getNome(),NORMAL));
            
            detalhes.add(Chunk.NEWLINE);
            detalhes.add(Chunk.NEWLINE);
            
            detalhes.add(Chunk.TABBING);
            detalhes.add(new Phrase("    "));
            detalhes.add(new Phrase("Hora: "+ordemServico.getHora(),NORMAL));
            
            detalhes.add(Chunk.TABBING);
            detalhes.add(Chunk.TABBING);
            detalhes.add(Chunk.TABBING);
            detalhes.add(Chunk.TABBING);
            detalhes.add(Chunk.TABBING);
                        
            detalhes.add(new Phrase("Valor total: "+ordemServico.getValorTotal(),NORMAL));
            
//================================Assinatura====================================            
            
            Paragraph assinatura = new Paragraph();
            
            assinatura.add(Chunk.NEWLINE);
            assinatura.add(Chunk.NEWLINE);
            
            assinatura.add(Chunk.TABBING);
            assinatura.add(Chunk.TABBING);
            assinatura.add(new Phrase("______________",BOLD));
            
            assinatura.add(Chunk.TABBING);
            assinatura.add(Chunk.TABBING);
            assinatura.add(Chunk.TABBING);
            assinatura.add(new Phrase("______________",BOLD));
            

            
            
            document.add(titulo);
            document.add(subTitulo);
            //document.add(dadosCliente);
            document.add(tableCliente);
            document.add(blankLine);
            //document.add(dadosVeiculo);
            document.add(tableVeiculo);
            document.add(blankLine);
            //document.add(dadosPeca);
            document.add(tablePeca);
            document.add(blankLine);
            document.add(descricao);
            document.add(tableDescricao);
            document.add(blankLine);
            document.add(detalhes);
            document.add(blankLine);
            document.add(assinatura);            

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void relatorioFuncionario(Funcionario funcionario, Cargo cargo){
        String RESULT = "C:\\Users\\Weeaboo\\Desktop\\Relatorio_Funcionario.pdf";
        
        try{
            Document document = new Document();
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
            
//================================Lista de fincionarios==================================== 
            PdfPTable tableFuncionario = new PdfPTable(2);
            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Nome:"+funcionario.getNome()));
            cell.setColspan(1);
            tableFuncionario.addCell(cell);

            cell = new PdfPCell(new Phrase("CPF:"+funcionario.getCpf()));
            cell.setColspan(1);
            tableFuncionario.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Email:"+funcionario.getEmail()));
            cell.setColspan(1);
            tableFuncionario.addCell(cell);

            cell = new PdfPCell(new Phrase("Salario:"+cargo.getSalario()));
            cell.setColspan(1);
            tableFuncionario.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Cargo:"+funcionario.getCargo()));
            cell.setColspan(1);
            tableFuncionario.addCell(cell);
            tableFuncionario.addCell("");
            
            document.add(titulo);
            document.add(subTitulo);                 
            document.add(tableFuncionario);
            document.add(blankLine);
            
            document.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void relatorioCliente(Cliente cliente){
        
    }
    
    public static void relatorioInventario(Inventario inventario){
        
    }
    
    public static void relatorioVeiculo(Veiculo veiculo){
        
    }
}
        
  
