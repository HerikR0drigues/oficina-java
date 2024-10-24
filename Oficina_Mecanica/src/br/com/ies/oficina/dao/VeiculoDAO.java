/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao;
import br.com.ies.oficina.dominio.*;
import javax.swing.table.TableModel;
/**
 *
 * @author Weeaboo
 */
public interface VeiculoDAO {
    public boolean cadastrarVeiculo(Veiculo veiculo, String cpf);
    public boolean alterarVeiculo(Veiculo veiculo);
    public Veiculo buscarVeiculoDados(int cd_veiculo);
    public int buscarCodigo(String placa);
    public TableModel listarVeiculo();
    public TableModel listarVeiculoPlaca(String placa);
    public boolean deletarVeiculo(int cd_veiculo);
}
