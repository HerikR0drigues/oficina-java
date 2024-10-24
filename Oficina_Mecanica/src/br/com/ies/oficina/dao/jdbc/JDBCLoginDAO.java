/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dao.jdbc;

import br.com.ies.oficina.dao.LoginDAO;
import static br.com.ies.oficina.dominio.Conexao.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Herik
 */
public class JDBCLoginDAO implements LoginDAO {
    private ResultSet rs;
    private Connection con;
    private Statement stm;
    
    @Override
    public String buscarSenha(String user) {
        String pwd = null;
        try {
            con = conectar();
            stm = con.createStatement();
            
            String sql = "select senha from usuario_senha where usuario = '"+user+"';";
            rs = stm.executeQuery(sql);
            while(rs.next()){
                pwd = rs.getString("senha");                 
            }
            return pwd;
        } catch (Exception e) {
            return null;
        }
    }
    
}
