/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.thowo.jmjavaframework.table.JMRow;
import com.thowo.jmpcframework.component.JMPCFormModal;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author jimi
 */
public class FormDouble extends JMPCFormModal {
    private String xlsType;
    private DoubleFormCaller caller=null;
    private DefaultListModel m1=new DefaultListModel();
    private DefaultListModel m2=new DefaultListModel();
    private DefaultListModel m3=new DefaultListModel();
    
    class MyCellRenderer extends DefaultListCellRenderer {
        private int[] indices;
        public MyCellRenderer(String xlsType){
            if(xlsType.equals("tb_xls_inventaris")){
                this.indices=new int[]{17,4,9,15,14};
            }else if(xlsType.equals("tb_xls_a")){
                this.indices=new int[]{16,4,6,14,13};
            }else if(xlsType.equals("tb_xls_b")){
                this.indices=new int[]{18,4,8,16,15};
            }else if(xlsType.equals("tb_xls_c")){
                this.indices=new int[]{20,4,9,17,16};
            }else if(xlsType.equals("tb_xls_d")){
                this.indices=new int[]{20,4,9,17,15};
            }else if(xlsType.equals("tb_xls_e")){
                this.indices=new int[]{19,4,14,16,15};
            }else if(xlsType.equals("tb_xls_f")){
                this.indices=new int[]{20,4,9,17,16};
            }else{
                this.indices=new int[]{15,4,5,12,11};
            }
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
            JMRow[] rows=(JMRow[])value;
            
            String text="";
            if(list.getName().equals("xls")){
                JMRow row=rows[0];
                text="<html><div  style='width:100px;margin:5px;padding:5px;border: 2px solid;border-radius: 10px'>"
                        + "<div>"+row.getCells().get(this.indices[0]).getDBValue()+"</div>"
                        + "<div>"+row.getCells().get(this.indices[1]).getDBValue()+"</div>"
                        + "<div>"+row.getCells().get(this.indices[2]).getDBValue()+"</div>"
                        + "<div>"+row.getCells().get(this.indices[3]).getDBValue()+"</div>"
                        + "<div>"+row.getCells().get(this.indices[4]).getText()+"</div>"
                        + "</div></html>";
            }else if(list.getName().equals("inv")){
                JMRow row=rows[0];
                text="<html><div  style='width:100px;margin:5px;padding:5px;border: 2px solid;border-radius: 10px'>"
                        + "<div>"+row.getCells().get(17).getDBValue()+"</div>"
                        + "<div>"+row.getCells().get(12).getDBValue()+"</div>"
                        + "<div>"+row.getCells().get(13).getText()+"</div>"
                        + "</div></html>";
            }else{
                text="<html><div  style='width:100px;margin:5px;padding:5px;border: 2px solid;border-radius: 10px'>"
                        + "<div>"+rows[1].getCells().get(17).getDBValue()+"</div>"
                        + "<div>"+rows[1].getCells().get(12).getDBValue()+"</div>"
                        + "<div>"+rows[1].getCells().get(13).getText()+"</div>"
                        + "</div></html>";
            }
          return super.getListCellRendererComponent(list, text, index, isSelected,
              cellHasFocus);
        }

    }

    /**
     * Creates new form FormDouble
     */
    public FormDouble(java.awt.Frame parent, boolean modal, DoubleFormCaller caller, String xlsType) {
        super(parent, modal);
        this.caller=caller;
        this.xlsType=xlsType;
        initComponents();
        this.setInterface();
    }
    
    private void setInterface(){
        this.jList1.setModel(m1);
        this.jList2.setModel(m2);
        this.jList3.setModel(m3);
        MyCellRenderer cr=new MyCellRenderer(this.xlsType);
        this.jList1.setCellRenderer(cr);
        this.jList2.setCellRenderer(cr);
        this.jList3.setCellRenderer(cr);
        
        this.caller.getXlsTable().firstRow(false);
        do{
            JMRow[] rows={this.caller.getXlsTable().getCurrentRow(),null};
            m1.addElement(rows);
        }while(this.caller.getXlsTable().nextRow(false)!=null);
        
        this.caller.getInvTable().firstRow(false);
        do{
            JMRow[] rows={this.caller.getInvTable().getCurrentRow(),null};
            m2.addElement(rows);
        }while(this.caller.getInvTable().nextRow(false)!=null);
        
        this.jList1.setSelectedIndex(0);
        this.jList2.setSelectedIndex(0);
        
        this.setButtons();
    }
    
    private void setButtons(){
        this.jButton1.setEnabled(this.jList1.getSelectedIndex()!=-1 && this.jList2.getSelectedIndex()!=-1);
        this.jButton2.setEnabled(this.jList3.getSelectedIndex()!=-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setName("xls"); // NOI18N
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.setName("inv"); // NOI18N
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jButton3.setText("OK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jButton1.setText(">");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList3.setName("merge"); // NOI18N
        jList3.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList3ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList3);

        jButton2.setText("<");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JMRow[] r=(JMRow[])this.m3.getElementAt(this.jList3.getSelectedIndex());
        JMRow[] r1={r[0],null};
        JMRow[] r2={r[1],null};
        this.m1.addElement(r1);
        this.m2.addElement(r2);
        
        this.m3.removeElement(r);
        
        this.setButtons();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JMRow[] r1=(JMRow[])this.m1.getElementAt(this.jList1.getSelectedIndex());
        JMRow[] r2=(JMRow[])this.m2.getElementAt(this.jList2.getSelectedIndex());
        JMRow[] rows={r1[0],r2[0]};
        this.m3.addElement(rows);
        
        this.m1.removeElement(r1);
        this.m2.removeElement(r2);
        
        this.setButtons();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        this.setButtons();
    }//GEN-LAST:event_jList1ValueChanged

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        // TODO add your handling code here:
        this.setButtons();
    }//GEN-LAST:event_jList2ValueChanged

    private void jList3ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList3ValueChanged
        // TODO add your handling code here:
        this.setButtons();
    }//GEN-LAST:event_jList3ValueChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(this.m1.isEmpty() && this.m2.isEmpty()){
            if(!this.m3.isEmpty()){
                List<String> tbInvKeys=new ArrayList();
                List<String> tbXlsKeys=new ArrayList();
                for(Object obj:this.m3.toArray()){
                    JMRow[] rows=(JMRow[]) obj;
                    tbInvKeys.add(rows[1].getCells().get(0).getDBValue());
                    tbXlsKeys.add(rows[0].getCells().get(0).getDBValue());
                }
                this.caller.setResult(tbInvKeys, tbXlsKeys);
                this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                return;
            }
        }
        int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
           this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        } else {
           this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.close();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(FormDouble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDouble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDouble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDouble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormDouble dialog = new FormDouble(new javax.swing.JFrame(), true,null,"");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
