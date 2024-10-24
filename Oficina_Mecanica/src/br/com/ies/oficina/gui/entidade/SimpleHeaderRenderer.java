/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ies.oficina.gui.entidade;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Weeaboo
 */
public class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
 
    public SimpleHeaderRenderer() {
        setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
        setOpaque(true);
        setForeground(Color.WHITE);
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createEtchedBorder());
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
        // code to customize the GUI based on the parameters
 
        return this;
    }
}
