/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dominio;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Weeaboo
 */
public class Conexao {
    private static Connection con;  
    private static String url = "jdbc:postgresql://localhost:5432/mecanica";
    private static String user = "galera";
    private static String password = "ies123";
     
    public static Connection conectar(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url ,user, password);
            return con;
        } catch (Exception e) {
            System.err.println("ERRO ao conectar com o banco de dados");
            return null;
        }
    }
}
