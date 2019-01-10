/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import graph.Graph;

/**
 *
 * @author geevb
 */
public class vertexMenuView extends javax.swing.JFrame {
    
    Graph graph;

    /**
     * Creates new form newVertexView
     */
    public vertexMenuView(Graph graph) {
        this.graph = graph;
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jTextFieldNewId = new javax.swing.JTextField();
        jLabelNewId = new javax.swing.JLabel();
        jLabelNewValue = new javax.swing.JLabel();
        jTextFieldNewValue = new javax.swing.JTextField();
        jButtonAddVertex = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldOldId = new javax.swing.JTextField();
        jButtonRemoveVertex = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelTitle.setFont(new java.awt.Font("Ubuntu", 1, 28)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Vértices");
        jLabelTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.gray));

        jLabelNewId.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabelNewId.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNewId.setText("ID Novo Vértice");

        jLabelNewValue.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabelNewValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNewValue.setText("Valor Novo Vértice");

        jButtonAddVertex.setText("Adicionar");
        jButtonAddVertex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddVertexActionPerformed(evt);
            }
        });

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID Vértice Antigo");

        jTextFieldOldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOldIdActionPerformed(evt);
            }
        });

        jButtonRemoveVertex.setText("Remover");
        jButtonRemoveVertex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveVertexActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Adicionar Vértice");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Remover Vértice");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNewValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNewValue, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldNewId, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNewId, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonAddVertex, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(57, 57, 57)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldOldId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButtonRemoveVertex, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNewId, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldNewId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelNewValue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldOldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRemoveVertex)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNewValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAddVertex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(jButtonSair)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        this.dispose();
        new mainMenuView(this.graph);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jTextFieldOldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOldIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOldIdActionPerformed

    private void jButtonAddVertexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddVertexActionPerformed
        String id = this.jTextFieldNewId.getText();
        if(id.equals("")) {
            JOptionPane.showMessageDialog(null, "ID não pode ser vazio!");
            return;
        }
        
        String value = this.jTextFieldNewValue.getText();
        int vertexValue;
        
        if(value.equals("")) {
            JOptionPane.showMessageDialog(null, "Valor zero será utilizado.");
            vertexValue = 0;
        } else {
           try {
                vertexValue = Integer.parseInt(this.jTextFieldNewValue.getText());
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "O valor do vértice precisa ser um int!");
                return;
            } 
        }
                
        try {
            this.graph.addVertex(id, vertexValue);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        } 
        
        JOptionPane.showMessageDialog(null, "Vértice adicionado com sucesso.");
        this.jTextFieldNewId.setText("");
        this.jTextFieldNewValue.setText("");
    }//GEN-LAST:event_jButtonAddVertexActionPerformed

    private void jButtonRemoveVertexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveVertexActionPerformed
        String id = this.jTextFieldOldId.getText();
        Boolean removed = this.graph.removeVertex(id);
        if(!removed) {
            JOptionPane.showMessageDialog(null, "O vértice de ID: " + id + " não existe!");
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Vértice removido com sucesso.");    
    }//GEN-LAST:event_jButtonRemoveVertexActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddVertex;
    private javax.swing.JButton jButtonRemoveVertex;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelNewId;
    private javax.swing.JLabel jLabelNewValue;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JTextField jTextFieldNewId;
    private javax.swing.JTextField jTextFieldNewValue;
    private javax.swing.JTextField jTextFieldOldId;
    // End of variables declaration//GEN-END:variables
}
