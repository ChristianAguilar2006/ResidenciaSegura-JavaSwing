package com.residenciasegura.vista;

import com.residenciasegura.controlador.ControladorAviso;
import com.residenciasegura.modelo.Aviso;
import com.residenciasegura.modelo.Usuario;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana para ver avisos del administrador
 * 
 * @author DARIX
 */
public class VentanaVerAvisos extends javax.swing.JFrame {
    
    private final Usuario usuarioActual;
    private final ControladorAviso controlador;
    private DefaultTableModel modeloTabla;
    
    public VentanaVerAvisos(Usuario usuario) {
        this.usuarioActual = usuario;
        this.controlador = new ControladorAviso();
        initComponents();
        configurarVentana();
        cargarAvisos();
    }
    
    private void configurarVentana() {
        setTitle("Avisos - Residencia Segura");
        setLocationRelativeTo(null);
        
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Título");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Estado");
        tablaAvisos.setModel(modeloTabla);
    }
    
    private void cargarAvisos() {
        modeloTabla.setRowCount(0);
        List<Aviso> avisos = controlador.obtenerActivos();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        for (Aviso aviso : avisos) {
            Object[] fila = {
                aviso.getIdAviso(),
                aviso.getTitulo(),
                aviso.getTipo().getValor(),
                aviso.getFechaPublicacion() != null ? sdf.format(aviso.getFechaPublicacion()) : "N/A",
                aviso.isActivo() ? "Activo" : "Inactivo"
            };
            modeloTabla.addRow(fila);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAvisos = new javax.swing.JTable();
        btnVerDetalle = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("AVISOS DEL ADMINISTRADOR");

        tablaAvisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaAvisos);

        btnVerDetalle.setText("Ver Detalle");
        btnVerDetalle.addActionListener(this::btnVerDetalleActionPerformed);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnVerDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerDetalle)
                    .addComponent(btnCerrar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed
        int fila = tablaAvisos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un aviso", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int idAviso = (Integer) modeloTabla.getValueAt(fila, 0);
        Aviso aviso = controlador.obtenerPorId(idAviso);
        
        if (aviso != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String detalle = "TÍTULO: " + aviso.getTitulo() + "\n\n" +
                           "TIPO: " + aviso.getTipo().getValor() + "\n\n" +
                           "MENSAJE:\n" + aviso.getMensaje() + "\n\n" +
                           "FECHA: " + (aviso.getFechaPublicacion() != null ? sdf.format(aviso.getFechaPublicacion()) : "N/A") + "\n" +
                           "ESTADO: " + (aviso.isActivo() ? "Activo" : "Inactivo");
            
            JOptionPane.showMessageDialog(this, detalle, "Detalle del Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnVerDetalleActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAvisos;
    // End of variables declaration//GEN-END:variables
}

