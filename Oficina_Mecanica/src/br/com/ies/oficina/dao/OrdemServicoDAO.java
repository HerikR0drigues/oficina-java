/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao;

import br.com.ies.oficina.dominio.OrdemServico;
import javax.swing.table.TableModel;

/**
 *
 * @author Herik
 */
public interface OrdemServicoDAO {
    public boolean salvarOrdemServico(OrdemServico OrdemServico);
    public TableModel listarOrdemServico();
    public TableModel listarOSFinalizado();
    public TableModel listarOSCancelada();
    public void alterarSituacaoF(int cd_servico);
    public void alterarSituacaoC(int cd_servico);
}
