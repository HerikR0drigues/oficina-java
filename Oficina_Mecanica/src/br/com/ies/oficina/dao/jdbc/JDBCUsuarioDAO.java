/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao.jdbc;

import static br.com.ies.oficina.dominio.Conexao.conectar;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Weeaboo
 */
public class JDBCUsuarioDAO {
    Connection con;
    Statement stm;

    public boolean cadastrarUsuario(String user, String pwd) {
        try {
            con = conectar();
            stm = con.createStatement(); 

            String sql = "insert into usuario_senha (usuario, senha) values ('"+user+"', '"+pwd+"');";
            stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }   
}
