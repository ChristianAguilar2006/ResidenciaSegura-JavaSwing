package com.residenciasegura.vista;

import com.residenciasegura.controlador.ControladorAviso;
import com.residenciasegura.modelo.Aviso;
import com.residenciasegura.modelo.Usuario;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana para gestionar avisos (CRUD completo) - Admin
 * 
 * @author DARIX
 */
public class VentanaGestionAvisos extends javax.swing.JFrame {
    
    private final Usuario usuarioActual;
    private final ControladorAviso controlador;
    private DefaultTableModel modeloTabla;
    
    public VentanaGestionAvisos(Usuario usuario) {
        this.usuarioActual = usuario;
        this.controlador = new ControladorAviso();
        initComponents();
        configurarVentana();
        cargarDatos();
    }
    
    private void configurarVentana() {
        setTitle("Gestión de Avisos - Residencia Segura");
        setLocationRelativeTo(null);
        
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Título");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Estado");
        tablaAvisos.setModel(modeloTabla);
    }
    
    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Aviso> avisos = controlador.obtenerTodos();
        
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        checkActivo = new javax.swing.JCheckBox();
        btnCrear = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("GESTIÓN DE AVISOS");

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
        tablaAvisos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAvisosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAvisos);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Aviso"));

        jLabel2.setText("Título:");

        jLabel3.setText("Mensaje:");

        txtMensaje.setColumns(20);
        txtMensaje.setRows(5);
        jScrollPane2.setViewportView(txtMensaje);

        jLabel4.setText("Tipo:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "informativo", "alerta", "mantenimiento", "emergencia" }));

        jLabel5.setText("Activo:");

        checkActivo.setSelected(true);

        btnCrear.setText("Crear");
        btnCrear.addActionListener(this::btnCrearActionPerformed);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTitulo)
                            .addComponent(jScrollPane2)
                            .addComponent(comboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkActivo)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(checkActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tablaAvisosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAvisosMouseClicked
        int fila = tablaAvisos.getSelectedRow();
        if (fila >= 0) {
            int idAviso = (Integer) modeloTabla.getValueAt(fila, 0);
            Aviso aviso = controlador.obtenerPorId(idAviso);
            if (aviso != null) {
                txtTitulo.setText(aviso.getTitulo());
                txtMensaje.setText(aviso.getMensaje());
                comboTipo.setSelectedItem(aviso.getTipo().getValor());
                checkActivo.setSelected(aviso.isActivo());
            }
        }
    }//GEN-LAST:event_tablaAvisosMouseClicked

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        try {
            if (txtTitulo.getText().trim().isEmpty() || txtMensaje.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Aviso aviso = new Aviso();
            aviso.setIdAdministrador(usuarioActual.getIdUsuario());
            aviso.setTitulo(txtTitulo.getText().trim());
            aviso.setMensaje(txtMensaje.getText().trim());
            aviso.setTipo(Aviso.TipoAviso.fromString(comboTipo.getSelectedItem().toString()));
            aviso.setActivo(checkActivo.isSelected());
            
            if (controlador.crearAviso(aviso)) {
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        int fila = tablaAvisos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un aviso", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int idAviso = (Integer) modeloTabla.getValueAt(fila, 0);
            Aviso aviso = controlador.obtenerPorId(idAviso);
            
            if (aviso != null) {
                if (txtTitulo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese el título del aviso", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (txtMensaje.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese el mensaje del aviso", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                aviso.setTitulo(txtTitulo.getText().trim());
                aviso.setMensaje(txtMensaje.getText().trim());
                aviso.setTipo(Aviso.TipoAviso.fromString(comboTipo.getSelectedItem().toString()));
                aviso.setActivo(checkActivo.isSelected());
                
                if (controlador.actualizarAviso(aviso)) {
                    cargarDatos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tablaAvisos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un aviso", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Eliminar aviso?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            int idAviso = (Integer) modeloTabla.getValueAt(fila, 0);
            if (controlador.eliminarAviso(idAviso)) {
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtMensaje.setText("");
        comboTipo.setSelectedIndex(0);
        checkActivo.setSelected(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JCheckBox checkActivo;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaAvisos;
    private javax.swing.JTextArea txtMensaje;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
