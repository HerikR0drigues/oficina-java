/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao;

import br.com.ies.oficina.dominio.Inventario;
import javax.swing.table.TableModel;

/**
 *
 * @author Herik
 */
public interface InventarioDAO {
    public boolean cadastrarInventario(Inventario inventario);
    public boolean alterarInventario(Inventario inventario);
    public Inventario buscarInventario(int cd_inventario);
    public TableModel listarInventario();
    public TableModel listarInventarioNome(String nome);
    public TableModel listarInventarioMarca(String marca);
    public boolean deletarInventario(int cd_cargo);
}
