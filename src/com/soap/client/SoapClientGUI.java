package com.soap.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.soap.server.Tasks;

public class SoapClientGUI extends javax.swing.JFrame {

    private Tasks eif;
    private boolean isLoggedIn = false;
    private int userID;

    public SoapClientGUI(int userID) {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        this.userID = userID;
        
        URL url = null;
        try {
            url = new URL("http://localhost:9876/?wsdl");
        } catch (MalformedURLException ex) {
            Logger.getLogger(SoapClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        QName qname = new QName("http://server.soap.com/", "TasksImplService");

        // Create, in effect, a factory for the service.
        Service service = Service.create(url, qname);
        // Extract the endpoint interface, the service "port".

        eif = service.getPort(Tasks.class);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnLoadData = new javax.swing.JButton();
        btnAddTask = new javax.swing.JButton();
        btnEditTask = new javax.swing.JButton();
        btnDeleteTask = new javax.swing.JButton();
        btnApplyUpdates = new javax.swing.JButton();
        btnApplyFilter = new javax.swing.JButton();
        textFieldFilter = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        btnLoadData.setText("Atualizar");
        btnLoadData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadDataActionPerformed(evt);
            }
        });

        btnAddTask.setText("Adicionar");

        btnEditTask.setText("Editar");

        btnDeleteTask.setText("Deletar");

        btnApplyUpdates.setText("Apply Updates");

        btnApplyFilter.setText("Aplicar Filtro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textFieldFilter)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnLoadData, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddTask, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditTask, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteTask, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnApplyUpdates)
                            .addComponent(btnApplyFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoadData)
                    .addComponent(btnAddTask)
                    .addComponent(btnEditTask)
                    .addComponent(btnDeleteTask)
                    .addComponent(btnApplyUpdates))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnApplyFilter))
                .addGap(7, 7, 7)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadDataActionPerformed
/*
        if (!this.isLoggedIn) {
            LoginDialog loginDialog = new LoginDialog(new javax.swing.JFrame(), true);
            List<Object> credentials = loginDialog.showDialog();
            System.out.println(credentials.get(0));
            

            if (!loginDialog.getLoginCancelled()) {
                if (true) {
                    this.isLoggedIn = true;
                } else {
                    JOptionPane.showMessageDialog(this, "Error Logging in, please try again");
                    return;
                }
            } else {
                return;
            }
        }

    System.out.println (eif.getUserCount());
        */

        DefaultTableModel model = new DefaultTableModel(new String[]{"Prioridade", "Tarefa", "Detalhes", "Prazo", "Finalizado"}, 0);
        Object[] entries = eif.getAllEntries(this.userID);
        for(Object obj : entries){
            model.addRow((Object[]) obj);
        }
        table.setModel(model);

    }//GEN-LAST:event_btnLoadDataActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTask;
    private javax.swing.JButton btnApplyFilter;
    private javax.swing.JButton btnApplyUpdates;
    private javax.swing.JButton btnDeleteTask;
    private javax.swing.JButton btnEditTask;
    private javax.swing.JButton btnLoadData;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JTextField textFieldFilter;
    // End of variables declaration//GEN-END:variables
}
