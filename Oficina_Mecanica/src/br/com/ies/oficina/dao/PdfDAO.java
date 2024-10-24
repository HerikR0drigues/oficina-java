/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao;

import br.com.ies.oficina.dominio.OrdemServico;

/**
 *
 * @author Herik
 */
public interface PdfDAO {
    public void gerarPdfOS(OrdemServico ordemServico);
}
