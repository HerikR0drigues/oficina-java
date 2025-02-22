/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.gui;

import br.com.ies.oficina.criptografia.CriptografarSenha;
import br.com.ies.oficina.dao.jdbc.JDBCLoginDAO;
import br.com.ies.oficina.gui.realizar.RealizarOrdemServico;
import java.awt.Color;
import static javafx.scene.paint.Color.color;
import javax.swing.JOptionPane;

/**
 *
 * @author Weeaboo
 */
public class Login extends javax.swing.JFrame {
    
    /**
     * Creates new form main
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanelLogin = new javax.swing.JPanel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelSenha = new javax.swing.JLabel();
        jButtonEntrar = new javax.swing.JButton();
        jTextFieldUser = new javax.swing.JTextField();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanelBackground = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("Oficina Mecanica"), this, org.jdesktop.beansbinding.BeanProperty.create("title"));
        bindingGroup.addBinding(binding);

        jPanelLogin.setBackground(new java.awt.Color(32, 33, 35));
        jPanelLogin.setForeground(new java.awt.Color(87, 96, 111));

        jLabelUsuario.setBackground(new java.awt.Color(55, 66, 250));
        jLabelUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsuario.setText("Usuário:");

        jLabelSenha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSenha.setText("Senha:");

        jButtonEntrar.setBackground(new java.awt.Color(46, 204, 113));
        jButtonEntrar.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButtonEntrar.setForeground(new java.awt.Color(32, 33, 35));
        jButtonEntrar.setText("Entrar");
        jButtonEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonEntrar.setSelected(true);
        jButtonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEntrarActionPerformed(evt);
            }
        });
        jButtonEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonEntrarKeyPressed(evt);
            }
        });

        jTextFieldUser.setBackground(new java.awt.Color(32, 33, 35));
        jTextFieldUser.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldUser.setBorder(null);
        jTextFieldUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldUserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldUserFocusLost(evt);
            }
        });

        jPasswordFieldSenha.setBackground(new java.awt.Color(32, 33, 35));
        jPasswordFieldSenha.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordFieldSenha.setBorder(null);
        jPasswordFieldSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordFieldSenhaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordFieldSenhaFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelUsuario)
                            .addComponent(jLabelSenha)
                            .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jTextFieldUser)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator3)))
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jButtonEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabelUsuario)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabelSenha)
                .addGap(18, 18, 18)
                .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButtonEntrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBackground.setPreferredSize(new java.awt.Dimension(345, 320));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ies/oficina/pics/BackGroundLogin.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanelBackgroundLayout = new javax.swing.GroupLayout(jPanelBackground);
        jPanelBackground.setLayout(jPanelBackgroundLayout);
        jPanelBackgroundLayout.setHorizontalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, 0))
        );
        jPanelBackgroundLayout.setVerticalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
            .addComponent(jPanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void abrirMenu() {
        new Menu().setVisible(true);       
        this.dispose();
    }
        
    private void jButtonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntrarActionPerformed
        String usuarioDigitado = jTextFieldUser.getText();
        String senhaDigitada = new String(jPasswordFieldSenha.getPassword());
        new Menu().setVisible(true);
        /*try {          
            JDBCLoginDAO JDBCLogin = new JDBCLoginDAO();
            String senhaCriptografada = JDBCLogin.buscarSenha(usuarioDigitado);
            
            CriptografarSenha criptografar = new CriptografarSenha();
            String senhaDesincriptada = criptografar.desencriptar(senhaCriptografada);
            
            if(senhaDigitada.equals(senhaDesincriptada)) {
                abrirMenu();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Usuário ou senha incorretos!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro com o banco de dados!");
            e.printStackTrace();
        }*/
        this.dispose();
    }//GEN-LAST:event_jButtonEntrarActionPerformed

    private void jButtonEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonEntrarKeyPressed

    }//GEN-LAST:event_jButtonEntrarKeyPressed

    private void jTextFieldUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldUserFocusGained
       jLabelUsuario.setForeground(new Color(46,204,113));
       jTextFieldUser.setForeground(new Color(46,204,113));
    }//GEN-LAST:event_jTextFieldUserFocusGained

    private void jPasswordFieldSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFieldSenhaFocusGained
        jLabelSenha.setForeground(new Color(46,204,113));
        jPasswordFieldSenha.setForeground(new Color(46,204,113));
    }//GEN-LAST:event_jPasswordFieldSenhaFocusGained

    private void jPasswordFieldSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFieldSenhaFocusLost
        jLabelSenha.setForeground(new Color(255,255,255));
        jPasswordFieldSenha.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jPasswordFieldSenhaFocusLost

    private void jTextFieldUserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldUserFocusLost
        jLabelUsuario.setForeground(new Color(255,255,255));
        jTextFieldUser.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jTextFieldUserFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextFieldUser;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
