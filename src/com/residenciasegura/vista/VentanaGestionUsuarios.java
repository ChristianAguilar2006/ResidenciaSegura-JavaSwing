package com.residenciasegura.vista;

import com.residenciasegura.controlador.ControladorUsuario;
import com.residenciasegura.modelo.Usuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana para gestionar usuarios (CRUD completo) - Admin
 * 
 * @author DARIX
 */
public class VentanaGestionUsuarios extends javax.swing.JFrame {
    
    private final Usuario usuarioActual;
    private final ControladorUsuario controlador;
    private DefaultTableModel modeloTabla;
    
    public VentanaGestionUsuarios(Usuario usuario) {
        this.usuarioActual = usuario;
        this.controlador = new ControladorUsuario();
        initComponents();
        configurarVentana();
        cargarDatos();
    }
    
    private void configurarVentana() {
        setTitle("Gestión de Usuarios - Residencia Segura");
        setLocationRelativeTo(null);
        
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Rol");
        modeloTabla.addColumn("Departamento");
        modeloTabla.addColumn("Teléfono");
        tablaUsuarios.setModel(modeloTabla);
    }
    
    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        java.util.List<Usuario> usuarios = controlador.obtenerTodos();
        
        for (Usuario u : usuarios) {
            Object[] fila = {
                u.getIdUsuario(),
                u.getNombre(),
                u.getCorreo(),
                u.getRol().getValor(),
                u.getDepartamento(),
                u.getTelefono() != null ? u.getTelefono() : "N/A"
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        txtDepartamento.setText("");
        txtTelefono.setText("");
        comboRol.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        comboRol = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDepartamento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnCrear = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("GESTIÓN DE USUARIOS");

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Usuario"));

        jLabel2.setText("Nombre:");

        jLabel3.setText("Correo:");

        jLabel4.setText("Contraseña:");

        jLabel5.setText("Rol:");

        comboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "residente", "guardia" }));

        jLabel6.setText("Departamento:");

        jLabel7.setText("Teléfono:");

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
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre)
                            .addComponent(txtCorreo)
                            .addComponent(txtContrasena)
                            .addComponent(comboRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDepartamento)
                            .addComponent(txtTelefono)))
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
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        int fila = tablaUsuarios.getSelectedRow();
        if (fila >= 0) {
            int idUsuario = (Integer) modeloTabla.getValueAt(fila, 0);
            Usuario usuario = controlador.obtenerPorId(idUsuario);
            if (usuario != null) {
                txtNombre.setText(usuario.getNombre());
                txtCorreo.setText(usuario.getCorreo());
                txtContrasena.setText(""); // No mostrar contraseña por seguridad
                comboRol.setSelectedItem(usuario.getRol().getValor());
                txtDepartamento.setText(usuario.getDepartamento());
                txtTelefono.setText(usuario.getTelefono() != null ? usuario.getTelefono() : "");
            }
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        try {
            if (txtNombre.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty() || 
                txtContrasena.getText().trim().isEmpty() || txtDepartamento.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Usuario usuario = new Usuario();
            usuario.setNombre(txtNombre.getText().trim());
            usuario.setCorreo(txtCorreo.getText().trim());
            usuario.setContrasena(txtContrasena.getText().trim());
            usuario.setRol(Usuario.Rol.fromString(comboRol.getSelectedItem().toString()));
            usuario.setDepartamento(txtDepartamento.getText().trim());
            usuario.setTelefono(txtTelefono.getText().trim().isEmpty() ? null : txtTelefono.getText().trim());
            
            if (controlador.crearUsuario(usuario)) {
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int idUsuario = (Integer) modeloTabla.getValueAt(fila, 0);
            Usuario usuario = controlador.obtenerPorId(idUsuario);
            
            if (usuario != null) {
                if (usuario.getIdUsuario() == usuarioActual.getIdUsuario()) {
                    JOptionPane.showMessageDialog(this, "No puede modificar su propio usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (txtNombre.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty() || 
                    txtDepartamento.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Complete  los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                usuario.setNombre(txtNombre.getText().trim());
                usuario.setCorreo(txtCorreo.getText().trim());
                usuario.setRol(Usuario.Rol.fromString(comboRol.getSelectedItem().toString()));
                usuario.setDepartamento(txtDepartamento.getText().trim());
                usuario.setTelefono(txtTelefono.getText().trim().isEmpty() ? null : txtTelefono.getText().trim());
                
                if (controlador.actualizarUsuario(usuario)) {
                    cargarDatos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error  actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int idUsuario = (Integer) modeloTabla.getValueAt(fila, 0);
        
        if (idUsuario == usuarioActual.getIdUsuario()) {
            JOptionPane.showMessageDialog(this, "No puede eliminar su propio usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Eliminar usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            if (controlador.eliminarUsuario(idUsuario)) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> comboRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtDepartamento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
