/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.dominio;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
import java.math.BigDecimal;

/**
 *
 * @author Herik
 */
public class ManipuladorString {
    
    public static int contadorVazio(String string) {
        if(string == null || string.equals("")) {
            return 1;
        } else {           
            return 0;
        }
    }
    
    public static boolean verificarHora(String horario) {
        int hora = Integer.parseInt(horario.substring(0,2));
        int minuto = Integer.parseInt(horario.substring(3,5));
            if(hora <= 23 && hora >= 00 && minuto <= 59 && minuto >= 00) {
                return true;
            } else {
                return false;
            }
    }
    
    
    public static int contadorVazioData(String string) {
        if(string == null || string.equals("  /  /    ")) {
            return 1;
        } else {           
            return 0;
        }
    }
    
    public static int contadorVazioCelular(String string) {
        if(string == null || string.equals("(  )         ")) {
            return 1;
        } else {           
            return 0;
        }
    }
    
    public static int contadorVazioCpf(String string) {
        if(string == null || string.equals("   .   .   -  ")) {
            return 1;
        } else {           
            return 0;
        }
    }
        
    public static int contadorVazioCep(String string) {
        if(string == null || string.equals("  .   -   ")) {
            return 1;
        } else {           
            return 0;
        }
    }
    
    public static int contadorVazioPlaca(String string) {
        if(string == null || string.equals("   -    ")) {
            return 1;
        } else {           
            return 0;
        }
    }
    
    public static boolean verificarInt(String string){
        try{
            Integer.parseInt(string);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public static String firstToUpperCase(String string) {
        if(string == null || string.equals("")) {
            return null;
        } else {
            string = string.substring(0, 1).toUpperCase() + string.substring(1);
        return string;
        }
    }
    
    public static String placaToUpperCase(String string) {
        if(string == null || string.equals("")) {
            return null;
        } else {
            string = string.substring(0, 3).toUpperCase() + string.substring(3);
        return string;
        }
    }
    
    public static boolean verificarVazioCadastroCliente(Cliente cliente, Veiculo veiculo) {
        int contador = 0;
        contador = contador+contadorVazio(cliente.getNome());
        contador = contador+contadorVazioCpf(cliente.getCpf());
        contador = contador+contadorVazioData(cliente.getDt_nascimento());
        contador = contador+contadorVazio(cliente.getEmail());
        contador = contador+contadorVazioCelular(cliente.getTelefone());
        contador = contador+contadorVazio(cliente.getCidade());
        contador = contador+contadorVazio(cliente.getBairro());
        contador = contador+contadorVazio(cliente.getRua());
        contador = contador+contadorVazio(cliente.getNumero());
        contador = contador+contadorVazioCep(cliente.getCep());
        contador = contador+contadorVazio(veiculo.getMarca());
        contador = contador+contadorVazio(veiculo.getCor());
        contador = contador+contadorVazio(veiculo.getModelo());
        contador = contador+contadorVazioPlaca(veiculo.getPlaca());
        
        if(contador == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean verificarVazioCadastroFuncionario(Funcionario funcionario) {
        int contador = 0;
        contador = contador+contadorVazio(funcionario.getNome());
        contador = contador+contadorVazioCpf(funcionario.getCpf());
        contador = contador+contadorVazioData(funcionario.getDt_nascimento());
        contador = contador+contadorVazio(funcionario.getEmail());
        contador = contador+contadorVazioCelular(funcionario.getTelefone());
        contador = contador+contadorVazio(funcionario.getCidade());
        contador = contador+contadorVazio(funcionario.getBairro());
        contador = contador+contadorVazio(funcionario.getRua());
        contador = contador+contadorVazio(funcionario.getNumero());
        contador = contador+contadorVazioCep(funcionario.getCep());
        
        if(contador == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean verificarVazioPeca(Inventario inventario) {
        int contador = 0;
        
        contador = contador+contadorVazio(inventario.getNome());
        contador = contador+contadorVazio(inventario.getMarca());
        contador = contador+contadorVazio(inventario.getDescricao());
        
        if(contador == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean verificarVazioVeiculo(Veiculo veiculo){
        int contador = 0;
        
        if(contador == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean verificarData(String data){
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        if(dia > 00 && dia < 32 && mes > 00 && mes < 13) {
            return true;
        } else {
            return false;
        }
    }
    
    /*public static void converterMoedaToInt(String moeda) {
       
    }*/
}
