package com.residenciasegura.vista;

import com.residenciasegura.controlador.ControladorReporte;
import com.residenciasegura.modelo.Reporte;
import com.residenciasegura.modelo.Usuario;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana para que los guardias vean y atiendan reportes
 * 
 * @author DARIX
 */
public class VentanaGestionReportesGuardia extends javax.swing.JFrame {
    
    private final Usuario usuarioActual;
    private final ControladorReporte controlador;
    private DefaultTableModel modeloTabla;
    
    public VentanaGestionReportesGuardia(Usuario usuario) {
        this.usuarioActual = usuario;
        this.controlador = new ControladorReporte();
        initComponents();
        configurarVentana();
        cargarReportesPendientes();
    }
    
    private void configurarVentana() {
        setTitle("Gestión de Reportes - Guardia");
        setLocationRelativeTo(null);
        
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Ubicación");
        modeloTabla.addColumn("Prioridad");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Fecha");
        tablaReportes.setModel(modeloTabla);
    }
    
    private void cargarReportesPendientes() {
        modeloTabla.setRowCount(0);
        List<Reporte> reportes = controlador.obtenerPorEstado("pendiente");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        for (Reporte reporte : reportes) {
            Object[] fila = {
                reporte.getIdReporte(),
                reporte.getNombreUsuario() != null ? reporte.getNombreUsuario() : "N/A",
                reporte.getTipo().getValor(),
                reporte.getUbicacion(),
                reporte.getPrioridad().getValor(),
                reporte.getEstado().getValor(),
                reporte.getFechaReporte() != null ? sdf.format(reporte.getFechaReporte()) : "N/A"
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
        tablaReportes = new javax.swing.JTable();
        btnAtender = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("REPORTES PENDIENTES");

        tablaReportes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaReportes);

        btnAtender.setText("Atender Reporte");
        btnAtender.addActionListener(this::btnAtenderActionPerformed);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnVerDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtender)
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
                    .addComponent(btnAtender)
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

    private void btnAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderActionPerformed
        int fila = tablaReportes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un reporte de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int idReporte = (Integer) modeloTabla.getValueAt(fila, 0);
        
        if (controlador.asignarGuardia(idReporte, usuarioActual.getIdUsuario())) {
            JOptionPane.showMessageDialog(this, "Reporte asignado exitosamente. Estado cambiado a 'En Proceso'", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarReportesPendientes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al atender el reporte", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAtenderActionPerformed

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed
        int fila = tablaReportes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un reporte de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int idReporte = (Integer) modeloTabla.getValueAt(fila, 0);
        Reporte reporte = controlador.obtenerPorId(idReporte);
        
        if (reporte != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String detalle = "ID: " + reporte.getIdReporte() + "\n\n" +
                           "USUARIO: " + (reporte.getNombreUsuario() != null ? reporte.getNombreUsuario() : "N/A") + "\n" +
                           "TIPO: " + reporte.getTipo().getValor() + "\n" +
                           "UBICACIÓN: " + reporte.getUbicacion() + "\n" +
                           "PRIORIDAD: " + reporte.getPrioridad().getValor() + "\n" +
                           "ESTADO: " + reporte.getEstado().getValor() + "\n" +
                           "FECHA: " + (reporte.getFechaReporte() != null ? sdf.format(reporte.getFechaReporte()) : "N/A") + "\n\n" +
                           "DESCRIPCIÓN:\n" + reporte.getDescripcion();
            
            if (reporte.getNombreGuardia() != null) {
                detalle += "\n\nGUARDIA ASIGNADO: " + reporte.getNombreGuardia();
            }
            
            if (reporte.getFechaResolucion() != null) {
                detalle += "\nFECHA RESOLUCIÓN: " + sdf.format(reporte.getFechaResolucion());
            }
            
            JOptionPane.showMessageDialog(this, detalle, "Detalle del Reporte", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnVerDetalleActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtender;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReportes;
    // End of variables declaration//GEN-END:variables
}

