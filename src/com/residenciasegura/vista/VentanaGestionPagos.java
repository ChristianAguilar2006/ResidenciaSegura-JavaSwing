package com.residenciasegura.vista;

import com.residenciasegura.controlador.ControladorPago;
import com.residenciasegura.controlador.ControladorUsuario;
import com.residenciasegura.modelo.Pago;
import com.residenciasegura.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana para gestionar pagos (CRUD completo)
 * 
 * @author DARIX
 */
public class VentanaGestionPagos extends javax.swing.JFrame {
    
    private final Usuario usuarioActual;
    private final ControladorPago controladorPago;
    private final ControladorUsuario controladorUsuario;
    private DefaultTableModel modeloTabla;
    
    public VentanaGestionPagos(Usuario usuario) {
        this.usuarioActual = usuario;
        this.controladorPago = new ControladorPago();
        this.controladorUsuario = new ControladorUsuario();
        initComponents();
        configurarVentana();
        cargarDatos();
    }
    
    private void configurarVentana() {
        setLocationRelativeTo(null);
        
        // Configurar tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Monto");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Método");
        tablaPagos.setModel(modeloTabla);
        
        // Si es admin, solo puede ver (solo lectura)
        if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
            setTitle("Consulta de Pagos - Residencia Segura (Solo Lectura)");
            // Ocultar panel de formulario completamente para admin
            jPanel2.setVisible(false);
            // Cargar todos los pagos
            cargarDatos();
        } else {
            setTitle("Pagar Servicios - Residencia Segura");
            // Si es residente, ocultar combo de usuario y usar el usuario actual
            comboUsuario.setVisible(false);
            jLabel2.setVisible(false);
            // Simplificar: solo mostrar campos esenciales para pagar
            txtComprobante.setVisible(false);
            jLabel8.setVisible(false);
            txtObservaciones.setVisible(false);
            jLabel9.setVisible(false);
            jScrollPane2.setVisible(false);
            comboEstado.setVisible(false);
            jLabel6.setVisible(false);
            // El estado siempre será pendiente al crear
            // Cargar solo los pagos del residente
            cargarDatos();
        }
    }
    
    
    private void cargarUsuarios() {
        List<Usuario> usuarios = controladorUsuario.obtenerTodos();
        comboUsuario.removeAllItems();
        for (Usuario u : usuarios) {
            comboUsuario.addItem(u.getNombre() + " (" + u.getCorreo() + ")");
        }
    }
    
    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Pago> pagos;
        
        // Si es admin, ver todos los pagos
        if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
            pagos = controladorPago.obtenerTodos();
        } else {
            // Si es residente, solo sus pagos
            pagos = controladorPago.obtenerPorUsuario(usuarioActual.getIdUsuario());
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Pago pago : pagos) {
            Object[] fila = {
                pago.getIdPago(),
                pago.getNombreUsuario() != null ? pago.getNombreUsuario() : usuarioActual.getNombre(),
                pago.getTipoServicio().getValor(),
                "$" + pago.getMonto(),
                pago.getFechaPago() != null ? sdf.format(pago.getFechaPago()) : "N/A",
                pago.getEstado().getValor(),
                pago.getMetodoPago().getValor()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void limpiarCampos() {
        txtMonto.setText("");
        txtComprobante.setText("");
        txtObservaciones.setText("");
        comboUsuario.setSelectedIndex(0);
        comboTipo.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        comboMetodo.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPagos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboUsuario = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        comboMetodo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtComprobante = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        btnCrear = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("GESTIÓN DE PAGOS");

        tablaPagos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPagosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPagos);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Pago"));

        jLabel2.setText("Usuario:");

        comboUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));

        jLabel3.setText("Tipo de Servicio:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "alicuota", "agua", "luz", "internet", "mantenimiento", "otro" }));

        jLabel4.setText("Monto:");

        jLabel5.setText("Fecha (YYYY-MM-DD):");

        jLabel6.setText("Estado:");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pendiente", "pagado", "rechazado" }));

        jLabel7.setText("Método de Pago:");

        comboMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "efectivo", "transferencia", "tarjeta" }));

        jLabel8.setText("Comprobante:");

        jLabel9.setText("Observaciones:");

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

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
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMonto)
                            .addComponent(txtFecha)
                            .addComponent(comboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboMetodo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtComprobante)
                            .addComponent(jScrollPane2)))
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
                    .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 
                    usuarioActual.getRol() == Usuario.Rol.ADMIN ? 500 : 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 
                    usuarioActual.getRol() == Usuario.Rol.ADMIN ? 0 : javax.swing.GroupLayout.DEFAULT_SIZE, 
                    usuarioActual.getRol() == Usuario.Rol.ADMIN ? 0 : javax.swing.GroupLayout.DEFAULT_SIZE)
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

    private void tablaPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPagosMouseClicked
        int fila = tablaPagos.getSelectedRow();
        if (fila >= 0) {
            int idPago = (Integer) modeloTabla.getValueAt(fila, 0);
            Pago pago = controladorPago.obtenerPorId(idPago);
            if (pago != null) {
                // Si es admin, solo puede ver (no editar)
                if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
                    // Mostrar información en un mensaje
                    String info = "ID: " + pago.getIdPago() + "\n" +
                                 "Usuario: " + (pago.getNombreUsuario() != null ? pago.getNombreUsuario() : "N/A") + "\n" +
                                 "Tipo: " + pago.getTipoServicio().getValor() + "\n" +
                                 "Monto: $" + pago.getMonto() + "\n" +
                                 "Fecha: " + pago.getFechaPago() + "\n" +
                                 "Estado: " + pago.getEstado().getValor() + "\n" +
                                 "Método: " + pago.getMetodoPago().getValor() + "\n" +
                                 "Comprobante: " + (pago.getComprobante() != null ? pago.getComprobante() : "N/A") + "\n" +
                                 "Observaciones: " + (pago.getObservaciones() != null ? pago.getObservaciones() : "N/A");
                    JOptionPane.showMessageDialog(this, info, "Detalles del Pago", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Si es residente, puede editar solo sus propios pagos
                    if (pago.getIdUsuario() == usuarioActual.getIdUsuario()) {
                        // Llenar campos con los datos del pago seleccionado
                        txtMonto.setText(pago.getMonto().toString());
                        txtFecha.setText(pago.getFechaPago().toString());
                        txtComprobante.setText(pago.getComprobante() != null ? pago.getComprobante() : "");
                        txtObservaciones.setText(pago.getObservaciones() != null ? pago.getObservaciones() : "");
                        comboTipo.setSelectedItem(pago.getTipoServicio().getValor());
                        comboEstado.setSelectedItem(pago.getEstado().getValor());
                        comboMetodo.setSelectedItem(pago.getMetodoPago().getValor());
                    } else {
                        JOptionPane.showMessageDialog(this, "Solo puede editar sus propios pagos", "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_tablaPagosMouseClicked

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // Solo residentes pueden crear pagos
        if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
            JOptionPane.showMessageDialog(this, "Los administradores solo pueden consultar pagos", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            // Validar campos
            if (txtMonto.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el monto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (txtFecha.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la fecha", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Pago pago = new Pago();
            pago.setIdUsuario(usuarioActual.getIdUsuario()); // El residente crea su propio pago
            pago.setTipoServicio(Pago.TipoServicio.fromString(comboTipo.getSelectedItem().toString()));
            pago.setMonto(new BigDecimal(txtMonto.getText()));
            pago.setFechaPago(Date.valueOf(txtFecha.getText()));
            pago.setEstado(Pago.EstadoPago.PENDIENTE); // Por defecto pendiente
            pago.setMetodoPago(Pago.MetodoPago.fromString(comboMetodo.getSelectedItem().toString()));
            pago.setComprobante(txtComprobante.getText().trim().isEmpty() ? null : txtComprobante.getText());
            pago.setObservaciones(txtObservaciones.getText().trim().isEmpty() ? null : txtObservaciones.getText());
            
            if (controladorPago.crearPago(pago)) {
                JOptionPane.showMessageDialog(this, "Pago registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el pago", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage() + "\nFormato de fecha: YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // Solo residentes pueden actualizar sus propios pagos
        if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
            JOptionPane.showMessageDialog(this, "Los administradores solo pueden consultar pagos", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int fila = tablaPagos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un pago de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int idPago = (Integer) modeloTabla.getValueAt(fila, 0);
            Pago pago = controladorPago.obtenerPorId(idPago);
            
            if (pago != null) {
                // Verificar que el pago pertenezca al usuario actual
                if (pago.getIdUsuario() != usuarioActual.getIdUsuario()) {
                    JOptionPane.showMessageDialog(this, "Solo puede actualizar sus propios pagos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Validar campos
                if (txtMonto.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese el monto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (txtFecha.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese la fecha", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                pago.setIdUsuario(usuarioActual.getIdUsuario()); // Mantener el mismo usuario
                pago.setTipoServicio(Pago.TipoServicio.fromString(comboTipo.getSelectedItem().toString()));
                pago.setMonto(new BigDecimal(txtMonto.getText()));
                pago.setFechaPago(Date.valueOf(txtFecha.getText()));
                // El residente puede cambiar el estado solo si está pendiente
                if (pago.getEstado() == Pago.EstadoPago.PENDIENTE) {
                    pago.setEstado(Pago.EstadoPago.fromString(comboEstado.getSelectedItem().toString()));
                }
                pago.setMetodoPago(Pago.MetodoPago.fromString(comboMetodo.getSelectedItem().toString()));
                pago.setComprobante(txtComprobante.getText().trim().isEmpty() ? null : txtComprobante.getText());
                pago.setObservaciones(txtObservaciones.getText().trim().isEmpty() ? null : txtObservaciones.getText());
                
                if (controladorPago.actualizarPago(pago)) {
                    JOptionPane.showMessageDialog(this, "Pago actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarDatos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el pago", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage() + "\nFormato de fecha: YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Solo residentes pueden eliminar sus propios pagos pendientes
        if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
            JOptionPane.showMessageDialog(this, "Los administradores solo pueden consultar pagos", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int fila = tablaPagos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un pago de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int idPago = (Integer) modeloTabla.getValueAt(fila, 0);
            Pago pago = controladorPago.obtenerPorId(idPago);
            
            if (pago != null) {
                // Verificar que el pago pertenezca al usuario actual
                if (pago.getIdUsuario() != usuarioActual.getIdUsuario()) {
                    JOptionPane.showMessageDialog(this, "Solo puede eliminar sus propios pagos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Solo se pueden eliminar pagos pendientes
                if (pago.getEstado() != Pago.EstadoPago.PENDIENTE) {
                    JOptionPane.showMessageDialog(this, "Solo se pueden eliminar pagos pendientes", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int respuesta = JOptionPane.showConfirmDialog(this, 
                    "¿Está seguro que desea eliminar este pago?", 
                    "Confirmar eliminación", 
                    JOptionPane.YES_NO_OPTION);
                
                if (respuesta == JOptionPane.YES_OPTION) {
                    if (controladorPago.eliminarPago(idPago)) {
                        JOptionPane.showMessageDialog(this, "Pago eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        cargarDatos();
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el pago", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboMetodo;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JComboBox<String> comboUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaPagos;
    private javax.swing.JTextField txtComprobante;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables
}

