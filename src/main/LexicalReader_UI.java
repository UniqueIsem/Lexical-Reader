package main;

import components.ChooseFile;
import components.LineNumber;
import components.TextFileReader;
import java.awt.Cursor;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LexicalReader_UI extends javax.swing.JFrame {

    public LexicalReader_UI() {
        initComponents();
        this.setLocationRelativeTo(null);
        LineNumber tln = new LineNumber(txtPaneCode);
        scrollCode.setRowHeaderView(tln);
    }

    private void cleanFields() {
        txtPaneCode.setText("");
        txtPaneTerminal.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new javax.swing.JPanel();
        scrollCode = new javax.swing.JScrollPane();
        txtPaneCode = new javax.swing.JTextPane();
        scrollTerminal = new javax.swing.JScrollPane();
        txtPaneTerminal = new javax.swing.JTextPane();
        pnlBtnContainer = new javax.swing.JPanel();
        btnRunProject = new javax.swing.JLabel();
        btnClean = new javax.swing.JLabel();
        mnuBar = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuItemnewFile = new javax.swing.JMenuItem();
        fileSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuItemOpenFile = new javax.swing.JMenuItem();
        fileSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnuItemExit = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        mnuItemFontSize = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 800));

        pnlContainer.setBackground(new java.awt.Color(45, 45, 45));

        txtPaneCode.setBackground(new java.awt.Color(73, 73, 73));
        txtPaneCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPaneCode.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtPaneCode.setForeground(new java.awt.Color(204, 204, 204));
        scrollCode.setViewportView(txtPaneCode);

        txtPaneTerminal.setBackground(new java.awt.Color(73, 73, 73));
        txtPaneTerminal.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        txtPaneTerminal.setForeground(new java.awt.Color(204, 204, 204));
        scrollTerminal.setViewportView(txtPaneTerminal);

        pnlBtnContainer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlBtnContainerMouseEntered(evt);
            }
        });

        btnRunProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/run.png"))); // NOI18N
        btnRunProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRunProjectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRunProjectMouseEntered(evt);
            }
        });

        btnClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clean.png"))); // NOI18N
        btnClean.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCleanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCleanMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlBtnContainerLayout = new javax.swing.GroupLayout(pnlBtnContainer);
        pnlBtnContainer.setLayout(pnlBtnContainerLayout);
        pnlBtnContainerLayout.setHorizontalGroup(
            pnlBtnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRunProject)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClean)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        pnlBtnContainerLayout.setVerticalGroup(
            pnlBtnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBtnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnClean, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRunProject))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContainerLayout = new javax.swing.GroupLayout(pnlContainer);
        pnlContainer.setLayout(pnlContainerLayout);
        pnlContainerLayout.setHorizontalGroup(
            pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContainerLayout.createSequentialGroup()
                        .addComponent(scrollCode, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBtnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scrollTerminal))
                .addContainerGap())
        );
        pnlContainerLayout.setVerticalGroup(
            pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollCode, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBtnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTerminal, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnuBar.setBackground(new java.awt.Color(45, 45, 45));

        mnuFile.setText("File");

        mnuItemnewFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuItemnewFile.setText("New File");
        mnuItemnewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemnewFileActionPerformed(evt);
            }
        });
        mnuFile.add(mnuItemnewFile);
        mnuFile.add(fileSeparator1);

        mnuItemOpenFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuItemOpenFile.setText("Open File");
        mnuItemOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemOpenFileActionPerformed(evt);
            }
        });
        mnuFile.add(mnuItemOpenFile);
        mnuFile.add(fileSeparator2);

        mnuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuItemExit.setText("Exit IDE");
        mnuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuItemExit);

        mnuBar.add(mnuFile);

        mnuEdit.setText("Edit");

        mnuItemFontSize.setText("Font Size");
        mnuItemFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemFontSizeActionPerformed(evt);
            }
        });
        mnuEdit.add(mnuItemFontSize);

        mnuBar.add(mnuEdit);

        setJMenuBar(mnuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuItemnewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemnewFileActionPerformed
        cleanFields();
    }//GEN-LAST:event_mnuItemnewFileActionPerformed

    private void mnuItemOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemOpenFileActionPerformed
        // Crear un hilo secundario para esperar la seleccion del archivo
        Thread hiloFileChooser = new Thread(() -> {
            String archivoPath = new ChooseFile().abrirArchivo();
            if (archivoPath != null) {
                try {
                    txtPaneTerminal.setText("Selected file: " + archivoPath);
                    txtPaneCode.setText(TextFileReader.leerArchivo(archivoPath));
                } catch (IOException ex) {
                    Logger.getLogger(LexicalReader_UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        hiloFileChooser.start();
    }//GEN-LAST:event_mnuItemOpenFileActionPerformed

    private void mnuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemExitActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_mnuItemExitActionPerformed

    private void mnuItemFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemFontSizeActionPerformed
        boolean validInput = false;
        while (!validInput) {
            String input = JOptionPane.showInputDialog(null, "Font Size:", "Input data", JOptionPane.QUESTION_MESSAGE);
            if (input != null) {
                try {
                    int newFontSize = Integer.parseInt(input);
                    Font currentFont = txtPaneCode.getFont();
                    Font updatedFont = new Font(currentFont.getName(), Font.PLAIN, newFontSize);
                    txtPaneCode.setFont(updatedFont);
                    validInput = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid data. Please input an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Canceled operation.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_mnuItemFontSizeActionPerformed

    private void pnlBtnContainerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBtnContainerMouseEntered
        btnRunProject.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClean.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_pnlBtnContainerMouseEntered

    private void btnRunProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRunProjectMouseEntered
        btnRunProject.setToolTipText("Run Project");
    }//GEN-LAST:event_btnRunProjectMouseEntered

    private void btnRunProjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRunProjectMouseClicked
        String text = txtPaneCode.getText();
        LexicalReader reader = new LexicalReader();
        reader.analyze(text);
    }//GEN-LAST:event_btnRunProjectMouseClicked

    private void btnCleanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCleanMouseEntered
        btnClean.setToolTipText("Clear Terminal");
    }//GEN-LAST:event_btnCleanMouseEntered

    private void btnCleanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCleanMouseClicked
        txtPaneTerminal.setText("");
    }//GEN-LAST:event_btnCleanMouseClicked

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
            java.util.logging.Logger.getLogger(LexicalReader_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LexicalReader_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LexicalReader_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LexicalReader_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LexicalReader_UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClean;
    private javax.swing.JLabel btnRunProject;
    private javax.swing.JPopupMenu.Separator fileSeparator1;
    private javax.swing.JPopupMenu.Separator fileSeparator2;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuItemExit;
    private javax.swing.JMenuItem mnuItemFontSize;
    private javax.swing.JMenuItem mnuItemOpenFile;
    private javax.swing.JMenuItem mnuItemnewFile;
    private javax.swing.JPanel pnlBtnContainer;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JScrollPane scrollCode;
    private javax.swing.JScrollPane scrollTerminal;
    private javax.swing.JTextPane txtPaneCode;
    private javax.swing.JTextPane txtPaneTerminal;
    // End of variables declaration//GEN-END:variables
}
