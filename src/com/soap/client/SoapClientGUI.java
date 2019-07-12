package com.soap.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.soap.server.Tasks;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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

        // Cria um serviço
        Service service = Service.create(url, qname);
        // Extrai o endpoint da interface, the service "port".

        // Extrai o estilo de vinculação RPC e retorna um proxy
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
        btnAddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTaskActionPerformed(evt);
            }
        });

        btnEditTask.setText("Editar");
        btnEditTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTaskActionPerformed(evt);
            }
        });

        btnDeleteTask.setText("Deletar");
        btnDeleteTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTaskActionPerformed(evt);
            }
        });

        btnApplyFilter.setText("Aplicar Filtro");
        btnApplyFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyFilterActionPerformed(evt);
            }
        });

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
                        .addComponent(btnApplyFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(btnDeleteTask))
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
        DefaultTableModel model = new DefaultTableModel(new String[]{"Prioridade", "Tarefa", "Detalhes", "Prazo", "Finalizado"}, 0);
        
        Object[] entries = eif.getAllEntries(this.userID);
        
        for (Object obj : entries) {
            model.addRow((Object[]) obj);
        }
  
        table.setModel(model);
    }//GEN-LAST:event_btnLoadDataActionPerformed

    private void btnAddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTaskActionPerformed
        AddTaskFrame addFrame = new AddTaskFrame(userID);
    }//GEN-LAST:event_btnAddTaskActionPerformed

    private void btnEditTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTaskActionPerformed
        List<String> numdata = new ArrayList<String>();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int row = table.getSelectedRow();

        for (int count = 0; count < model.getColumnCount(); count++) {
            numdata.add(model.getValueAt(row, count).toString());
        }

        EditTaskFrame editFrame = new EditTaskFrame(Integer.parseInt(numdata.get(0)), numdata.get(1), numdata.get(2), numdata.get(3), Integer.parseInt(numdata.get(4)), userID);
    }//GEN-LAST:event_btnEditTaskActionPerformed

    private void btnDeleteTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTaskActionPerformed
        List<String> numdata = new ArrayList<String>();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int row = table.getSelectedRow();

        for (int count = 0; count < model.getColumnCount(); count++) {
            numdata.add(model.getValueAt(row, count).toString());
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        
        try {
            date = df.parse(numdata.get(3));
        } catch (ParseException ex) {
            Logger.getLogger(SoapClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        eif.deleteTask(Integer.parseInt(numdata.get(4)), numdata.get(1), date, this.userID);
    }//GEN-LAST:event_btnDeleteTaskActionPerformed

    private void btnApplyFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyFilterActionPerformed
        String filter = textFieldFilter.getText();
        RowFilter<TableModel, Object> rf = null;
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.table.getModel());

        if (filter.length() != 0) {
            table.setRowSorter(sorter);

            try {
                rf = RowFilter.regexFilter(filter);
            } catch (java.util.regex.PatternSyntaxException e) {
                System.out.printf("PatternSyntaxException: %s\n", e);
                return;
            }

            sorter.setRowFilter(rf);
        } else {
            table.setRowSorter(null);
        }
    }//GEN-LAST:event_btnApplyFilterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTask;
    private javax.swing.JButton btnApplyFilter;
    private javax.swing.JButton btnDeleteTask;
    private javax.swing.JButton btnEditTask;
    private javax.swing.JButton btnLoadData;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JTextField textFieldFilter;
    // End of variables declaration//GEN-END:variables
}
