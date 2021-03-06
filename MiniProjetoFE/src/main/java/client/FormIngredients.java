package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static javax.swing.JOptionPane.showMessageDialog;

public class FormIngredients extends javax.swing.JFrame {

    Testar t = new Testar();
    private DefaultListModel lista = new DefaultListModel();
    ArrayList<String> ingredientes = t.retornaIngrediente();

    private FormMain fm;

    public FormIngredients(FormMain fm) {
        setTitle("Ingredientes");
        initComponents();
        this.fm = fm;

        Dimension tamanho = new Dimension(420, 240);
        setSize(tamanho);
        setLayout(new BorderLayout());
        setPreferredSize(tamanho);
        setLocationRelativeTo(null);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fm.refreshIngredientes();
                fm.setVisible(true);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        refreshIngredientes();
        /*jl_listaIngredientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(e);
                if (jl_listaIngredientes.getSelectedValue() != null) {
                    jtf_nomeIngrediente.setText(jl_listaIngredientes.getSelectedValue().toString());
                }
            }

        });*/
    }

    private void refreshIngredientes() {
        lista.clear();
        ingredientes = t.retornaIngrediente();
        for (int i = 0; i < ingredientes.size(); i++) {
            lista.addElement(ingredientes.get(i));
            jl_listaIngredientes.setModel(lista);
        }
    }

    private void limparAviso() {
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jl_aviso.setText("");
            }
        });
        t.setRepeats(false);
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtf_nomeIngrediente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jl_listaIngredientes = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jb_adicionar = new javax.swing.JButton();
        jb_remover = new javax.swing.JButton();
        jl_aviso = new javax.swing.JLabel();
        jb_editar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setViewportView(jl_listaIngredientes);

        jLabel3.setText("Ingredientes:");

        jLabel2.setText("Nome do ingrediente:");

        jb_adicionar.setText("Adicionar");
        jb_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_adicionarActionPerformed(evt);
            }
        });

        jb_remover.setText("Eliminar");
        jb_remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_removerActionPerformed(evt);
            }
        });

        jl_aviso.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N

        jb_editar.setText("Editar");
        jb_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_editarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtf_nomeIngrediente, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jb_adicionar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jb_editar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jb_remover)))
                                .addGap(0, 15, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jl_aviso, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_nomeIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_adicionar)
                            .addComponent(jb_remover)
                            .addComponent(jb_editar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jl_aviso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_adicionarActionPerformed
        if (this.jtf_nomeIngrediente.getText().length() != 0) {
            System.out.println(this.jtf_nomeIngrediente.getText());
            jl_aviso.setText(t.adicionarIngrediente(this.jtf_nomeIngrediente.getText()));

            refreshIngredientes();
            limparAviso();
            this.jtf_nomeIngrediente.setText("");
        } else {
            jl_aviso.setText("Para adicionar, escreva o nome do ingrediente!");
            limparAviso();
        }
    }//GEN-LAST:event_jb_adicionarActionPerformed

    private void jb_removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_removerActionPerformed
        if (jl_listaIngredientes.isSelectionEmpty()) {
            jl_aviso.setText("Por favor escolha um ingrediente!");
        } else {
            //t.removerIngrediente(this.jtf_nomeIngrediente.getText());
            jl_aviso.setText(t.removerIngrediente(jl_listaIngredientes.getSelectedValue().toString()));
            //jl_aviso.setText(t.removerIngrediente(jtf_nomeIngrediente.getText()));
            refreshIngredientes();
            limparAviso();
            this.jtf_nomeIngrediente.setText("");
        }
    }//GEN-LAST:event_jb_removerActionPerformed

    private void jb_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_editarActionPerformed

        if (jl_listaIngredientes.isSelectionEmpty()) {
            jl_aviso.setText("Por favor escolha um ingrediente!");
        } else {
            System.out.println("3");
            jl_aviso.setText(t.editarIngrediente(jl_listaIngredientes.getSelectedValue().toString(), this.jtf_nomeIngrediente.getText()));

            refreshIngredientes();
            limparAviso();
            this.jtf_nomeIngrediente.setText("");
        }


    }//GEN-LAST:event_jb_editarActionPerformed

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
            java.util.logging.Logger.getLogger(FormIngredients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormIngredients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormIngredients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormIngredients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FormIngredients().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jb_adicionar;
    private javax.swing.JButton jb_editar;
    private javax.swing.JButton jb_remover;
    private javax.swing.JLabel jl_aviso;
    private javax.swing.JList<String> jl_listaIngredientes;
    private javax.swing.JTextField jtf_nomeIngrediente;
    // End of variables declaration//GEN-END:variables
}
