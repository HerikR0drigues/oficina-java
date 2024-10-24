/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.criptografia;

import org.jasypt.util.text.StrongTextEncryptor;

/**
 *
 * @author Herik
 */
public class CriptografarSenha {
    String password= "ies123";
    StrongTextEncryptor  passwordEncryptor = new StrongTextEncryptor();
    
    public CriptografarSenha() {
        passwordEncryptor.setPassword(password);
    }
    
    public String encriptar(String senha){             
        String encrypted = passwordEncryptor.encrypt(senha);
        return encrypted;
    }
    
    public String desencriptar(String senha){
        String decrypted = passwordEncryptor.decrypt(senha);
        return decrypted;
    }
}
